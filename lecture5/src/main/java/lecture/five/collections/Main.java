package lecture.five.collections;


import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        basicCollectionsDemo();
        iterationAndSorting();
        streamExample();
        synchronizedCollection();
        concurrentCollectionsDemo();
    }

    // 1. Основы: List, Set, Map
    public static void basicCollectionsDemo() {
        System.out.println("=== Basic Collections Demo ===");
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");

        Set<String> set = new HashSet<>(list);

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 5);
        map.put("Orange", 2);

        System.out.println("List: " + list);
        System.out.println("Set: " + set);
        System.out.println("Map: " + map);
    }

    // 2. Перебор и сортировка
    public static void iterationAndSorting() {
        System.out.println("\n=== Iteration and Sorting ===");
        List<String> names = Arrays.asList("Anna", "John", "Bella", "Mike");

        System.out.println("Original:");
        for (String name : names) {
            System.out.println(name);
        }

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        System.out.println("Sorted: " + sorted);

        sorted.sort(Comparator.reverseOrder());
        System.out.println("Reverse sorted: " + sorted);
    }

    // 3. Stream API
    public static void streamExample() {
        System.out.println("\n=== Stream API Example ===");
        List<Integer> numbers = Arrays.asList(2, 3, 6, 7, 8, 10);

        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Even squares: " + evenSquares);
    }

    // 4. Синхронизированные коллекции
    public static void synchronizedCollection() {
        System.out.println("\n=== Synchronized Collection ===");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");

        synchronized (syncList) {
            for (String s : syncList) {
                System.out.println(s);
            }
        }
    }

    // 5. Конкурентные коллекции
    public static void concurrentCollectionsDemo() {
        System.out.println("\n=== Concurrent Collections ===");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("X");
        cowList.add("Y");

        ConcurrentHashMap<String, Integer> cmap = new ConcurrentHashMap<>();
        cmap.put("Java", 10);
        cmap.put("Python", 20);

        System.out.println("CopyOnWriteArrayList: " + cowList);
        System.out.println("ConcurrentHashMap: " + cmap);
    }
}
