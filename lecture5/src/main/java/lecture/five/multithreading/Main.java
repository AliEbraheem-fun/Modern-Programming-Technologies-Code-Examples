package lecture.five.multithreading;


public class Main {

    public static void main(String[] args) throws Exception {
        ThreadCreation.demo();
        ThreadMethods.demo();
        SyncDemo.demo();
        WaitNotifyDemo.demo();
        ThreadStateDemo.demo();
    }

    // 1. Создание потоков
    static class ThreadCreation {
        public static void demo() {
            System.out.println("=== Thread Creation ===");

            Thread t1 = new MyThread();
            t1.start();

            Runnable task = new MyRunnable();
            Thread t2 = new Thread(task);
            t2.start();

            Thread t3 = new Thread(() -> System.out.println("Поток через лямбду"));
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException ignored) {}
        }

        static class MyThread extends Thread {
            public void run() {
                System.out.println("Поток через MyThread");
            }
        }

        static class MyRunnable implements Runnable {
            public void run() {
                System.out.println("Поток через MyRunnable");
            }
        }
    }

    // 2. Основные методы потока
    static class ThreadMethods {
        public static void demo() throws InterruptedException {
            System.out.println("\n=== Thread Methods ===");

            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Выполнение потока");
                } catch (InterruptedException e) {
                    System.out.println("Поток прерван");
                }
            });

            t.start();
            t.join();
            System.out.println("Главный поток завершён");
        }
    }

    // 3. Синхронизация
    static class SyncDemo {
        private int counter = 0;

        public synchronized void increment() {
            counter++;
        }

        public static void demo() throws InterruptedException {
            System.out.println("\n=== Синхронизация ===");
            SyncDemo demo = new SyncDemo();

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) demo.increment();
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) demo.increment();
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("Счётчик: " + demo.counter);
        }
    }

    // 4. wait / notify
    static class WaitNotifyDemo {
        private static final Object lock = new Object();
        private static boolean ready = false;

        public static void demo() throws InterruptedException {
            System.out.println("\n=== Wait / Notify ===");

            Thread producer = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("Producer: работа...");
                    ready = true;
                    lock.notify();
                }
            });

            Thread consumer = new Thread(() -> {
                synchronized (lock) {
                    while (!ready) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consumer: получил сигнал");
                }
            });

            consumer.start();
            Thread.sleep(500);
            producer.start();

            producer.join();
            consumer.join();
        }
    }

    // 5. Состояния потока
    static class ThreadStateDemo {
        public static void demo() throws InterruptedException {
            System.out.println("\n=== Состояния потока ===");

            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}
            });

            System.out.println("Состояние (NEW): " + t.getState());
            t.start();
            Thread.sleep(100);
            System.out.println("Состояние (RUNNABLE): " + t.getState());
            t.join();
            System.out.println("Состояние (TERMINATED): " + t.getState());
        }
    }
}
