package lecture.eight.student.service;

import lecture.eight.student.model.User;
import lecture.eight.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} interface that handles user registration,
 * password encoding, and data access via {@link UserRepository}.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
