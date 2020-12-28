package lt.verbus.eshop.user.repository;

import lt.verbus.eshop.user.model.Role;
import lt.verbus.eshop.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryShould {

    public static final String USER_NAME = "UserName";
    public static final String PASSWORD = "password";
    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Test
    void return_user_with_roles_by_username() {

        //  given
        User user = new User();
        user.setUsername(USER_NAME);
        user.setPassword(PASSWORD);
        user.setRoles(Set.of(Role.builder().roleName("NAUJA_ROLE").build()));
        em.persistAndFlush(user);
        //  when
        Optional<User> userOptional = userRepository.findWithRolesByUsername(USER_NAME);

        //  then
        assertTrue(userOptional.isPresent());
        assertThat(userOptional.get().getRoles(), containsInAnyOrder(
                hasProperty("roleName", equalTo("NAUJA_ROLE"))));
//        User userFromDb = userOptional.get();
//        assertEquals(USER_NAME, userFromDb.getUsername());
//        assertEquals(PASSWORD, userFromDb.getPassword());
    }
}