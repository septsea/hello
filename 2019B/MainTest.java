import java.awt.*;
import java.awt.event.*;

public class MainTest {

    public static void main(String[] args) {
        new Calculator().init();
    }

}

class Calculator extends Frame {

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
        upperPanel.add(expressionBox);
        equalButton = new Button("=");
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
            }
        }
        add(lowerPanel, BorderLayout.SOUTH);
    }

}
