package lecture.three.encapsulation;

public class EncapsulationExamples {
    // Приватные поля — недоступны напрямую снаружи
    private String name;
    private int age;

    // Конструктор
    public EncapsulationExamples(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттер для имени
    public String getName() {
        return name;
    }

    // Сеттер для имени
    public void setName(String name) {
        // Пример валидации данных
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    // Геттер для возраста
    public int getAge() {
        return age;
    }

    // Сеттер для возраста
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    // Метод, выводящий информацию
    public void printInfo() {
        System.out.println("Имя: " + name + ", возраст: " + age);
    }

    // Главный метод для демонстрации
    public static void main(String[] args) {
        EncapsulationExamples person = new EncapsulationExamples("Анна", 25);
        person.printInfo();

        // Попытка изменить поля через сеттеры
        person.setName("Мария");
        person.setAge(30);
        person.printInfo();

        // Попытка установить некорректные значения
        person.setName("");
        person.setAge(-10);
        person.printInfo();  // Значения не изменятся, так как данные не прошли валидацию
    }
}
