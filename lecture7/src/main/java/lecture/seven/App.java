package lecture.seven;

// Импорт классов JavaFX для работы с графическим интерфейсом
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Главный класс JavaFX-приложения. Наследуется от javafx.application.Application.
 * Отвечает за загрузку FXML-файлов, создание сцен и управление переключением между ними.
 */
public class App extends Application {

    /** Главное окно приложения (Stage), используется для отображения сцен. */
    private Stage primaryStage;

    /** Сцена с цифровыми часами, загружается из digital.fxml. */
    private Scene digitalScene;

    /** Сцена с аналоговыми часами, загружается из analog.fxml. */
    private Scene analogScene;

    /** Статическая переменная, содержащая экземпляр App.
     * Используется для доступа к App из контроллеров (Singleton-подход).
     */
    private static App instance;

    /**
     * Метод start вызывается JavaFX при запуске приложения.
     * Здесь происходит:
     * - инициализация переменной instance,
     * - загрузка сцен из FXML,
     * - установка заголовка и начальной сцены,
     * - отображение окна.
     *
     * @param stage главный Stage, автоматически передаётся JavaFX
     * @throws Exception если возникнут ошибки при загрузке FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        instance = this;            // Сохраняем текущий объект App (для Singleton-доступа)
        this.primaryStage = stage; // Сохраняем ссылку на главное окно

        // Загружаем digital.fxml — макет цифровых часов
        FXMLLoader digitalLoader = new FXMLLoader(getClass().getResource("/lecture/seven/digital.fxml"));
        digitalScene = new Scene(digitalLoader.load());

        // Загружаем analog.fxml — макет аналоговых часов
        FXMLLoader analogLoader = new FXMLLoader(getClass().getResource("/lecture/seven/analog.fxml"));
        analogScene = new Scene(analogLoader.load());

        // Настраиваем основное окно
        stage.setTitle("Clock");            // Устанавливаем заголовок окна
        stage.setScene(digitalScene);       // Устанавливаем цифровую сцену как стартовую
        primaryStage.setResizable(false);   // Запрещаем изменение размеров окна
        stage.show();                       // Показываем окно пользователю
    }

    /**
     * Геттер, возвращающий текущий экземпляр класса App.
     * Нужен, чтобы из других классов (например, контроллеров) можно было получить доступ к методам App.
     *
     * @return текущий экземпляр App
     */
    public static App getInstance() {
        return instance;
    }

    /**
     * Переключает текущую сцену на цифровой вариант (digitalScene).
     * Обычно вызывается при нажатии кнопки "Переключить на цифровые часы".
     */
    public void switchToDigital() {
        primaryStage.setScene(digitalScene);
    }

    /**
     * Переключает текущую сцену на аналоговый вариант (analogScene).
     * Обычно вызывается при нажатии кнопки "Переключить на аналоговые часы".
     */
    public void switchToAnalog() {
        primaryStage.setScene(analogScene);
    }

    /**
     * Главная точка входа в программу.
     * Метод launch(...) запускает JavaFX-приложение и вызывает метод start().
     *
     * @param args параметры командной строки (обычно не используются)
     */
    public static void main(String[] args) {
        launch(args); // Запускаем JavaFX-приложение
    }
}
