package lecture.one.classes;
// Абстрактный базовый класс (можно наследовать только один)
abstract class Device {
    public abstract void turnOn();
}

// Интерфейс 1
interface Connectable {
    void connect();
}

// Интерфейс 2
interface Updatable {
    void updateFirmware();
}

/**
 * Класс SmartDevice — это пример комплексного класса,
 * демонстрирующего все основные возможности Java-класса.
 *
 * Он наследуется от абстрактного класса Device
 * и реализует два интерфейса: Connectable и Updatable.
 */
public class SmartDevice extends Device implements Connectable, Updatable {

    // Поля экземпляра (non-static) — принадлежат каждому объекту отдельно
    private String modelName;
    private boolean isConnected;

    // Статическое поле — общее для всех объектов класса
    public static int deviceCount = 0;

    // Константа (static final)
    public static final String MANUFACTURER = "SmartTech Inc.";

    // Блок инициализации экземпляра — выполняется при каждом создании объекта
    {
        System.out.println("Инициализация экземпляра SmartDevice");
        deviceCount++;
    }

    // Статический блок инициализации — выполняется один раз при загрузке класса
    static {
        System.out.println("Класс SmartDevice загружен в память");
    }

    // Конструктор
    public SmartDevice(String modelName) {
        this.modelName = modelName;
        this.isConnected = false;
    }

    // Метод экземпляра
    public void turnOn() {
        System.out.println(modelName + " включено.");
    }

    // Реализация метода интерфейса Connectable
    @Override
    public void connect() {
        isConnected = true;
        System.out.println(modelName + " подключено.");
    }

    // Реализация метода интерфейса Updatable
    @Override
    public void updateFirmware() {
        System.out.println(modelName + ": обновление прошивки завершено.");
    }

    // Геттер
    public String getModelName() {
        return modelName;
    }

    // Сеттер
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    // Статический метод
    public static void showTotalDevices() {
        System.out.println("Общее количество устройств: " + deviceCount);
    }

    // Вложенный (static) класс
    public static class FirmwareVersion {
        private String version;

        public FirmwareVersion(String version) {
            this.version = version;
        }

        public void showVersion() {
            System.out.println("Версия прошивки: " + version);
        }
    }
}
