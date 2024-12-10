package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register/user")
    public User registerUserTest(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User loginRequestDTO) {
//        Response response = userService.login(loginRequestDTO);
        return ResponseEntity.status(200).body(loginRequestDTO);
    }


}
