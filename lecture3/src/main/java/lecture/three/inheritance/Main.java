package lecture.three.inheritance;


// Родительский класс
class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    // Метод, который можно переопределить
    public void speak() {
        System.out.println(name + " издаёт звук.");
    }

    // Метод final — нельзя переопределить
    public final void sleep() {
        System.out.println(name + " спит.");
    }
}

// Интерфейс с методом по умолчанию
interface Movable {
    void move();

    default void description() {
        System.out.println("Этот объект может двигаться.");
    }
}

// Абстрактный класс
abstract class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    // Абстрактный метод
    public abstract void fly();
}

// Подкласс с использованием extends и implements
class Dog extends Animal implements Movable {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " лает.");
    }

    @Override
    public void move() {
        System.out.println(name + " бежит.");
    }
}

// Запечатанный класс (Java 17+)
sealed class Vehicle permits Car, Bicycle {
    public void start() {
        System.out.println("Транспортное средство начинает движение.");
    }
}

// Разрешённый подкласс запечатанного класса
final class Car extends Vehicle {
    // final — нельзя наследовать от Car
}

// Подкласс, который разрешает дальнейшее наследование
non-sealed class Bicycle extends Vehicle {
}

// Класс-наследник от non-sealed
class MountainBike extends Bicycle {
}

// Главный класс для демонстрации
public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Бобик");
        dog.speak();          // Переопределённый метод
        dog.move();           // Метод интерфейса
        dog.sleep();          // final метод родителя
        dog.description();    // default метод интерфейса

        Vehicle v = new Car();  // Восходящее преобразование
        v.start();

        // Использование sealed/non-sealed
        Vehicle bike = new MountainBike();
        bike.start();
    }
}

