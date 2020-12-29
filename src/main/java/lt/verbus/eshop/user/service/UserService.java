package lt.verbus.eshop.user.service;

import lt.verbus.eshop.user.exception.UserNotFoundException;
import lt.verbus.eshop.user.model.Role;
import lt.verbus.eshop.user.model.User;
import lt.verbus.eshop.user.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Primary
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(new Role(1L, "USER")));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void updateUser(Long id, User newUser) {
        User existingUser = getUserById(id);
        //FIXME: Use mapper or BeanUtil.
        existingUser.setPhone(newUser.getPhone());
        existingUser.setZip(newUser.getZip());
        userRepository.save(existingUser);
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findWithRolesByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));


        //        return new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//            }
//
//            @Override
//            public String getPassword() {
//                return "{bcrypt}$2y$12$w7I7MEqU245PnWr9l29Qr.RlXMt2nIDyDGakaxYlujlk2v0yQjj6G";
//            }
//
//            @Override
//            public String getUsername() {
//                return username;
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return true;
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return true;
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return true;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return true;
//            }
//        };
//        throw new UsernameNotFoundException(username);
    }
}
