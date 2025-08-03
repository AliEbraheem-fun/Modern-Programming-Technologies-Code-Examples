package lecture.eight.student.controller;

import lecture.eight.student.model.Role;
import lecture.eight.student.model.User;
import lecture.eight.student.repository.UserRepository;
import lecture.eight.student.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Web controller for managing users (accessible only by SUPER_ADMIN).
 */
@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class UserWebController {

    @Autowired
    private JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public UserWebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Displays the user control panel.
     *
     * @param model Spring MVC model to inject user list and messages
     * @param msg optional flash message to show
     * @return name of the users view template
     */
    @GetMapping
    public String userPage(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("message", msg);
        return "users";
    }

    /**
     * Updates the role of a user.
     *
     * @param id    user ID
     * @param role  new role to assign
     * @param redirectAttributes attributes to carry flash messages
     * @return redirect back to users page with success/failure message
     */
    @PostMapping("/update")
    public String updateRole(@RequestParam Long id,
                             @RequestParam Role role,
                             RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(role);
            userRepository.save(user);
            redirectAttributes.addAttribute("msg", "Role updated successfully.");
        } else {
            redirectAttributes.addAttribute("msg", "User not found.");
        }
        return "redirect:/users";
    }

    /**
     * Deletes a user by ID.
     *
     * @param id ID of user to delete
     * @param redirectAttributes flash message about the result
     * @return redirect to users page
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted";
        } else {
            return "User not found";
        }
    }
}
