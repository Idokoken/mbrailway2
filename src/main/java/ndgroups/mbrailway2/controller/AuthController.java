package ndgroups.mbrailway2.controller;

import jakarta.validation.Valid;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.UserRepository;
import ndgroups.mbrailway2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Optional;

@Controller
public class AuthController implements WebMvcConfigurer {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/auth/register")
    public String getRegisterUser(@ModelAttribute("user") User user) {
        return "pages/register";
    }
    @PostMapping("/auth/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "pages/register";
        }
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("email", null, "Email already in use");
            return "pages/register";
        }
        if(user.getRole() == null || user.getRole().isBlank()){
            user.setRole("USER");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?success";
    }


    @GetMapping("/login")
    public String getLogin() {
        return "pages/login";
    }

    //logout route
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}

