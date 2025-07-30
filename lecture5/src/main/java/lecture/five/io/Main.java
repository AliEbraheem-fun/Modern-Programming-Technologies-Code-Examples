package lecture.five.io;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        byteStreamDemo();
        charStreamDemo();
        bufferedDemo();
        consoleIO(); // Пример со стандартным вводом
        tryWithResourcesDemo();
    }

    // 1. Чтение и запись байтов (InputStream / OutputStream)
    public static void byteStreamDemo() throws IOException {
        System.out.println("=== Byte Stream Demo ===");

        try (FileOutputStream fos = new FileOutputStream("bytes.bin")) {
            fos.write(new byte[]{1, 2, 3, 4});
        }

        try (FileInputStream fis = new FileInputStream("bytes.bin")) {
            int b;
            System.out.print("Прочитанные байты: ");
            while ((b = fis.read()) != -1) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    // 2. Чтение и запись текста (Reader / Writer)
    public static void charStreamDemo() throws IOException {
        System.out.println("\n=== Character Stream Demo ===");

        try (FileWriter writer = new FileWriter("text.txt")) {
            writer.write("Привет, мир!");
        }

        try (FileReader reader = new FileReader("text.txt")) {
            int c;
            System.out.print("Прочитанный текст: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        }
    }

    // 3. Буферизованные потоки
    public static void bufferedDemo() throws IOException {
        System.out.println("\n=== Buffered Stream Demo ===");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"))) {
            bw.write("Первая строка");
            bw.newLine();
            bw.write("Вторая строка");
        }

        try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"))) {
            String line;
            System.out.println("Содержимое файла:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // 4. Стандартный ввод и вывод
    public static void consoleIO() {
        System.out.println("\n=== Console I/O Demo ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите своё имя: ");
        String name = scanner.nextLine();

        System.out.println("Привет, " + name + "!");
    }

    // 5. Try-with-resources
    public static void tryWithResourcesDemo() {
        System.out.println("\n=== Try-with-Resources Demo ===");

        try (BufferedReader reader = new BufferedReader(new FileReader("buffered.txt"))) {
            System.out.println("Первая строка из buffered.txt: " + reader.readLine());
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
