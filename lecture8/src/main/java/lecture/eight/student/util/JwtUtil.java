package lecture.eight.student.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for generating and validating JWT tokens.
 * <p>
 * This class is used for creating JSON Web Tokens and extracting
 * user information from them.
 */
@Component
public class JwtUtil {

    /**
     * Secret key used to sign and verify JWT tokens.
     */
    private final String SECRET = "modernprogrammingtechnologiesisinterestingandusefullstudentsloveitreadwritecodeenjoyaprogrammerslifeletusnotgetboredwithrestap";

    /**
     * Generates a JWT token for the provided user details.
     *
     * @param userDetails Spring Security's UserDetails object
     * @return JWT token as a String
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token JWT token
     * @return username from the token
     */
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates the token by comparing it with the provided user details.
     *
     * @param token       JWT token
     * @param userDetails expected user details
     * @return true if valid; false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the token has expired.
     *
     * @param token JWT token
     * @return true if expired; false otherwise
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
