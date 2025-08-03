package lecture.eight.student.service;

import lecture.eight.student.model.User;
import lecture.eight.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Custom implementation of {@link UserDetailsService} for Spring Security.
 * <p>
 * Loads user details from the database based on email and converts
 * them to Spring Security's {@link UserDetails} with appropriate role-based authorities.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user from the database by email and maps their role to
     * a {@link SimpleGrantedAuthority} for authentication and authorization.
     *
     * @param email the email of the user (used as username)
     * @return a fully populated {@link UserDetails} object
     * @throws UsernameNotFoundException if user is not found by email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
