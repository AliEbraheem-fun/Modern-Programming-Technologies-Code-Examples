package lecture.one.records;

// Объявление записи с двумя полями: тип и имя достаточно
public record Person(String name, int age) implements Comparable<Person> {

    // Можно определить дополнительный метод
    public String greet() {
        return "Hello, my name is " + name + " and I am " + age + " years old.";
    }

    // Можно переопределить auto-generated метод, если нужно
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    // Можно добавить статический метод или вложенные типы
    public static Person of(String name) {
        return new Person(name, 0);
    }

    public static void main(String[] args) {
        // Создание объекта записи
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob", 25);

        // Автоматически доступны методы-геттеры
        System.out.println("Name: " + p1.name());
        System.out.println("Age: " + p1.age());

        // toString() сгенерирован автоматически
        System.out.println("Record as string: " + p1);

        // equals() и hashCode() работают по содержимому
        Person p3 = new Person("Alice", 30);
        System.out.println("p1 equals p3? " + p1.equals(p3)); // true

        // Использование дополнительного метода
        System.out.println(p2.greet());

        // Сортировка с помощью compareTo()
        System.out.println("Compare p1 and p2 by age: " + p1.compareTo(p2)); // положительное число

        // Использование статического фабричного метода
        Person p4 = Person.of("Charlie");
        System.out.println(p4);
    }


}

