package lecture.two.local;

public class OuterClass {
    private String instanceField = "Приватное поле внешнего класса";
    static String staticField = "Статическое поле внешнего класса";

    public void methodWithLocalClass() {
        final int finalLocalVar = 10;
        int effectivelyFinalVar = 20;

        // Локальный класс внутри нестатического метода
        class LocalHelper {
            // static int x = 5; // Ошибка: нельзя объявлять static
            static final String CONSTANT = "OK"; // static final константа

            void printInfo() {
                // Доступ к private полю внешнего класса
                System.out.println("1. instanceField = " + instanceField);

                // Доступ к final / effectively final переменным
                System.out.println("2. finalLocalVar = " + finalLocalVar);
                System.out.println("3. effectivelyFinalVar = " + effectivelyFinalVar);

                // Конфликт имён и явное обращение к внешнему классу
                String instanceField = "Локальное поле с тем же именем";
                System.out.println("4. Локальное поле: " + instanceField);
                System.out.println("5. Внешнее поле: " + OuterClass.this.instanceField);
            }
        }

        // Используем локальный класс
        LocalHelper helper = new LocalHelper();
        helper.printInfo();
    }

    public static void staticMethodWithLocalClass() {
        final int local = 42;

        // Локальный класс внутри статического метода
        class StaticContextClass {
            void show() {
                System.out.println("6. local = " + local);

                // Доступ только к static полям внешнего класса
                System.out.println("7. staticField = " + staticField);

                // Нельзя обращаться к instanceField
                // System.out.println(instanceField); // Ошибка
            }
        }

        StaticContextClass obj = new StaticContextClass();
        obj.show();
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.methodWithLocalClass();

        OuterClass.staticMethodWithLocalClass();
    }
}

