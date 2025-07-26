package lecture.one.operators;

public class Main {
    public static void main(String[] args) {

        // Арифметические операторы
        int a = 10;
        int b = 3;

        int sum = a + b;      // сложение
        int diff = a - b;     // вычитание
        int prod = a * b;     // умножение
        int quot = a / b;     // деление (результат будет целым числом)
        int mod = a % b;      // остаток от деления

        System.out.println("Арифметические операторы:");
        System.out.println("Сложение: " + sum);
        System.out.println("Вычитание: " + diff);
        System.out.println("Умножение: " + prod);
        System.out.println("Деление: " + quot);
        System.out.println("Остаток: " + mod);
        System.out.println();

        // Операторы сравнения
        System.out.println("Операторы сравнения:");
        System.out.println("a == b: " + (a == b));   // равно
        System.out.println("a != b: " + (a != b));   // не равно
        System.out.println("a > b: " + (a > b));     // больше
        System.out.println("a < b: " + (a < b));     // меньше
        System.out.println("a >= b: " + (a >= b));   // больше или равно
        System.out.println("a <= b: " + (a <= b));   // меньше или равно
        System.out.println();

        // Логические операторы
        boolean x = true;
        boolean y = false;

        System.out.println("Логические операторы:");
        System.out.println("x && y: " + (x && y));   // логическое И
        System.out.println("x || y: " + (x || y));   // логическое ИЛИ
        System.out.println("!x: " + (!x));           // логическое НЕ
        System.out.println();

        // Побитовые операторы
        int m = 5;   // 0101
        int n = 3;   // 0011

        System.out.println("Побитовые операторы:");
        System.out.println("m & n: " + (m & n));       // побитовое И
        System.out.println("m | n: " + (m | n));       // побитовое ИЛИ
        System.out.println("m ^ n: " + (m ^ n));       // побитовое исключающее ИЛИ
        System.out.println("~m: " + (~m));             // побитовое НЕ (инверсия)
        System.out.println("m << 1: " + (m << 1));     // сдвиг влево (умножение на 2)
        System.out.println("m >> 1: " + (m >> 1));     // арифметический сдвиг вправо (деление на 2)
        System.out.println("m >>> 1: " + (m >>> 1));   // беззнаковый сдвиг вправо
        System.out.println();

        // Операторы присваивания
        int val = 8;

        System.out.println("Операторы присваивания:");
        val += 2;  // val = val + 2
        System.out.println("+= : " + val);
        val -= 1;
        System.out.println("-= : " + val);
        val *= 2;
        System.out.println("*= : " + val);
        val /= 3;
        System.out.println("/= : " + val);
        val %= 3;
        System.out.println("%= : " + val);
        System.out.println();

        // Унарные операторы
        int z = 5;

        System.out.println("Унарные операторы:");
        System.out.println("++z: " + (++z));   // префиксный инкремент
        System.out.println("z++: " + (z++));   // постфиксный инкремент
        System.out.println("Текущее z: " + z);
        System.out.println("--z: " + (--z));   // префиксный декремент
        System.out.println("z--: " + (z--));   // постфиксный декремент
        System.out.println("Текущее z: " + z);
        System.out.println("-z: " + (-z));     // унарный минус
        System.out.println("+z: " + (+z));     // унарный плюс (не меняет значение)
    }
}
