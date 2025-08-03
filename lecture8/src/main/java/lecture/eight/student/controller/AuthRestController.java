package lecture.eight.student.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lecture.eight.student.model.User;
import lecture.eight.student.repository.UserRepository;
import lecture.eight.student.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication operations such as login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Authenticates the user and returns a JWT token.
     *
     * @param request user credentials (email and password)
     * @return JWT token if authenticated
     */
    @Operation(summary = "Login", description = "Authenticates the user and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Authentication successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}
