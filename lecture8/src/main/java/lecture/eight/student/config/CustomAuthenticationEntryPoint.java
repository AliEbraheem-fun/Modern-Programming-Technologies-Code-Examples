package lecture.eight.student.config;

import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom implementation of {@link AuthenticationEntryPoint} for handling unauthorized access.
 * <p>
 * Sends a JSON error response for API requests and redirects to the login page for others.
 * </p>
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called when a user tries to access a secured REST resource without authentication.
     *
     * @param request       the {@link HttpServletRequest}
     * @param response      the {@link HttpServletResponse}
     * @param authException the exception that caused the invocation
     * @throws IOException in case of I/O errors
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        if (request.getRequestURI().startsWith("/api/")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized\"}");
        } else {
            response.sendRedirect("/login");
        }
    }
}
