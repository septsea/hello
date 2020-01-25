import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Prog5_1 extends Applet implements ActionListener {
    Button exitButton;

    public void init() {
        add(new Button("Button 1"));
        add(new Button("Button 2"));
        add(new Label("Text field: "));
        add(new TextField(10));
        add(new Label("Text area: "));
        add(new TextArea(10, 20));
        exitButton = new Button("EXIT");
        exitButton.addActionListener(this);
        add(exitButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
