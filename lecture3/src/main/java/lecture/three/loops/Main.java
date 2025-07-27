package lecture.three.loops;

public class Main {
    // 1. Цикл while — выполняется, пока условие истинно
    public static void exampleWhile() {
        int i = 0;
        while (i < 5) {
            System.out.println("Итерация while: " + i);
            i++;
        }
    }

    // 2. Цикл do-while — выполняется хотя бы один раз
    public static void exampleDoWhile() {
        int i = 0;
        do {
            System.out.println("Итерация do-while: " + i);
            i++;
        } while (i < 5);
    }

    // 3. Классический цикл for
    public static void exampleFor() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Итерация for: " + i);
        }
    }

    // 4. Расширенный цикл for (foreach)
    public static void exampleForEach() {
        int[] numbers = {10, 20, 30, 40};
        for (int num : numbers) {
            System.out.println("Элемент массива: " + num);
        }
    }

    // 5. Прерывание цикла с помощью break
    public static void exampleBreak() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println("Прерывание цикла при i = 5");
                break;
            }
            System.out.println("Итерация: " + i);
        }
    }

    // 6. Пропуск итерации с помощью continue
    public static void exampleContinue() {
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                System.out.println("Пропущена итерация при i = 2");
                continue;
            }
            System.out.println("Итерация: " + i);
        }
    }

    // 7. Использование метки с break и continue
    public static void exampleLabeledLoops() {
        outerLoop:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Выход из внешнего цикла при i = 2, j = 2");
                    break outerLoop; // выход из внешнего цикла
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }

        System.out.println("Пример continue с меткой:");
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    continue outer; // переход к следующей итерации внешнего цикла
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
    }

    // Точка входа в программу
    public static void main(String[] args) {
        exampleWhile();
        exampleDoWhile();
        exampleFor();
        exampleForEach();
        exampleBreak();
        exampleContinue();
        exampleLabeledLoops();
    }
}
