package edu.farmingdale.csc311.lostfound.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // shows the empty form
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // when the user clicks Register
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {

        username = username.trim();
        String error = null;

        if (username.length() < 3) {
            error = "Username must be at least 3 characters.";
        } else if (password.length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (repository.existsByUsername(username)) {
            error = "That username is already taken.";
        }

        if (error != null) {
            // send the user back to the form with the message
            model.addAttribute("error", error);
            model.addAttribute("username", username);
            return "register";
        }

        // hash the password with BCrypt
        String hashedPassword = passwordEncoder.encode(password);
        repository.save(new AppUser(username, hashedPassword));

        return "redirect:/login?registered";
    }
}
