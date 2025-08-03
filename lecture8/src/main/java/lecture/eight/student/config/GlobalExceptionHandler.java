package lecture.eight.student.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * <p>
 * Handles general exceptions and 404 errors differently for API and web requests.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all unhandled exceptions.
     * <p>
     * Returns a JSON error response for API requests,
     * and renders a custom error page for web requests.
     * </p>
     *
     * @param ex      the thrown exception
     * @param request the current {@link HttpServletRequest}
     * @param model   the model to pass data to the view
     * @return JSON response or error view name
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest request, Model model) {
        boolean isApi = request.getRequestURI().startsWith("/api/");

        if (isApi) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage() != null ? ex.getMessage() : "Internal Server Error");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            model.addAttribute("message", ex.getMessage());
            return "error"; // one HTML template
        }
    }

    /**
     * Handles 404 Not Found exceptions (no handler for request).
     * <p>
     * Returns a JSON error for API requests and an error page for others.
     * </p>
     *
     * @param ex      the thrown {@link NoHandlerFoundException}
     * @param request the current request
     * @param model   the model to pass data to the view
     * @return JSON response or error view name
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNotFound(NoHandlerFoundException ex, HttpServletRequest request, Model model) {
        boolean isApi = request.getRequestURI().startsWith("/api/");

        if (isApi) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Not Found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            model.addAttribute("message", "Страница не найдена: " + request.getRequestURI());
            return "error"; // same template
        }
    }
}
