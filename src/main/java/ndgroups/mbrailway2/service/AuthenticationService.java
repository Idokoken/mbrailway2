package ndgroups.mbrailway2.service;

import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {

//        if(user.getRole() == null || user.getRole().isBlank()){
//            user.setRole("USER");
//        }
//        if (userRepository.isEmailExist(user.getEmail())){
//            throw new OurException(user.getEmail() + "Email Already Exist");
//        }
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User>optUser = userRepository.findByUsername(username);
        return optUser.get();
    }

}
