package lecture.seven;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Контроллер для отображения цифровых часов.
 * Этот класс управляет представлением времени в виде текста (HH:mm:ss),
 * обновляемого каждую секунду. Также обеспечивает переключение на аналоговые часы.
 */
public class DigitalClockController {

    /** Метка (Label) для отображения текущего времени на экране. */
    @FXML private Label timeLabel;

    /** Форматтер для отображения времени в формате "часы:минуты:секунды" (24-часовой формат). */
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Анимация (таймер), обновляющая время каждую секунду. */
    private Timeline timeline;

    /**
     * Метод инициализации, автоматически вызываемый после загрузки FXML.
     * Выполняет начальное отображение времени и запускает таймер для обновления каждую секунду.
     */
    @FXML
    public void initialize() {
        // Сразу отобразить текущее время при загрузке окна
        updateTime();

        // Создать и запустить Timeline — механизм обновления UI по расписанию
        timeline = new Timeline(
                // Каждую секунду вызывается updateTime()
                new KeyFrame(Duration.seconds(1), e -> updateTime())
        );

        // Повторять бесконечно
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Запуск анимации
        timeline.play();
    }

    /**
     * Обновляет содержимое метки `timeLabel` текущим временем,
     * отформатированным в соответствии с `TIME_FORMATTER`.
     */
    private void updateTime() {
        LocalTime now = LocalTime.now(); // Получить текущее локальное время
        timeLabel.setText(now.format(TIME_FORMATTER)); // Установить отформатированное время в метку
    }

    /**
     * Метод-обработчик кнопки переключения интерфейса на аналоговые часы.
     * Вызывается из FXML с помощью onAction="#switchToAnalog".
     */
    @FXML
    private void switchToAnalog() {
        // Получить ссылку на главный объект приложения и переключить сцену
        App.getInstance().switchToAnalog();
    }
}
