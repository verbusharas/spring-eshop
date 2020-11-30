package lt.verbus.eshop.user.repository;

import lt.verbus.eshop.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
