package lecture.two.arrays;
public class Main {
    public static void main(String[] args) {

        // === 1. C-подобный стиль объявления ===
        int numbers[] = new int[5];  // массив из 5 элементов типа int
        numbers[0] = 10;             // присваивание значений по индексу
        numbers[1] = 20;

        System.out.println("1. C-подобное объявление массива:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

        // === 2. Java-стиль объявления ===
        String[] names = new String[] {"Alice", "Bob", "Charlie"};

        System.out.println("\n\n2. Java-стиль (объявление + инициализация):");
        for (String name : names) {
            System.out.print(name + " ");
        }

        // === 3. Упрощённая инициализация без new (только при объявлении) ===
        double[] prices = {19.99, 9.99, 14.50};

        System.out.println("\n\n3. Упрощённая инициализация:");
        for (double price : prices) {
            System.out.print(price + " ");
        }

        // === 4. Массив объектов (например, String) ===
        String[] fruits = new String[3];
        fruits[0] = "Apple";
        fruits[1] = "Banana";
        fruits[2] = "Orange";

        System.out.println("\n\n4. Массив объектов:");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }

        // === 5. Двумерный массив (прямоугольный) ===
        int[][] grid = {
                {1, 2},
                {3, 4}
        };

        System.out.println("\n\n5. Двумерный массив:");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        // === 6. Jagged (зубчатый) массив ===
        int[][] jagged = new int[3][];
        jagged[0] = new int[] {1, 2};
        jagged[1] = new int[] {3, 4, 5};
        jagged[2] = new int[] {6};

        System.out.println("\n6. Зубчатый массив:");
        for (int i = 0; i < jagged.length; i++) {
            System.out.print("Строка " + i + ": ");
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + " ");
            }
            System.out.println();
        }

        // === 7. Объявление массива без инициализации ===
        char[] letters;             // только объявление
        letters = new char[] {'A', 'B', 'C'};

        System.out.println("\n7. Объявление массива без инициализации:");
        for (char ch : letters) {
            System.out.print(ch + " ");
        }
    }
}
