package ndgroups.mbrailway2.service.Interface;

import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.request.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerUser(RegistrationRequest request);
    User getOneUser(Integer id);
    List<User> getAllUsers();
    User updateUser(Integer id, User student);
    void deleteUser(Integer id);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String verificationToken);
    String validateToken(String theToken);
}