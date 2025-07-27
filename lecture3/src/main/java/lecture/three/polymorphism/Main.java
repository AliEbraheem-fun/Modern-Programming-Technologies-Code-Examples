package lecture.three.polymorphism;


// Интерфейс, реализуемый разными классами
interface Shape {
    void draw();  // общий метод
}

// Первый класс реализует метод
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуется круг");
    }

    // Перегрузка метода draw
    public void draw(String color) {
        System.out.println("Рисуется круг цвета: " + color);
    }
}

// Второй класс реализует метод по-своему
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуется прямоугольник");
    }
}

// Класс для демонстрации instanceof, upcasting и downcasting
public class Main {

    public static void main(String[] args) {
        // Полиморфизм времени выполнения: один интерфейс — разное поведение
        Shape s1 = new Circle();     // Восходящее преобразование (Upcasting)
        Shape s2 = new Rectangle();

        s1.draw();  // вызывает реализацию круга
        s2.draw();  // вызывает реализацию прямоугольника

        // Перегрузка метода (compile-time polymorphism)
        Circle c = new Circle();
        c.draw();               // базовый метод
        c.draw("красный");      // перегруженный метод

        // instanceof и downcasting
        if (s1 instanceof Circle) {
            Circle realCircle = (Circle) s1;  // Нисходящее преобразование (Downcasting)
            realCircle.draw("синий");
        }

        // Пример возможной ошибки при downcasting
        if (s2 instanceof Circle) {
            // Эта ветка не выполнится, поэтому исключения не будет
            Circle notACircle = (Circle) s2;
        }
    }
}
