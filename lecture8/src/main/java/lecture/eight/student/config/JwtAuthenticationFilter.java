package lecture.eight.student.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lecture.eight.student.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter that intercepts HTTP requests and performs JWT-based authentication.
 * <p>
 * If a valid JWT is found in the "Authorization" header, the user is authenticated
 * and the security context is updated.
 * </p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Extracts the JWT token from the Authorization header, validates it,
     * and sets the authentication in the Spring Security context.
     * <p>
     * If token is invalid or missing, the filter continues normally,
     * or returns a JSON error response for API requests.
     * </p>
     *
     * @param request     HTTP request
     * @param response    HTTP response
     * @param filterChain chain of filters to continue processing
     * @throws ServletException in case of a general servlet error
     * @throws IOException      in case of I/O error
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

            } catch (Exception ex) {
                if (request.getRequestURI().startsWith("/api/")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"" + ex.getClass().getSimpleName() + ": " + ex.getMessage() + "\"}");
                    return;
                } else {
                    throw ex;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}
