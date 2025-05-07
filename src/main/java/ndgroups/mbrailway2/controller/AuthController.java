package ndgroups.mbrailway2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ndgroups.mbrailway2.events.RegistrationCompleteEvent;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.model.token.VerificationToken;
import ndgroups.mbrailway2.repository.UserRepository;
import ndgroups.mbrailway2.repository.VerificationTokenRepository;
import ndgroups.mbrailway2.request.RegistrationRequest;
import ndgroups.mbrailway2.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController implements WebMvcConfigurer {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @GetMapping("/auth/register")
    public String getRegisterUser(@ModelAttribute("user") User user) {
        return "pages/register";
    }
    @PostMapping("/auth/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationRequest registrationRequest,
                               final HttpServletRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "pages/register";
        }
//        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
//        if (existingUser.isPresent()) {
//            bindingResult.rejectValue("email", null, "Email already in use");
//            return "pages/register";
//        }
//        if(user.getRole() == null || user.getRole().isBlank()){
//            user.setRole("USER");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);

        User user =  userService.registerUser(registrationRequest);
        //publish an event to the user
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "redirect:/login?success";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
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

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken  = verificationTokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "This account has already been verified, Please login.";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login into your account";
        }
        return "invalid verification token";
    }
}

