package lecture.eight.student.repository;

import lecture.eight.student.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom
 * queries related to {@code User}.
 * </p>
 *
 * <p>Additional methods:</p>
 * <ul>
 *     <li>{@link #findByEmail(String)} — find user by email</li>
 *     <li>{@link #existsByEmail(String)} — check if email already exists</li>
 * </ul>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by email.
     *
     * @param email the email to search for
     * @return an {@link Optional} containing the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists by email.
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
