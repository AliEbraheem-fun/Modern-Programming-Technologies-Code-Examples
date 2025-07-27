package lecture.two.abstrclasses;

// Интерфейс с одним абстрактным методом
interface Trainable {
    void train(); // абстрактный метод
    default void praise() {
        System.out.println("Молодец!");
    }
}

// Абстрактный класс — реализует интерфейс, но не реализует train(),
// поэтому остаётся абстрактным
abstract class Animal implements Trainable {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void sleep() {
        System.out.println(name + " спит.");
    }

    // Абстрактные методы — должны быть реализованы в наследниках
    public abstract void makeSound();
    protected abstract void move();

    // Метод praise() из интерфейса уже реализован как default, поэтому не требуется переопределять
}

// Подкласс — реализует все абстрактные методы, включая метод из интерфейса
class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " говорит: Гав-гав!");
    }

    @Override
    protected void move() {
        System.out.println(name + " бегает на четырёх лапах.");
    }

    // Реализация метода из интерфейса
    @Override
    public void train() {
        System.out.println(name + " выполняет команду 'сидеть'.");
    }
}

// Ещё один подкласс
class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " поёт: Чирик-чирик!");
    }

    @Override
    protected void move() {
        System.out.println(name + " летает в небе.");
    }

    @Override
    public void train() {
        System.out.println(name + " учится говорить слова.");
    }
}

// Главный класс
public class Main {

    public static void main(String[] args) {

        // Animal animal = new Animal("Животное"); // Нельзя создать экземпляр абстрактного класса

        Animal dog = new Dog("Шарик");
        Animal bird = new Bird("Кеша");

        dog.sleep();
        dog.makeSound();
        dog.move();
        dog.train();
        dog.praise(); // вызов default-метода интерфейса

        System.out.println();

        bird.sleep();
        bird.makeSound();
        bird.move();
        bird.train();
        bird.praise();
    }
}
