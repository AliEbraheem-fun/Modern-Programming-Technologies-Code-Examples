// Определение модуля с именем lecture.seven
module lecture.seven {

    // Указывает, что модуль использует JavaFX-контролы (например, Button, Label, Scene и т.д.)
    requires javafx.controls;

    // Указывает, что модуль использует JavaFX FXML — механизм загрузки интерфейсов из FXML-файлов
    requires javafx.fxml;

    // Ниже — зависимости от сторонних библиотек (если вы используете ControlsFX, FormsFX и BootstrapFX)

    // ControlsFX — сторонняя библиотека с расширенными элементами управления (например, NotificationPane, CheckComboBox и т.д.)
    requires org.controlsfx.controls;

    // FormsFX — библиотека для построения форм ввода (например, для настройки параметров)
    requires com.dlsc.formsfx;

    // BootstrapFX — библиотека, добавляющая CSS-стили в стиле Bootstrap для JavaFX-приложений
    requires org.kordamp.bootstrapfx.core;

    // Разрешает модулю javafx.fxml доступ к пакету lecture.seven (нужен для корректной загрузки контроллеров через FXML)
    // Без этой строки FXML-загрузчик не сможет создать экземпляры классов из этого пакета
    opens lecture.seven to javafx.fxml;

    // Экспортирует пакет lecture.seven — это значит, что другие модули могут использовать классы из него
    exports lecture.seven;
}
