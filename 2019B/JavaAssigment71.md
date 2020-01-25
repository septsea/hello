# Assignment 7-1

编程题如下:

1\. 编写 Application, 在其中创建 ```subThread``` 类的三个对象 ```t1```、```t2```、```t3```, 它们的名称分别为 ```First```, ```Second```, ```Third```, 并启动这三个线程。请编写一个类, 类名为 ```subThread```, 它是 ```Thread``` 类的子类。该类中定义了含一个字符串参数的构造函数和 ```run()``` 方法, 方法中有一个 ```for``` 循环, 循环一共进行 5 次, 循环体中先在命令行显示该线程循环到了第几次, 然后随机休眠小于 1 秒的时间, 循环结束后显示线程结束信息: 线程名 + "finished"。

```java
// File: subThread.java

class subThread extends Thread {

    String name;

    public static void main(String[] args) {
        Thread t1 = new subThread("First");
        Thread t2 = new subThread("Second");
        Thread t3 = new subThread("Third");
        t1.start();
        t2.start();
        t3.start();
    }

    public subThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long sleeptime;
        for (int i = 0; i < 5; i++) {
            try {
                sleeptime = (long) (Math.random() * 1000);
                System.out.println("Thread \"" + this.name + "\": the " + num2ord(i + 1) + " time");
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread \"" + this.name + "\" finished.");
    }

    public static String num2ord(int x) {
        if (x < 0) {
            return "";
        } else if (x % 100 == 11 || x % 100 == 12 || x % 100 == 13) {
            return x + "th";
        } else if (x % 10 == 1) {
            return x + "st";
        } else if (x % 10 == 2) {
            return x + "nd";
        } else if (x % 10 == 3) {
            return x + "rd";
        } else {
            return x + "th";
        }
    }

}

```

2\. 编写一个类, 类名为 ```MulThread```, 类中定义了含一个字符串参数的构造函数, 并实现了 ```Runnable``` 接口, 接口中的 ```run()``` 方法如下实现: 方法中先在命令行显示该线程信息, 然后随机休眠小于 1 秒的时间, 最后后显示线程结束信息: "finished" + 线程名。编写 Application, 在其中通过 ```Runnable``` 创建 ```MulThread``` 类的三个线程对象 ```t1```、```t2```、```t3```, 并启动这三个线程。

``` java
// File: MulThread.java

class MulThread implements Runnable {

    String name;

    public static void main(String[] args) {
        Thread t1 = new Thread(new MulThread("First"));
        Thread t2 = new Thread(new MulThread("Second"));
        Thread t3 = new Thread(new MulThread("Third"));
        t1.start();
        t2.start();
        t3.start();
    }

    public MulThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long sleeptime;
        for (int i = 0; i < 5; i++) {
            try {
                sleeptime = (long) (Math.random() * 1000);
                System.out.println("Thread \"" + this.name + "\": the " + num2ord(i + 1) + " time");
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread \"" + this.name + "\" finished.");
    }

    public static String num2ord(int x) {
        if (x < 0) {
            return "";
        } else if (x % 100 == 11 || x % 100 == 12 || x % 100 == 13) {
            return x + "th";
        } else if (x % 10 == 1) {
            return x + "st";
        } else if (x % 10 == 2) {
            return x + "nd";
        } else if (x % 10 == 3) {
            return x + "rd";
        } else {
            return x + "th";
        }
    }

}

```

3、有兴趣有能力的做，不用写本子上

编写 Applet 小程序实现 ```Runnable``` 接口，通过多线程实现在小程序窗口中不断的显示自然数：从 1 直到 100

```java
// File: OneToHundred.java

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class OneToHundred extends Applet implements ActionListener {

    public static final int SIZE = 10;
    int[][] doubleArray;
    TextArea outputbox;
    Button execute;

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        doubleArray = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                doubleArray[i][j] = 10 * i + j + 1;
            }
        }
        outputbox = new TextArea();
        execute = new Button("Execute");
        execute.addActionListener(this);
        add(outputbox);
        add(execute);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == execute) {
            outputbox.setText("");
            for (int i = 0; i < SIZE; i++) {
                new Thread(new PrintByThreads(doubleArray[i])).start();
            }
        }
    }

    class PrintByThreads implements Runnable {

        int[] intArray;

        public PrintByThreads(int... intArray) {
            this.intArray = new int[intArray.length];
            for (int i = 0; i < intArray.length; i++) {
                this.intArray[i] = intArray[i] + 0;
            }
        }

        @Override
        public void run() {
            for (int x : this.intArray) {
                try {
                    outputbox.append(x + "\n");
                    Thread.sleep((long) (Math.random() * 100L));
                } catch (InterruptedException e) {
                }
            }
        }

    }

}

```
