package lecture.eight.student.controller;

import lecture.eight.student.model.User;
import lecture.eight.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user registration and login web pages.
 */
@Controller
public class AuthWebController {

    @Autowired
    private UserService userService;

    /**
     * Displays the user registration form.
     *
     * @param model Spring MVC model for view rendering
     * @return name of the registration template
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles registration form submission.
     *
     * @param user  new user object from form
     * @param model Spring MVC model for showing error if any
     * @return redirect to login on success or stay on form with error
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        boolean success = userService.register(user);
        if (success) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "User already exists");
            return "register";
        }
    }

    /**
     * Displays the login page.
     *
     * @return name of the login view template
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
