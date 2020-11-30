package lt.verbus.eshop.user.service;

import lt.verbus.eshop.user.exception.UserNotFoundException;
import lt.verbus.eshop.user.model.User;
import lt.verbus.eshop.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        //TODO:user password encoder here..
        UUID tempPsw = UUID.randomUUID();
        user.setPassword(tempPsw.toString());
        return userRepository.save(user);
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void updateUser(Long id, User newUser){
        User existingUser = getUserById(id);
        //FIXME: Use mapper or BeanUtil.
        existingUser.setPhone(newUser.getPhone());
        existingUser.setPhone(newUser.getZip());
        userRepository.save(existingUser);
    }



}
