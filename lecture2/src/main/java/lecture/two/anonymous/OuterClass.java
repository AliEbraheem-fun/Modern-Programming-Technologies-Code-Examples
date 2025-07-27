package lecture.two.anonymous;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// === Интерфейс для пользовательского примера ===
interface Greeter {
    void greet();
}

public class OuterClass {

    private String outerField = "Приватное поле внешнего класса";

    public void demonstrateAnonymousClass() {
        final String localVar = "локальная переменная (final)";

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("1. outerField = " + outerField); // доступ к приватному полю
                System.out.println("2. localVar = " + localVar);     // доступ к final переменной

                String outerField = "локальное перекрывающее имя";
                System.out.println("3. Внешнее поле: " + OuterClass.this.outerField);
                System.out.println("4. Локальное поле: " + outerField);
            }
        };
        r.run();
    }

    public static void demonstrateInStaticContext() {
        final String staticLocal = "локальная static переменная";

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("5. staticLocal = " + staticLocal);

                // Недоступ к нестатическим полям внешнего класса
                // System.out.println(outerField); // ошибка
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        // === Пример 1: Анонимный класс, реализующий Runnable ===
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

        // === Пример 4: Анонимный класс внутри нестатического метода ===
        OuterClass outer = new OuterClass();
        outer.demonstrateAnonymousClass();

        // === Пример 5: Анонимный класс внутри static метода ===
        OuterClass.demonstrateInStaticContext();

        // === Пример 6: Анонимный класс в GUI (Swing) ===
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
