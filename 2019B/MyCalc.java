import java.awt.*;
import java.awt.event.*;

public class MyCalc {

    /* As you can see, wlkl means "wo lie kai le". */
    public static void main(String... wlkl) {
        new Calculator().init();
    }

}

class Calculator extends Frame implements ActionListener {

    /* It is recommended to add serialVersionUID. */
    private static final long serialVersionUID = 1L;
    TextField expressionBox;
    Button equalButton;
    Button[][] buttons;
    Panel upperPanel, lowerPanel;

    public void init() {
        String[][] buttonnames = { { "(", ")", "BkSp", "CA" }, { "7", "8", "9", "+" }, { "4", "5", "6", "-" },
                { "1", "2", "3", "*" }, { ".", "0", "E", "/" } };

        setLayout(new BorderLayout(3, 3));
        /* Change the size of the window if necessary. */
        setSize(220, 200);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        upperPanel = new Panel();
        upperPanel.setLayout(new FlowLayout());
        expressionBox = new TextField(18);
        /* Enable users to press ENTER to perform computations. */
        expressionBox.addActionListener(this);
        upperPanel.add(expressionBox);
        equalButton = new Button("=");
        equalButton.addActionListener(this);
        upperPanel.add(equalButton);
        add(upperPanel, BorderLayout.NORTH);

        lowerPanel = new Panel();
        /* 5 by 4 layout. */
        lowerPanel.setLayout(new GridLayout(5, 4, 3, 3));
        buttons = new Button[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new Button(buttonnames[i][j]);
                lowerPanel.add(buttons[i][j]);
                buttons[i][j].addActionListener(this);
            }
        }
        add(lowerPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == equalButton || e.getSource() == expressionBox) {
            /*
             * expressionBox.getText() returns the expression shown in the text field
             * expressionBox.
             */
            expressionBox.setText(eval(expressionBox.getText()));
        } else if (e.getSource() == buttons[0][3]) {
            expressionBox.setText("");
        } else if (e.getSource() == buttons[0][2]) {
            if (expressionBox.getText().length() > 0) {
                expressionBox.setText(expressionBox.getText().substring(0, expressionBox.getText().length() - 1));
            }
        } else {
            /* e.getActionCommand() returns the name of the button. */
            expressionBox.setText(expressionBox.getText() + e.getActionCommand());
        }
    }

    public String eval(String expr) {
        return Computation
                .evaluate(Computation.InfixToPostfix(new PythonList(Computation.str2backexpr(expr).split("#"))));
    }

}

class PythonList {

    /* It is assumed that the length of the expression is not so long. */
    public static final int MAX_LENGTH = 100;
    private String[] list;
    private int size;

    /** Initialization (with a given String array). */
    PythonList(String... array) {
        this.list = new String[PythonList.MAX_LENGTH];
        this.size = 0;
        this.append(array);
    }

    /** Appends a given String array onto the list. */
    public void append(String... array) {
        for (String x : array) {
            this.list[this.size++] = x;
        }
    }

    /** Removes the last element of the list and returns that element. */
    public String pop() {
        return this.list[--this.size];
    }

    /** Get the length of the list. */
    public int length() {
        return this.size;
    }

    /** Returns whether the list is empty. */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Sets the element at index index to be x. */
    public void element(int index, String x) {
        this.list[index] = x;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    /** Visits the element at index index. */
    public String element(int index) {
        return this.list[index];
    }

    /** Returns the sum of given lists. */
    public static PythonList sum(PythonList... lists) {
        PythonList answer = new PythonList();
        for (PythonList x : lists) {
            for (int i = 0; i < x.length(); answer.append(x.element(i++)))
                ;
        }
        return answer;
    }

    /** Returns the sublist with startIndex <= index < endIndex. */
    public PythonList sublist(int startIndex, int endIndex) {
        PythonList answer = new PythonList();
        for (int i = startIndex; i < endIndex; answer.append(this.element(i++)))
            ;
        return answer;
    }

    /** Returns the sublist with startIndex <= index < length. */
    public PythonList sublist(int startIndex) {
        return this.sublist(startIndex, this.length());
    }

}

class Computation {

