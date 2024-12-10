package ndgroups.mbrailway2.service;

import jakarta.persistence.EntityNotFoundException;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()  {
        return userRepository.findAll();
    }
    public User getOneUser(Integer id) {
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User updateUser(Integer id, User student) {
        User existingUser  = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        // Copy updated fields to existing product (excluding id)
        BeanUtils.copyProperties(student,existingUser, "id");

        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
