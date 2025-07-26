package lecture.two.lambdas;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // Runnable: no arguments, no return
        Runnable runner = () -> System.out.println("Running a thread using lambda");
        new Thread(runner).start();

        // Supplier: no arguments, returns value
        Supplier<String> stringSupplier = () -> "Supplied value";
        System.out.println("Supplier: " + stringSupplier.get());

        // Consumer: one argument, returns nothing
        Consumer<String> printer = str -> System.out.println("Consumed: " + str);
        printer.accept("Lambda Expression");

        // BiConsumer: two arguments, returns nothing
        BiConsumer<String, Integer> printKeyValue = (k, v) -> System.out.println(k + " = " + v);
        printKeyValue.accept("Age", 30);

        // Predicate: one argument, returns boolean
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        System.out.println("Is 'Java' not empty? " + isNotEmpty.test("Java"));

        // BiPredicate
        BiPredicate<Integer, Integer> isDivisible = (a, b) -> a % b == 0;
        System.out.println("Is 10 divisible by 5? " + isDivisible.test(10, 5));

        // Function: one argument, returns transformed value
        Function<String, Integer> parseLength = str -> str.length();
        System.out.println("Length: " + parseLength.apply("Lambda"));

        // BiFunction: two arguments, returns value
        BiFunction<Integer, Integer, Integer> power = (base, exp) -> (int)Math.pow(base, exp);
        System.out.println("2^3 = " + power.apply(2, 3));

        // UnaryOperator: one argument, same return type
        UnaryOperator<String> shout = str -> str.toUpperCase() + "!";
        System.out.println(shout.apply("hello"));

        // BinaryOperator: two arguments, same type and return
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        System.out.println("3 * 4 = " + multiply.apply(3, 4));

        // Using lambdas with map/forEach
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.forEach((key, val) -> System.out.println(key + " -> " + val));

        // Sorting with Comparator
        List<String> names = Arrays.asList("Zara", "Liam", "Alex", "Mona");
        names.sort((a, b) -> a.compareToIgnoreCase(b));
        System.out.println("Sorted names: " + names);

        // Stream processing
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // Filtering with lambdas
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog");
        words.stream()
                .filter(w -> w.length() == 3)
                .forEach(System.out::println);

        // Custom functional interface
        Greetable greetable = name -> "Hi, " + name;
        System.out.println(greetable.greet("Emma"));

        // Capturing local variables (effectively final)
        int factor = 2;
        Function<Integer, Integer> multiplier = x -> x * factor;
        System.out.println("5 * 2 = " + multiplier.apply(5));
    }

    @FunctionalInterface
    interface Greetable {
        String greet(String name);
    }
}