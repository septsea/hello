import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Exam5_4 extends Applet implements TextListener {
    private static final long serialVersionUID = 1L;
    Label lab1, lab2, lab3;
    TextField text1, text2, text3;
    String str;
    int i;
    float f;

    public void init() {
        lab1 = new Label("Enter an integer: ");
        add(lab1);
        text1 = new TextField("0", 30);
        text1.addTextListener(this);
        add(text1);
        lab2 = new Label("Enter a float: ");
        add(lab2);
        text2 = new TextField("0.0 ", 30);
        text2.addTextListener(this);
        add(text2);
        lab3 = new Label("Enter a string: ");
        add(lab3);
        text3 = new TextField(" ", 30);
        text3.addTextListener(this);
        add(text3);
    }

    public void textValueChanged(TextEvent e) {
        i = Integer.parseInt(text1.getText());
        f = (Float.valueOf(text2.getText())).floatValue();
        str = text3.getText();
        repaint();
    }

    public void paint(Graphics g) {
        g.drawString("Integer = " + i, 20, 150);
        g.drawString("Float = " + f, 20, 180);
        g.drawString("String = " + str, 20, 210);
    }
}
