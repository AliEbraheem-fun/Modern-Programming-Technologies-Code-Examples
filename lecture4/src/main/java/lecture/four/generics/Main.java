package lecture.four.generics;

import java.util.*;

// 1. Обобщённый класс
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

// 2. Обобщённый интерфейс
interface Transformer<T> {
    T transform(T input);
}

class UpperCaseTransformer implements Transformer<String> {
    public String transform(String input) {
        return input.toUpperCase();
    }
}

// 3. Ограниченные параметры (extends)
class NumberBox<T extends Number> {
    private T number;

    public NumberBox(T number) {
        this.number = number;
    }

    public double doubleValue() {
        return number.doubleValue();
    }
}

// 4. Наследование с конкретизацией и сохранением типа
class StringBox extends Box<String> {}                 // Конкретизация
class GenericBox<T> extends Box<T> {}                 // Сохранение типа

// 5. Type Erasure (демонстрация ограничения)
class TypeErasureDemo<T> {
    public void check(Object obj) {
        // if (obj instanceof T) {} — Ошибка: нельзя использовать instanceof с параметром типа
        System.out.println("Тип T стёрт до Object на этапе исполнения");
    }
}

// 6. Джокеры — ? / ? extends T / ? super T
class WildcardDemo {
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println("Элемент: " + obj);
        }
    }

    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // 1. Обобщённый класс
        Box<String> strBox = new Box<>();
        strBox.set("Привет");
        System.out.println("Box: " + strBox.get());

        // 2. Обобщённый интерфейс
        Transformer<String> transformer = new UpperCaseTransformer();
        System.out.println("Transform: " + transformer.transform("hello"));

        // 3. Ограниченные параметры
        NumberBox<Integer> nb = new NumberBox<>(123);
        System.out.println("Double value: " + nb.doubleValue());

        // 4. Наследование обобщённых классов
        StringBox sb = new StringBox();
        sb.set("Generic Java");
        System.out.println("StringBox: " + sb.get());

        GenericBox<Double> gb = new GenericBox<>();
        gb.set(3.14);
        System.out.println("GenericBox<Double>: " + gb.get());

        // 5. Type Erasure
        TypeErasureDemo<String> ted = new TypeErasureDemo<>();
        ted.check("Test");

        // 6. Джокеры
        List<String> words = Arrays.asList("one", "two", "three");
        WildcardDemo.printList(words);

        List<Integer> nums = Arrays.asList(1, 2, 3);
        System.out.println("Сумма: " + WildcardDemo.sumNumbers(nums));

        List<Number> numberList = new ArrayList<>();
        WildcardDemo.addIntegers(numberList);
        System.out.println("После добавления целых чисел: " + numberList);

        // 7. Массивы обобщённых типов
        genericArrayExample();
    }

    @SuppressWarnings("unchecked")
    public static void genericArrayExample() {
        Box<String>[] boxes = (Box<String>[]) new Box[3]; // безопасный каст с подавлением предупреждения
        boxes[0] = new Box<>();
        boxes[0].set("Java");
        System.out.println("Массив обобщённого типа: " + boxes[0].get());
    }
}
