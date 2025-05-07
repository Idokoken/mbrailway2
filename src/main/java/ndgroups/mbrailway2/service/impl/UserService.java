package ndgroups.mbrailway2.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ndgroups.mbrailway2.exception.UserAlreadyExistException;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.model.token.VerificationToken;
import ndgroups.mbrailway2.repository.UserRepository;
import ndgroups.mbrailway2.repository.VerificationTokenRepository;
import ndgroups.mbrailway2.request.RegistrationRequest;
import ndgroups.mbrailway2.service.Interface.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  VerificationTokenRepository tokenRepository;

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if(user.isPresent()){
            throw new UserAlreadyExistException(
                    "user with email address " + request.email() + " already exist");
        }
        var newUser =  new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }
    @Override
    public User getOneUser(Integer id) {
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
@Override
public List<User> getAllUsers() {
    return userRepository.findAll();
}

    @Override
    public User updateUser(Integer id, User student) {
        User existingUser  = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        // Copy updated fields to existing product (excluding id)
        BeanUtils.copyProperties(student,existingUser, "id");

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public String validateToken(String theToken) {
        VerificationToken token  = tokenRepository.findByToken(theToken);
        if(token  == null) {
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if((token.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return "token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }
}
