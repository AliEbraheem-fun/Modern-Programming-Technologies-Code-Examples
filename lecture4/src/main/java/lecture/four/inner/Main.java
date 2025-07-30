package lecture.four.inner;// Примеры вложенных классов и интерфейсов

// 1. Нестатический вложенный класс (inner class)
class Outer {
    private String secret = "секрет внешнего класса";

    public class Inner {
        public void showSecret() {
            System.out.println("Доступ из Inner: " + secret);
        }
    }

    public static void runInnerExample() {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.showSecret();
    }
}

// 2. Статический вложенный класс (static nested class)
class Container {
    static int staticValue = 42;

    static class StaticNested {
        void printInfo() {
            System.out.println("Статическое значение: " + staticValue);
        }
    }

    public static void runStaticNestedExample() {
        Container.StaticNested nested = new Container.StaticNested();
        nested.printInfo();
    }
}

// 3. Вложенный интерфейс внутри класса
class Machine {
    public interface PowerSwitch {
        void turnOn();
        void turnOff();
    }

    public static class Engine implements PowerSwitch {
        public void turnOn() {
            System.out.println("Двигатель включён");
        }

        public void turnOff() {
            System.out.println("Двигатель выключен");
        }
    }

    public static void runInterfaceInClassExample() {
        PowerSwitch ps = new Engine();
        ps.turnOn();
        ps.turnOff();
    }
}

// 4. Вложенный интерфейс внутри интерфейса
interface Device {
    void start();

    interface Status {
        int READY = 1;
        int ERROR = -1;
    }
}

class Printer implements Device {
    public void start() {
        System.out.println("Принтер запущен. Статус: " + Status.READY);
    }

    public static void runInterfaceInInterfaceExample() {
        new Printer().start();
    }
}

// 5. Наследование вложенного нестатического класса
class OuterBase {
    public class Inner {
        public void greet() {
            System.out.println("Привет из Inner");
        }
    }
}

class SubInner extends OuterBase.Inner {
    public SubInner(OuterBase outer) {
        outer.super();
    }

    @Override
    public void greet() {
        System.out.println("Привет из SubInner");
    }

    public static void runSubInnerExample() {
        OuterBase outer = new OuterBase();
        SubInner si = new SubInner(outer);
        si.greet();
    }
}

// 6. Реализация вложенного интерфейса внешним классом
class Robot {
    interface Controller {
        void execute();
    }

    public static class Brain implements Controller {
        public void execute() {
            System.out.println("Робот выполняет команду");
        }
    }

    public static void runRobotExample() {
        Controller c = new Brain();
        c.execute();
    }
}

// Main-класс для запуска всех примеров
public class Main {
    public static void main(String[] args) {
        System.out.println("1. Inner class:");
        Outer.runInnerExample();

        System.out.println("\n2. Static nested class:");
        Container.runStaticNestedExample();

        System.out.println("\n3. Interface inside class:");
        Machine.runInterfaceInClassExample();

        System.out.println("\n4. Interface inside interface:");
        Printer.runInterfaceInInterfaceExample();

        System.out.println("\n5. Inherited inner class:");
        SubInner.runSubInnerExample();

        System.out.println("\n6. Inner interface implemented externally:");
        Robot.runRobotExample();
    }
}
