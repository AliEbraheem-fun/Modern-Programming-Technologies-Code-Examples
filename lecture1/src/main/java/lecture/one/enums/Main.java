package lecture.one.enums;

// Простой enum без логики — только фиксированный набор направлений
enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST
}

// Расширенный enum с полями, методами, конструктором и абстрактным методом
enum Operation {
    ADD("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACT("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            if (y == 0) throw new ArithmeticException("Division by zero");
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return name() + " (" + symbol + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        double a = 12;
        double b = 4;

        System.out.println("=== Перебор всех операций (values) ===");
        for (Operation op : Operation.values()) {
            System.out.printf("%s: %.2f %s %.2f = %.2f%n",
                    op.name(), a, op.getSymbol(), b, op.apply(a, b));
        }

        System.out.println("\n=== Получение операции по имени (valueOf) ===");
        Operation selectedOp = Operation.valueOf("ADD");
        System.out.println("Выбрана операция: " + selectedOp);

        System.out.println("\n=== Порядковый номер операции (ordinal) ===");
        System.out.println("SUBTRACT.ordinal() = " + Operation.SUBTRACT.ordinal());

        System.out.println("\n=== Пример switch с Operation ===");
        switch (selectedOp) {
            case ADD -> System.out.println("Это сложение.");
            case SUBTRACT -> System.out.println("Это вычитание.");
            case MULTIPLY -> System.out.println("Это умножение.");
            case DIVIDE -> System.out.println("Это деление.");
        }

        System.out.println("\n=== Перебор направлений (Direction.values) ===");
        for (Direction dir : Direction.values()) {
            System.out.println("Направление: " + dir);
        }

        System.out.println("\n=== Switch по Direction ===");
        Direction d = Direction.NORTH;
        switch (d) {
            case NORTH -> System.out.println("Идём на север.");
            case SOUTH -> System.out.println("Идём на юг.");
            case EAST  -> System.out.println("Идём на восток.");
            case WEST  -> System.out.println("Идём на запад.");
        }

        System.out.println("\n=== Direction.valueOf(\"EAST\") ===");
        Direction east = Direction.valueOf("EAST");
        System.out.println("Получено: " + east);
    }
}
