package lecture.two.interfaces;

// Основной интерфейс, задающий поведение движущегося объекта
interface Movable {
    // Константа — максимальная скорость (public static final по умолчанию)
    int MAX_SPEED = 120;

    // Абстрактный метод (по умолчанию public abstract)
    void move();

    // Default-метод с реализацией (можно переопределить в классе)
    default void stop() {
        System.out.println("Объект остановлен.");
        log("Вызван метод stop()");
    }

    // Static-метод — вызывается на интерфейсе, а не на экземпляре
    static void info() {
        System.out.println("Интерфейс Movable определяет движение объектов.");
    }

    // Private-метод (доступен только внутри интерфейса, с Java 9)
    private void log(String message) {
        System.out.println("ЛОГ (Movable): " + message);
    }
}

// Второй интерфейс, демонстрирующий множественное наследование
interface Powered {
    void turnOn();

    default void batteryStatus() {
        System.out.println("Батарея заряжена на 80%");
    }
}

// Интерфейс, наследующий два других
interface SmartDevice extends Movable, Powered {
    void connectToWiFi();
}

// Класс, реализующий интерфейс SmartDevice (а значит и все унаследованные)
class Robot implements SmartDevice {

    // Реализация абстрактного метода из Movable
    @Override
    public void move() {
        System.out.println("Робот двигается вперёд.");
    }

    // Реализация метода из Powered
    @Override
    public void turnOn() {
        System.out.println("Робот включён.");
    }

    // Реализация метода SmartDevice
    @Override
    public void connectToWiFi() {
        System.out.println("Подключение к Wi-Fi выполнено.");
    }

    // Переопределение default-метода stop()
    @Override
    public void stop() {
        System.out.println("Робот остановлен (переопределённый метод).");
    }
}

// Главный класс с методом main
public class InterfaceDemo {
    public static void main(String[] args) {
        Robot robot = new Robot();

        robot.turnOn();           // Включение устройства
        robot.move();             // Движение
        robot.connectToWiFi();    // Подключение к сети
        robot.stop();             // Остановка (переопределённый default-метод)
        robot.batteryStatus();    // Вызов default-метода из Powered

        // Вызов static-метода интерфейса напрямую
        Movable.info();

        // Вывод значения константы из интерфейса
        System.out.println("Максимальная скорость: " + Movable.MAX_SPEED + " км/ч");
    }
}
