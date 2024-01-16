package unit11;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unit11.data.domain.AddressEntity;
import unit11.data.domain.UsersEntity;
import unit11.data.dto.UserDtoRequest;
import unit11.repository.AddressRepository;
import unit11.repository.UserRepository;
import unit11.service.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void beforeEach() {
        addressRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getUser_whenExists_thenReturnFromDb() {
        var address = new AddressEntity("my_address", false, 0, Set.of());
        address = addressRepository.save(address);
        var user = new UsersEntity(1, "andrey", address, Set.of());
        user = userRepository.save(user);

        var actualUser = userService.getUser(user.getId());
        Assertions.assertEquals(user.getName(), actualUser.getName());
        Assertions.assertEquals(user.getAddress().getAddress(), actualUser.getAddress());
        Assertions.assertEquals(user.getCars(), user.getCars());

    }

    @Test
    void updateUser_whenExists_thenUpdate() {
        var address = new AddressEntity("my_address", false, 0, Set.of());
        address = addressRepository.save(address);
        var user = new UsersEntity(1, "Andrey", address, Set.of());
        userRepository.save(user);

        userService.updateUser(1, new UserDtoRequest("Andrey 2", "my_address"));
        var actualUser = userRepository.findById(1).get();
        Assertions.assertEquals("Andrey 2", actualUser.getName());
        Assertions.assertEquals("my_address", actualUser.getAddress().getAddress());
        Assertions.assertEquals(user.getCars(), user.getCars());

    }

    @Test
    void deleteUser_whenExists_thenDelete() {
        var address = new AddressEntity("my_address", false, 0, Set.of());
        address = addressRepository.save(address);
        var user = new UsersEntity(1, "Andrey", address, Set.of());
        userRepository.save(user);

        userService.deleteUser(1);

        var userOptional = userRepository.findById(1);

        Assertions.assertTrue(userOptional.isEmpty());

    }

    @Test
    void addUser_thenExistsInDb() {
        var address = new AddressEntity("my_address", false, 0, Set.of());
        addressRepository.save(address);

        userService.addUser(new UserDtoRequest("Andrey_add", "my_address"));

        var actualUser = userRepository.findAll().stream().findAny().get();

        Assertions.assertEquals("my_address", actualUser.getAddress().getAddress());
        Assertions.assertEquals("Andrey_add", actualUser.getName());


    }

}
