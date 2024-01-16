package unit11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unit11.data.domain.CarsEntity;
import unit11.data.dto.CarDtoRequest;
import unit11.repository.CarsRepository;
import unit11.service.CarService;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;
    @Autowired
    private CarsRepository carsRepository;

    @AfterEach
    void beforeEach() {
        carsRepository.deleteAll();
    }

    @Test
    void getCar_whenCarExists_thenReturnFromDb() {
        var car = carsRepository.save(CarsEntity.builder().brand("BMW").number("number").build());

        var actualCar = carService.getCar(car.getId());

        Assertions.assertEquals(car.getNumber(), actualCar.getNumber());
        Assertions.assertEquals(car.getBrand(), actualCar.getBrand());

    }

    @Test
    void addCar_thenExistsInDb() {
        carService.addCar(new CarDtoRequest("number", "BMW"));
        var actualCar = carsRepository.findAll().stream().findAny().get();

        Assertions.assertEquals("number", actualCar.getNumber());
        Assertions.assertEquals("BMW", actualCar.getBrand());

    }

    @Test
    void deleteCar_thenNotExistsInDb() {
        var car = carsRepository.save(CarsEntity.builder().brand("BMW").number("number").build());

        carService.deleteCar(car.getId());

        var size = carsRepository.findAll().size();

        Assertions.assertEquals(0, size);
    }

    @Test
    void updateCar_whenCarExists_thenUpdate() {
        var car = carsRepository.save(CarsEntity.builder().brand("BMW").number("number").build());

        carService.updateCar(car.getId(), new CarDtoRequest("a123bc", "BMW"));

        var actualCar = carsRepository.findById(car.getId()).get();

        Assertions.assertEquals("a123bc", actualCar.getNumber());
        Assertions.assertEquals("BMW", actualCar.getBrand());

    }

}
