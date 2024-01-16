package unit11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unit11.data.domain.AddressEntity;
import unit11.data.domain.CarsEntity;
import unit11.data.domain.UsersEntity;
import unit11.repository.AddressRepository;
import unit11.repository.CarsRepository;
import unit11.repository.UserRepository;
import unit11.service.CarRegistrationService;

@SpringBootTest
public class CarRegistrationServiceTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRegistrationService carRegistrationService;
    @Autowired
    private CarsRepository carsRepository;

    @AfterEach
    void afterEach() {
        carsRepository.deleteAll();
        userRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void addCarToUser_whenNotExists_thenAdd() {
        var address = AddressEntity.builder().address("my_address").build();
        var user = UsersEntity.builder().name("andrey").address(address).build();
        user = userRepository.save(user);
        var car = CarsEntity.builder().brand("BMW").number("number").build();
        car = carsRepository.save(car);

        carRegistrationService.addCarToUser(user.getId(), car.getId());

        var actualUserCars = userRepository.findById(user.getId()).get().getCars();
        Assertions.assertEquals(1, actualUserCars.size());
        var actualCar = actualUserCars.stream().findAny().get();
        Assertions.assertEquals(car.getBrand(), actualCar.getBrand());
        Assertions.assertEquals(car.getNumber(), actualCar.getNumber());

    }

    @Test
    void removeCarsFromUser_whenExists_thenDelete() {
        var address = AddressEntity.builder().address("my_address").build();
        var user = UsersEntity.builder().name("andrey").address(address).build();
        user = userRepository.save(user);
        var car = CarsEntity.builder().brand("BMW").number("number").build();
        car = carsRepository.save(car);
        user.addCar(car);
        userRepository.save(user);

        carRegistrationService.removeCarFromUser(user.getId(), car.getId());

        var actualUserCars = userRepository.findById(user.getId()).get().getCars();

        Assertions.assertTrue(actualUserCars.isEmpty());

    }

    @Test
    void getUserCars_whenExists_thenReturn() {
        var address = AddressEntity.builder().address("my_address").build();
        var user = UsersEntity.builder().name("andrey").address(address).build();
        user = userRepository.save(user);
        var car = CarsEntity.builder().brand("BMW").number("number").build();
        car = carsRepository.save(car);
        user.addCar(car);
        userRepository.save(user);

        var userCars = carRegistrationService.getUserCars(user.getId());
        Assertions.assertEquals(userCars.getCarId().size(), 1);
        Assertions.assertEquals(userCars.getUserId(), user.getId());
        var actualCar = userCars.getCarId().stream().findAny().get();
        Assertions.assertEquals(car.getBrand(), actualCar.getBrand());
        Assertions.assertEquals(car.getNumber(), actualCar.getNumber());

    }
}
