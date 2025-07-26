package lecture.two.annotations;

// === Пример 1: Аннотация с RetentionPolicy.SOURCE ===
// Используется только во время компиляции (например, ErrorProne, Checkstyle, Lombok)
import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
@interface Todo {
    String message();
}

class ExampleSource {
    // Эта аннотация может быть обработана инструментами статического анализа
    @Todo(message = "Удалить после рефакторинга")
    private String tempField;
}

// === Пример 2: Аннотация с RetentionPolicy.CLASS ===
// Хранится в .class файле, но не доступна во время выполнения через Reflection.
// Может использоваться сторонними инструментами (APT, bytecode analyzers).
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@interface Internal {
    String module() default "core";
}

@Internal(module = "billing")
class BillingService {
    // Представим, что build-инструмент (например, Gradle плагин или APT) проверяет наличие этой аннотации
}

// === Пример 3: Аннотация с RetentionPolicy.RUNTIME ===
// Можно обрабатывать через Reflection во время выполнения программы.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Info {
    String author();
    String date();
    int version() default 1;
}

class MyService {

    @Info(author = "Alice", date = "2025-07-01", version = 2)
    public void process() {
        System.out.println("Выполняется метод process...");
    }

    @Info(author = "Bob", date = "2025-07-02")
    public void validate() {
        System.out.println("Выполняется метод validate...");
    }
}

class AnnotationProcessor {
    public static void main(String[] args) {
        Class<MyService> clazz = MyService.class;

        for (var method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Info.class)) {
                Info info = method.getAnnotation(Info.class);
                System.out.println("Метод: " + method.getName());
                System.out.println("  Автор: " + info.author());
                System.out.println("  Дата: " + info.date());
                System.out.println("  Версия: " + info.version());
                System.out.println();
            }
        }
    }
}
