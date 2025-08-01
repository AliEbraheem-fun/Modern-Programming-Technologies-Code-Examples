package lecture.seven;

// Импорт необходимых компонентов JavaFX
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;

/**
 * Контроллер аналоговых часов.
 * Отвечает за отображение графических стрелок, меток времени (1-12) и обновление положения стрелок по реальному времени.
 */
public class AnalogClockController {

    /** Группа, содержащая все отметки и цифры циферблата. */
    @FXML private Group ticksAndNumbers;

    /** Линии, представляющие стрелки часов: часовая, минутная и секундная. */
    @FXML private Line hourHand, minuteHand, secondHand;

    /** Центр часов и радиус круга, используемого для построения отметок и стрелок. */
    private final double centerX = 150, centerY = 150, radius = 120;

    /**
     * Метод инициализации, вызываемый автоматически после загрузки FXML.
     * Создаёт метки времени (1–12), устанавливает начальное положение стрелок и запускает таймер обновления.
     */
    @FXML
    public void initialize() {
        drawTicksAndNumbers();   // Рисуем метки времени вокруг циферблата
        updateHands();           // Устанавливаем стрелки в нужное положение

        // Создаём таймер, который будет вызывать updateHands() каждую секунду
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateHands())
        );

        // Таймер будет повторяться бесконечно
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Запуск анимации
    }

    /**
     * Метод для отрисовки 12 цифровых меток времени (1–12) по кругу.
     * Используется базовая геометрия для позиционирования текста.
     */
    private void drawTicksAndNumbers() {
        for (int i = 1; i <= 12; i++) {
            // Вычисляем угол для текущей метки в радианах (начиная с "12 часов")
            double angle = Math.toRadians(i * 30 - 90);

            // Вычисляем координаты для текста цифры (слегка внутри круга)
            double xNum = centerX + (radius - 20) * Math.cos(angle);
            double yNum = centerY + (radius - 20) * Math.sin(angle);

            // Создаём текстовую метку и смещаем её немного для центрирования
            Text num = new Text(String.valueOf(i));
            num.setX(xNum - 6); // Смещение по X для выравнивания текста
            num.setY(yNum + 6); // Смещение по Y, чтобы не "висело" в воздухе

            // Добавляем метку в группу на сцене
            ticksAndNumbers.getChildren().add(num);
        }
    }

    /**
     * Метод обновляет положение всех стрелок (часовой, минутной, секундной)
     * на основании текущего локального времени.
     */
    private void updateHands() {
        // Получаем системное локальное время
        LocalTime now = LocalTime.now();

        // Вычисляем угол для каждой стрелки:
        double secAngle = now.getSecond() * 6;                        // 360° / 60 секунд
        double minAngle = now.getMinute() * 6 + now.getSecond() * 0.1; // + поправка от секунд
        double hourAngle = (now.getHour() % 12) * 30 + now.getMinute() * 0.5; // 360 / 12 + поправка

        // Применяем вычисленные углы к стрелкам
        setHand(hourHand, hourAngle, 60);    // Часовая — самая короткая
        setHand(minuteHand, minAngle, 90);   // Минутная — средняя длина
        setHand(secondHand, secAngle, 110);  // Секундная — самая длинная
    }

    /**
     * Устанавливает положение заданной стрелки (объект Line) на основе угла и длины.
     *
     * @param hand     объект Line, представляющий стрелку
     * @param angleDeg угол в градусах (0 = 3 часа, -90 = вверх)
     * @param length   длина стрелки от центра
     */
    private void setHand(Line hand, double angleDeg, double length) {
        hand.setStartX(centerX); // Начало стрелки — центр циферблата по X
        hand.setStartY(centerY); // Начало стрелки — центр циферблата по Y

        // Конец стрелки определяется по углу и длине (с поправкой -90°)
        hand.setEndX(centerX + length * Math.cos(Math.toRadians(angleDeg - 90)));
        hand.setEndY(centerY + length * Math.sin(Math.toRadians(angleDeg - 90)));
    }

    /**
     * Обработчик кнопки "Переключить на цифровые часы", вызывается из FXML по onAction.
     * Использует ссылку на главный класс приложения для смены сцены.
     */
    @FXML
    private void switchToDigital() {
        App.getInstance().switchToDigital();
    }
}
