# How to "run" Applet programs in JDK 12+

Original Code:

```java
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class <Class name> extends Applet implements <Interfaces> {
    <Declaration of variables>

    public void init() {
        <Your code here>
    }

    <Overridden listener methods>
}

```

Now just change it to:

```java
import java.awt.*;
import java.awt.event.*;

// Add a main class.
public class <Main class name> {
    public static void main(String[] args) {
        new <Class name>().init();
    }
}

// Note that <Class name> cannot be public now.
class <Class name> extends Frame implements <Interfaces> {
    <Declaration of variables>

    public void init() {
        // Here are codes required by Frame.
        setLayout(new FlowLayout());
        setSize(300, 300);      //Or any other suitable size.
        setVisible(true);
        this.pack();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        <Your code here>
    }

    <Overridden listener methods>
}

```

If there are any questions, do not hesitate to contact me.

Good luck!
