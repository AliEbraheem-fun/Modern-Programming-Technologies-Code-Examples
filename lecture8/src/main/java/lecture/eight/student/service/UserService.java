package lecture.eight.student.service;

import lecture.eight.student.model.User;

import java.util.List;

/**
 * Service interface for managing {@link User} entities.
 * <p>
 * Provides methods for registration, lookup, and listing users.
 * </p>
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param user the {@link User} to register
     * @return true if registration is successful, false if user already exists
     */
    boolean register(User user);

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email to check
     * @return true if a user with the given email exists
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return the {@link User}, or null if not found
     */
    User findByEmail(String email);

    /**
     * Returns all registered users.
     *
     * @return list of {@link User}
     */
    List<User> findAll();
}
