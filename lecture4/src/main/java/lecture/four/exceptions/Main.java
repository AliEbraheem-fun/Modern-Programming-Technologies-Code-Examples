package lecture.four.exceptions;

import java.io.*;

// 1. Checked Exception (требует обработки)
class FileProcessor {
    public void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        System.out.println("Файл прочитан: " + reader.readLine());
        reader.close();
    }
}

// 2. Unchecked Exception (можно не обрабатывать явно)
class Divider {
    public int divide(int a, int b) {
        return a / b; // может вызвать ArithmeticException
    }
}

// 3. Пользовательское (checked) исключение
class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}

class Voter {
    public void register(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("Возраст должен быть 18+");
        System.out.println("Регистрация успешна!");
    }
}

// 4. Демонстрация try-catch-finally, throw и throws
class ExceptionFlowDemo {
    public void riskyMethod() throws IOException {
        throw new IOException("Ошибка ввода-вывода");
    }

    public void showFlow() {
        try {
            riskyMethod();
        } catch (IOException e) {
            System.out.println("Обработка IOException: " + e.getMessage());
        } finally {
            System.out.println("Блок finally выполнен");
        }
    }
}

// 5. try-with-resources (начиная с Java 7)
class ResourceDemo {
    public void readFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            System.out.println("Первая строка: " + br.readLine());
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}

// 6. Error (критическая ошибка среды выполнения)
class ErrorExample {
    public void triggerError() {
        throw new StackOverflowError("Пример StackOverflowError");
    }
}

// 7. Несколько catch-блоков
class MultipleCatchDemo {
    public void test(String filename) {
        try {
            int result = 10 / 0; // ArithmeticException
            BufferedReader br = new BufferedReader(new FileReader(filename)); // FileNotFoundException
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Общее исключение: " + e.getMessage());
        }
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        System.out.println("1. Checked exception:");
        FileProcessor fp = new FileProcessor();
        try {
            fp.readFile("example.txt"); // файл можно создать для теста
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        System.out.println("\n2. Unchecked exception:");
        Divider d = new Divider();
        try {
            System.out.println("Результат: " + d.divide(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n3. Custom checked exception:");
        Voter voter = new Voter();
        try {
            voter.register(16);
        } catch (InvalidAgeException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        }

        System.out.println("\n4. try-catch-finally и throw/throws:");
        new ExceptionFlowDemo().showFlow();

        System.out.println("\n5. try-with-resources:");
        new ResourceDemo().readFromFile("example.txt"); // пример файла

        System.out.println("\n6. Multiple catch blocks:");
        new MultipleCatchDemo().test("notfound.txt");

        System.out.println("\n7. Error example (необязательно запускать):");
//        new ErrorExample().triggerError(); // раскомментировать — вызывает StackOverflowError
    }
}
