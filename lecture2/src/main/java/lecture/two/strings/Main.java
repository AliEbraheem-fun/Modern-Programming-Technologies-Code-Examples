package lecture.two.strings;

// Для использования STR нужно компилировать с --enable-preview и использовать JDK 21+
// import java.util.StringTemplate;
// import static java.util.StringTemplate.STR;
// import static java.util.FormatProcessor.FMT;

public class Main {
    public static void main(String[] args) {

        // === Способы объявления строк ===

        // 1. Через строковый литерал
        String str1 = "Hello";

        // 2. Через new (создаёт новый объект в heap, не из string pool)
        String str2 = new String("World");

        // 3. Из массива символов
        char[] charArray = {'J', 'a', 'v', 'a'};
        String str3 = new String(charArray);

        // 4. Из части массива символов
        String str4 = new String(charArray, 1, 2); // "av"

        // 5. Из массива байтов
        byte[] byteArray = {65, 66, 67}; // A, B, C
        String str5 = new String(byteArray);

        // 6. Через StringBuilder
        String str6 = new StringBuilder("Builder String").toString();

        // 7. Многострочная строка (text block) — с Java 15
        String str7 = """
                      Это многострочная строка.
                      Она может содержать переносы строк,
                      табуляции и кавычки "внутри текста".
                      """;

        System.out.println("=== Строковые значения ===");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str6);
        System.out.println("\nText Block:\n" + str7);

        // === 🛠️ Методы класса String ===

        String base = "  Java Programming Language  ";
        System.out.println("\n=== Методы String ===");
        System.out.println("length(): " + base.length());
        System.out.println("charAt(5): " + base.charAt(5));
        System.out.println("toUpperCase(): " + base.toUpperCase());
        System.out.println("toLowerCase(): " + base.toLowerCase());
        System.out.println("trim(): [" + base.trim() + "]");
        System.out.println("substring(2, 6): " + base.substring(2, 6));
        System.out.println("contains(\"Program\"): " + base.contains("Program"));
        System.out.println("indexOf(\"a\"): " + base.indexOf("a"));
        System.out.println("lastIndexOf(\"a\"): " + base.lastIndexOf("a"));
        System.out.println("replace(\"Java\", \"Kotlin\"): " + base.replace("Java", "Kotlin"));
        System.out.println("startsWith(\"  Java\"): " + base.startsWith("  Java"));
        System.out.println("endsWith(\"e  \"): " + base.endsWith("e  "));
        System.out.println("equals(\"test\"): " + base.equals("test"));
        System.out.println("equalsIgnoreCase(...): " +
                base.equalsIgnoreCase("  java programming language  "));
        System.out.println("isEmpty(): " + base.isEmpty());
        System.out.println("isBlank(): " + base.isBlank());  // Java 11+
        System.out.println("repeat(2): " + "Hi ".repeat(2)); // Java 11+
        System.out.println("join(): " + String.join(" - ", "Java", "Python", "C++"));

        // === Преобразование строки в массивы ===
        char[] chars = base.toCharArray();
        byte[] bytes = base.getBytes();
        System.out.println("toCharArray(): " + java.util.Arrays.toString(chars));
        System.out.println("getBytes(): " + java.util.Arrays.toString(bytes));

        // === Сравнение ссылок и содержимого ===
        String s1 = "Test";
        String s2 = "Test";
        String s3 = new String("Test");

        System.out.println("\n=== Сравнение строк ===");
        System.out.println("s1 == s2: " + (s1 == s2));               // true
        System.out.println("s1 == s3: " + (s1 == s3));               // false
        System.out.println("s1.equals(s3): " + s1.equals(s3));       // true

        // === Шаблоны строк STR (Java 21+) ===
        /*
        String name = "Alice";
        int age = 30;

        // Интерполяция строк (предварительная фича Java 21+)
        String greeting = STR."Hello, \{name}! You are \{age} years old.";
        System.out.println("\nSTR-интерполяция:\n" + greeting);

        // Использование FMT для printf-подобного вывода
        String formatted = FMT."%-10s: \{name}\n%-10s: \{age}"("Name", "Age");
        System.out.println("\nФорматированный вывод:\n" + formatted);
        */
    }
}
