package lecture.two.anonymous;

import javax.swing.*;
import java.awt.event.*;

// === Интерфейс для пользовательского примера ===
interface Greeter {
    void greet();
}

public class Main {

    public static void main(String[] args) {

        // === Пример 1: Анонимный класс, реализующий интерфейс Runnable ===
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Запущен Runnable из анонимного класса");
            }
        };
        task.run();

        // === Пример 2: Анонимный класс, расширяющий Thread ===
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Анонимный подкласс Thread работает");
            }
        };
        t.start();

        // === Пример 3: Анонимный класс, реализующий пользовательский интерфейс ===
        Greeter g = new Greeter() {
            @Override
            public void greet() {
                System.out.println("Привет из анонимного класса Greeter");
            }
        };
        g.greet();

        // === Пример 4: Анонимный класс в GUI (Swing) ===
        JFrame frame = new JFrame("Пример с кнопкой");
        JButton button = new JButton("Нажми меня");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Кнопка нажата! (через анонимный класс)");
            }
        });

        frame.getContentPane().add(button);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
