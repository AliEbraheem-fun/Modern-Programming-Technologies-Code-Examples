package lecture.eight.student.config;

import lecture.eight.student.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурационный класс безопасности Spring Security.
 * <p>
 * Определяет правила доступа, конфигурацию форм логина и выхода,
 * а также подключение JWT-фильтра.
 * </p>
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomAuthenticationEntryPoint authEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * Конфигурирует цепочку фильтров безопасности.
     *
     * @param http объект HttpSecurity
     * @return цепочка фильтров безопасности
     * @throws Exception в случае ошибки конфигурации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/api/auth/**", "/h2-console/**").permitAll()
                        .requestMatchers("/api/students").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/api/students/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/students", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Кодировщик паролей, использующий BCrypt.
     *
     * @return объект PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Возвращает менеджер аутентификации на основе текущей конфигурации.
     *
     * @param config конфигурация аутентификации
     * @return AuthenticationManager
     * @throws Exception в случае ошибки
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
