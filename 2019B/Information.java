package AppletTest;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Information extends Applet implements ActionListener, ItemListener, TextListener {

    private static final long serialVersionUID = 1L;
    TextField namebox;
    CheckboxGroup gender;
    Checkbox male, female;
    Checkbox cet6;
    Choice colors;
    List sports;
    TextArea information;
    Button exitButton;
    private String[] data;

    public void init() {
        data = new String[5];
        namebox = new TextField(10);
        namebox.addTextListener(this);
        add(namebox);
        gender = new CheckboxGroup();
        male = new Checkbox("Male", gender, false);
        female = new Checkbox("Female", gender, false);
        male.addItemListener(this);
        female.addItemListener(this);
        add(male);
        add(female);
        cet6 = new Checkbox("I have passed CET-6.", false);
        cet6.addItemListener(this);
        add(cet6);
        add(new Label("My favorite color and sports are: "));
        colors = new Choice();
        colors.add(" ");
        colors.add("Green");
        colors.add("Red");
        colors.add("Blue");
        colors.addItemListener(this);
        add(colors);
        sports = new List();
        sports.add("Basketball");
        sports.add("Table Tennis");
        sports.add("Swimming");
        sports.addItemListener(this);
        add(sports);
        add(new Label("Show personal information: "));
        information = new TextArea(6, 12);
        add(information);
        exitButton = new Button("Finish filling information");
        exitButton.addActionListener(this);
        add(exitButton);
    }

    String updatedata(String sex) {
        data[0] = namebox.getText();
        data[1] = sex;
        data[2] = cet6.getState() ? "Passed CET-6" : "Not passed CET-6";
        data[3] = colors.getSelectedItem();
        data[4] = sports.getSelectedItem();
        return data[0] + "\n" + data[1] + "\n" + data[2] + "\n" + data[3] + "\n" + data[4];
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == male) {
            information.setText(updatedata("Male"));
        } else if (e.getSource() == female) {
            information.setText(updatedata("Female"));
        } else {
            information.setText(updatedata(data[1]));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.out.println(updatedata(data[1]));
            System.exit(0);
        }
    }

    @Override
    public void textValueChanged(TextEvent e) {
        information.setText(updatedata(data[1]));
    }

}
