package lecture.two.methodreferences;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        // 1. Ссылка на static-метод
        // Пример: Math::max
        BiFunction<Integer, Integer, Integer> maxFunc = Math::max;
        System.out.println("1. Max of 3 and 5: " + maxFunc.apply(3, 5));

        // 2. Ссылка на метод экземпляра конкретного объекта
        // Пример: System.out::println
        Consumer<String> printer = System.out::println;
        printer.accept("2. Hello from method reference!");

        // 3. Ссылка на метод экземпляра по типу
        // Пример: String::toUpperCase
        List<String> names = Arrays.asList("alice", "bob", "carol");
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("3. Uppercased names: " + upper);

        // 4. Ссылка на конструктор
        // Пример: ArrayList::new
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("4. Added using constructor reference");
        newList.forEach(System.out::println);

        // Дополнительно: ссылка на нестатический метод с аргументами
        // Пример: String::compareTo
        BiFunction<String, String, Integer> comparator = String::compareTo;
        System.out.println("5. Compare \"apple\" to \"banana\": " + comparator.apply("apple", "banana"));

        // Ссылка на конструктор с аргументами
        Function<String, Person> personFactory = Person::new;
        Person p = personFactory.apply("Alice");
        p.sayHello();
    }

    // Класс для демонстрации конструктора
    static class Person {
        private final String name;

        Person(String name) {
            this.name = name;
        }

        void sayHello() {
            System.out.println("Привет, меня зовут " + name);
        }
    }
}

