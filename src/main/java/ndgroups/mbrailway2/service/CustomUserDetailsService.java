package ndgroups.mbrailway2.service;

import ndgroups.mbrailway2.model.CustomUserDetails;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("No user found for the given Email");
        }
        return new CustomUserDetails(user.get());
    }


    public String[] getRoles(User user){
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }





}
