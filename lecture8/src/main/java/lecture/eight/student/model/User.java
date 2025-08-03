package lecture.eight.student.model;

import jakarta.persistence.*;

/**
 * Entity representing an application user.
 * <p>
 * Each user has an email, password, and associated {@link Role}.
 * Used for both authentication and role-based authorization.
 * </p>
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Primary key — unique ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email of the user. Must be unique and non-null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Password of the user (stored in hashed form).
     */
    @Column(nullable = false)
    private String password;

    /**
     * Role assigned to the user: USER, ADMIN, or SUPER_ADMIN.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    /**
     * Default constructor.
     */
    public User() {}

    /**
     * Constructor for creating a user with email and password.
     * Role defaults to {@link Role#USER}.
     *
     * @param email user's email
     * @param password user's password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}