    /*
     * Define a small number eps (epsilon). If |a - b| < eps, then a and b are
     * regarded as equal numbers. If |a| < eps, then a is regarded as 0. |a| < eps
     * exactly means a < eps && a > -eps.
     */
    public static final double eps = 2.220446049250313E-16;
    public static final String error = "null";

    public static int grade(String operator) {
        switch (operator) {
        case "*":
        case "/":
            return 1;
        default:
            return 0;
        }
    }

    public static boolean isDigit(char x) {
        return 48 <= (int) x && (int) x <= 57;
    }

    public static boolean isInt(String intstr) {
        if (intstr.isEmpty()) {
            return false;
        } else if (intstr.charAt(0) == '-') {
            return isNum(intstr.substring(1));
        } else {
            for (int i = 0; i < intstr.length(); i++) {
                if (!isDigit(intstr.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isNum(String numstr) {
        int pointflag = 0, eflag = 0;
        if (numstr.isEmpty()) {
            return false;
        } else if (numstr.charAt(0) == '-') {
            return isNum(numstr.substring(1));
        } else if (isDigit(numstr.charAt(0))) {
            for (int i = 1; i < numstr.length(); i++) {
                if (numstr.charAt(i) == '.') {
                    pointflag++;
                    if (pointflag > 1) {
                        return false;
                    }
                } else if (numstr.charAt(i) == 'E' || numstr.charAt(i) == 'e') {
                    eflag++;
                    if (!isInt(numstr.substring(i + 1)) || eflag > 1) {
                        return false;
                    }
                    if (numstr.charAt(i + 1) == '-') {
                        i++;
                    }
                } else if (isDigit(numstr.charAt(i))) {
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOP(String opstr) {
        return opstr.equals("+") || opstr.equals("-") || opstr.equals("*") || opstr.equals("/");
    }

    public static boolean isOP(char opch) {
        return opch == '+' || opch == '-' || opch == '*' || opch == '/';
    }

    /** A powerful method taken from Data Structure. */
    public static PythonList InfixToPostfix(PythonList InfixExpression) {
        PythonList PostfixExpression = new PythonList(), tempList = new PythonList();
        String item, tmp;
        for (int i = 0; i < InfixExpression.length(); i++) {
            item = InfixExpression.element(i);
            if (isOP(item)) {
                while (tempList.length() >= 0) {
                    if (tempList.length() == 0) {
                        tempList.append(item);
                        break;
                    }
                    tmp = tempList.pop();
                    if (tmp.equals("(") || grade(item) > grade(tmp)) {
                        tempList.append(tmp);
                        tempList.append(item);
                        break;
                    } else {
                        PostfixExpression.append(tmp);
                    }
                }
            } else if (item.equals("(")) {
                tempList.append(item);
            } else if (item.equals(")")) {
                while (tempList.length() > 0) {
                    tmp = tempList.pop();
                    if (!tmp.equals("(")) {
                        PostfixExpression.append(tmp);
                    } else {
                        break;
                    }
                }
            } else {
                PostfixExpression.append(item);
            }
        }
        while (tempList.length() > 0) {
            PostfixExpression.append(tempList.pop());
        }
        return PostfixExpression;
    }

    /** A separator adder, with exprseparator = "#". */
    public static String separatorAdder(String expr) {
        String answer = "";
        for (int i = 0; i < expr.length(); i++) {
            if (isOP(expr.charAt(i)) || expr.charAt(i) == '(' || expr.charAt(i) == ')') {
                answer = answer + "#" + expr.charAt(i) + "#";
            } else {
                answer = answer + expr.charAt(i);
            }
        }
        return answer;
    }

    /** A minus sign checker, with exprseparator = "#". */
    public static String minusSignChecker(String expr) {
        String answer = "", tempString = "";
        if (expr.charAt(0) == '#' && expr.charAt(1) == '-' && expr.charAt(2) == '#') {
            if (expr.length() > 4 && expr.charAt(4) == '(') {
                tempString = "#(#-1#)##*#" + expr.substring(3);
            } else {
                tempString = "-" + expr.substring(3);
            }
        } else {
            tempString = expr.substring(0);
        }
        for (int i = 0; i < tempString.length() - 1;) {
            if (tempString.charAt(i) == '#' && tempString.charAt(i + 1) == '-' && tempString.charAt(i + 2) == '#') {
                if (isDigit(tempString.charAt(i + 3))
                        && ((tempString.charAt(i - 1) == 'e' || tempString.charAt(i - 1) == 'E')
                                || (tempString.charAt(i - 1) == '#' && tempString.charAt(i - 3) == '#'
                                        && (isOP(tempString.charAt(i - 2)) || tempString.charAt(i - 2) == '(')))) {
                    answer = answer + "-";
                    i += 3;
                } else if (tempString.charAt(i + 3) == '#' && tempString.charAt(i + 4) == '('
                        && (tempString.charAt(i - 1) == '#' && tempString.charAt(i - 3) == '#'
                                && (isOP(tempString.charAt(i - 2)) || tempString.charAt(i - 2) == '('))) {
                    answer = answer + "#(#-1#)##*#";
                    i += 3;
                } else {
                    answer = answer + tempString.charAt(i++);
                }
            } else {
                answer = answer + tempString.charAt(i++);
            }
        }
        answer = answer + tempString.charAt(tempString.length() - 1);
        return answer;
    }

    /** An additional separator remover, with exprseparator = "#". */
    public static String additionalSeparatorRemover(String expr) {
        String answer = "", tempString = expr.substring(0);
        if (tempString.charAt(0) == '#') {
            tempString = tempString.substring(1);
        }
        if (tempString.charAt(tempString.length() - 1) == '#') {
            tempString = tempString.substring(0, tempString.length() - 1);
        }
        for (int i = 0; i < tempString.length();) {
            if (tempString.charAt(i) == '#' && tempString.charAt(i + 1) == '#') {
                answer = answer + "#";
                i += 2;
            } else {
                answer = answer + tempString.charAt(i++);
            }
        }
        return answer;
    }

    public static String str2backexpr(String expr) {
        return additionalSeparatorRemover(minusSignChecker(separatorAdder(expr)));
    }

    public static String operate(String operand1, String operand2, String operator) {
        if (isNum(operand1) && isNum(operand2)) {
            double number1 = Double.parseDouble(operand1), number2 = Double.parseDouble(operand2);
            switch (operator) {
            case "+":
                return "" + (number1 + number2);
            case "-":
                return "" + (number1 - number2);
            case "*":
                return "" + (number1 * number2);
            case "/":
                if (number2 >= eps || number2 <= -eps) {
                    return "" + (number1 / number2);
                }
            }
        }
        return error;
    }

    public static String evaluate(PythonList PostfixExpression) {
        String operand1, operand2, operator;
        if (PostfixExpression.length() == 0 || PostfixExpression.length() == 2) {
        } else if (PostfixExpression.length() == 1 && isNum(PostfixExpression.element(0))) {
            return "" + Double.parseDouble(PostfixExpression.element(0));
        } else {
            for (int i = 2; i < PostfixExpression.length(); i++) {
                operand1 = PostfixExpression.element(i - 2);
                operand2 = PostfixExpression.element(i - 1);
                operator = PostfixExpression.element(i);
                if (isNum(operand1) && isNum(operand2) && isOP(operator)) {
                    return evaluate(PythonList.sum(PostfixExpression.sublist(0, i - 2),
                            new PythonList(operate(operand1, operand2, operator)),
                            PythonList.sum(PostfixExpression.sublist(i + 1))));
                }
            }
        }
        return error;
    }

}
