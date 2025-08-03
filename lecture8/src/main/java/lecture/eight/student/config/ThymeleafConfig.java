package lecture.eight.student.config;

import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс Thymeleaf.
 * <p>
 * Регистрирует поддержку Spring Security в шаблонах Thymeleaf
 * через добавление диалекта {@link SpringSecurityDialect}.
 * </p>
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Регистрирует диалект SpringSecurityDialect для Thymeleaf,
     * который позволяет использовать security-теги в шаблонах.
     *
     * @return объект SpringSecurityDialect
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
