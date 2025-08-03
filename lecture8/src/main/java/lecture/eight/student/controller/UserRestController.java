package lecture.eight.student.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lecture.eight.student.model.Role;
import lecture.eight.student.model.User;
import lecture.eight.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 * Accessible only to users with SUPER_ADMIN role.
 */
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a list of all users.
     *
     * @return list of users
     */
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    @ApiResponse(responseCode = "200", description = "List of users returned successfully")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Changes the role of a specific user by ID.
     *
     * @param id   user ID
     * @param role new role to assign
     * @return success or error response
     */
    @Operation(summary = "Change user role", description = "Updates the role of the specified user")
    @ApiResponse(responseCode = "200", description = "User role updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable Long id, @RequestParam Role role) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User user = optionalUser.get();
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok().body("User role updated");
    }

    /**
     * Deletes a user by ID.
     *
     * @param id user ID
     * @return success or error response
     */
    @Operation(summary = "Delete user", description = "Deletes a user with the specified ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User deleted");
    }
}
