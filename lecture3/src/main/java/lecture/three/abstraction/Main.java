package lecture.three.abstraction;


// Абстрактный класс — частично реализованный шаблон
abstract class Transport {
    protected String name;

    public Transport(String name) {
        this.name = name;
    }

    // Абстрактный метод — должен быть реализован в подклассах
    public abstract void move();

    // Обычный метод — можно использовать сразу
    public void stop() {
        System.out.println(name + " остановился.");
    }
}

// Подкласс реализует абстрактный метод
class Car extends Transport {

    public Car(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(name + " едет по дороге.");
    }
}

// Интерфейс — альтернативный способ абстракции
interface Powered {
    void powerOn();
}

// Класс, реализующий интерфейс
class ElectricScooter extends Transport implements Powered {

    public ElectricScooter(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(name + " движется по велодорожке.");
    }

    @Override
    public void powerOn() {
        System.out.println(name + " включён.");
    }
}

// Главный класс для демонстрации абстракции
public class Main {

    public static void main(String[] args) {
        // Используем абстрактный тип ссылки
        Transport t1 = new Car("Лада");
        Transport t2 = new ElectricScooter("Xiaomi");

        t1.move();
        t1.stop();

        t2.move();
        t2.stop();

        // Используем интерфейс
        Powered p = new ElectricScooter("Ninebot");
        p.powerOn();
    }
}

