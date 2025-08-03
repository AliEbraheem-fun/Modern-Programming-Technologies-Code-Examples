package lecture.eight.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс запуска Spring Boot приложения.
 * <p>
 * Содержит метод {@code main}, который инициализирует контекст приложения.
 * </p>
 */
@SpringBootApplication
public class StudentApplication {

	/**
	 * Точка входа в приложение.
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
