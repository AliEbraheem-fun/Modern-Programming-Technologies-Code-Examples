package lecture.one.classloader;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        String basePath = Paths.get("").toAbsolutePath().toString();

        // 1. Загружаем Hello вручную
        MyCustomClassLoader loader = new MyCustomClassLoader(basePath + "/target/classes/compiled");
        Class<?> helloClass = loader.loadClass("Hello");
        Object helloInstance = helloClass.getDeclaredConstructor().newInstance();
        helloClass.getMethod("sayHello").invoke(helloInstance);
        System.out.println("Загрузчик Hello: " + helloClass.getClassLoader());

        // 2. Загружаем Hello2 через AppClassLoader
        MyCustomClassLoader loader2 = new MyCustomClassLoader(basePath);
        Class<?> hello2Class = loader2.loadClass("lecture.one.classloader.Hello2");
        Object hello2Instance = hello2Class.getDeclaredConstructor().newInstance();
        hello2Class.getMethod("sayHello").invoke(hello2Instance);
        System.out.println("Загрузчик Hello2: " + hello2Class.getClassLoader());

        // 3. Загрузим класс через Platform ClassLoader
        ClassLoader platformLoader = ClassLoader.getPlatformClassLoader();
        System.out.println("Platform ClassLoader: " + platformLoader);

        // Пробуем загрузить класс, который действительно доступен через платформу (например, java.logging)
        Class<?> loggerClass = platformLoader.loadClass("java.util.logging.Logger");
        System.out.println("Загрузчик Logger: " + loggerClass.getClassLoader());

        // 4. Bootstrap ClassLoader (null)
        Class<?> stringClass = String.class;
        System.out.println("Загрузчик String (Bootstrap): " + stringClass.getClassLoader());
    }
}
