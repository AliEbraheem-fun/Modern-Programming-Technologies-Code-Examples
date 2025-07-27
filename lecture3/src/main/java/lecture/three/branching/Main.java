package lecture.three.branching;

public class Main {
    // 1. Простой if и if-else
    public static void simpleIfElse(int number) {
        if (number > 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Неположительное число");
        }
    }

    // 2. Цепочка if-else-if
    public static void ifElseIf(int score) {
        if (score >= 90) {
            System.out.println("Оценка A");
        } else if (score >= 75) {
            System.out.println("Оценка B");
        } else if (score >= 60) {
            System.out.println("Оценка C");
        } else {
            System.out.println("Неудовлетворительно");
        }
    }

    // 3. Тернарный оператор
    public static void ternaryOperator(int age) {
        String result = (age >= 18) ? "Совершеннолетний" : "Несовершеннолетний";
        System.out.println(result);
    }

    // 4. Классический switch
    public static void classicSwitch(int day) {
        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
            case 3:
                System.out.println("Вторник или среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            default:
                System.out.println("Другой день");
                break;
        }
    }

    // 5. Switch с оператором -> (Java 14+)
    public static void arrowSwitch(int level) {
        switch (level) {
            case 1 -> System.out.println("Начинающий");
            case 2, 3 -> System.out.println("Средний уровень");
            case 4 -> System.out.println("Продвинутый");
            default -> System.out.println("Неизвестный уровень");
        }
    }

    // 6. Switch с yield (Java 14+)
    public static void switchWithYield(String code) {
        String result = switch (code) {
            case "A" -> {
                System.out.println("Обработка кода A");
                yield "Результат A";
            }
            case "B", "C" -> {
                yield "Результат B или C";
            }
            default -> {
                yield "Неизвестный код";
            }
        };
        System.out.println(result);
    }

    // 7. Switch с сопоставлением по типу (Java 17+)
    public static void patternSwitch(Object obj) {
        switch (obj) {
            case String s -> System.out.println("Это строка: " + s);
            case Integer i -> System.out.println("Это целое число: " + i);
            case null -> System.out.println("Это null");
            default -> System.out.println("Неизвестный тип");
        }
    }

    // 8. Switch с оператором when (Java 21+)
    public static void patternSwitchWithWhen(Object obj) {
        switch (obj) {
            case String s when s.length() > 5 -> System.out.println("Длинная строка: " + s);
            case String s -> System.out.println("Короткая строка: " + s);
            case Integer i when i > 100 -> System.out.println("Большое число: " + i);
            default -> System.out.println("Другое значение");
        }
    }

    // Точка входа в программу
    public static void main(String[] args) {
        simpleIfElse(5);
        ifElseIf(82);
        ternaryOperator(16);
        classicSwitch(2);
        arrowSwitch(3);
        switchWithYield("A");
        patternSwitch("Пример");
        patternSwitchWithWhen("Пример");
    }
}
