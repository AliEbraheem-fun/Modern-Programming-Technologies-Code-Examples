package lecture.eight.student.config;

import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom handler for AccessDeniedException.
 * <p>
 * Returns JSON error message for API requests,
 * and redirects to a custom access denied page for web requests.
 * </p>
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles access denied exceptions by checking the request path.
     * If the request is for the API (starts with /api/), returns JSON.
     * Otherwise, redirects to the "/access-denied" page.
     *
     * @param request               the {@link HttpServletRequest}
     * @param response              the {@link HttpServletResponse}
     * @param accessDeniedException the exception that was thrown
     * @throws IOException in case of I/O errors
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {

        if (request.getRequestURI().startsWith("/api/")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Forbidden\"}");
        } else {
            response.sendRedirect("/access-denied");
        }
    }
}
