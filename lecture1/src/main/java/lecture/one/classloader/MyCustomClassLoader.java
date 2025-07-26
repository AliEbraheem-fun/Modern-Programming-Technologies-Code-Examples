package lecture.one.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Наш пользовательский ClassLoader
public class MyCustomClassLoader extends ClassLoader {

    private final String classesDir;

    // Конструктор принимает путь к папке с .class-файлами
    public MyCustomClassLoader(String classesDir) {
        this.classesDir = classesDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // Получаем путь к файлу на диске
            String path = classesDir + "/" + name.replace('.', '/') + ".class";

            // Читаем содержимое файла в массив байт
            byte[] classBytes = Files.readAllBytes(Paths.get(path));

            // Создаём объект Class из байт-кода
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Не удалось загрузить класс: " + name, e);
        }
    }
}