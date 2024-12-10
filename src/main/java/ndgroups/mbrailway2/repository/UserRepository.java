package ndgroups.mbrailway2.repository;

import ndgroups.mbrailway2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User>  findByEmail(String email);
    Optional<User> findByUsername(String username);
//    public boolean isEmailExist(String email);

}
