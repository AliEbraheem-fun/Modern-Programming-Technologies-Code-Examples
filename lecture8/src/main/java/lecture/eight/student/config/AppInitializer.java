package lecture.eight.student.config;

import jakarta.annotation.PostConstruct;
import lecture.eight.student.model.*;
import lecture.eight.student.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Инициализатор приложения, создающий начальные учетные записи пользователей и студентов.
 * <p>
 * При запуске приложения добавляет супер-админа, админа и пользователя, если они ещё не существуют.
 * Также добавляет тестовые записи студентов, если таблица пуста.
 * </p>
 */
@Component
public class AppInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Метод, автоматически вызываемый после создания компонента.
     * Инициирует добавление пользователей и студентов.
     */
    @PostConstruct
    public void init() {
        createUserIfNotExists("superadmin@example.com", "password", Role.SUPER_ADMIN);
        createUserIfNotExists("admin@example.com", "password", Role.ADMIN);
        createUserIfNotExists("user@example.com", "password", Role.USER);
        createStudentsIfEmpty();
    }

    /**
     * Создает пользователя с указанным email, паролем и ролью, если он ещё не существует.
     *
     * @param email       адрес электронной почты
     * @param rawPassword необработанный пароль
     * @param role        роль пользователя
     */
    private void createUserIfNotExists(String email, String rawPassword, Role role) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            userRepository.save(user);
            System.out.println("Created " + role + ": " + email);
        }
    }

    /**
     * Добавляет несколько студентов, если таблица студентов пуста.
     */
    private void createStudentsIfEmpty() {
        if (studentRepository.count() == 0) {
            Student s1 = new Student();
            s1.setName("Ali");
            s1.setSurname("Hasan");

            Student s2 = new Student();
            s2.setName("Fatima");
            s2.setSurname("Kassem");

            Student s3 = new Student();
            s3.setName("Ivan");
            s3.setSurname("Petrov");

            Student s4 = new Student();
            s4.setName("Ekaterina");
            s4.setSurname("Sidorova");

            Student s5 = new Student();
            s5.setName("Dmitry");
            s5.setSurname("Alexandrov");

            studentRepository.save(s1);
            studentRepository.save(s2);
            studentRepository.save(s3);
            studentRepository.save(s4);
            studentRepository.save(s5);

            System.out.println("Sample students created");
        }
    }

}
