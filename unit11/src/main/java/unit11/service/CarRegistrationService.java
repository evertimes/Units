package unit11.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import unit11.data.dto.CarDtoResponse;
import unit11.data.dto.UserCarResponse;
import unit11.repository.CarsRepository;
import unit11.repository.UserRepository;

@Service
@AllArgsConstructor
public class CarRegistrationService {

    private CarsRepository carsRepository;
    private UserRepository userRepository;

    @Transactional
    public UserCarResponse getUserCars(@PathVariable("userId") int id) {
        var userCars = carsRepository.findByUsers_Id(id);
        var cars = userCars.stream().map(e -> new CarDtoResponse(e.getId(), e.getNumber(), e.getBrand())).toList();
        return UserCarResponse.builder().userId(id).carId(cars).build();
    }

    @Transactional
    public void removeCarFromUser(int userId, int carId) {
        var user = userRepository.getReferenceById(userId);
        user.removeCar(carsRepository.getReferenceById(carId));
        userRepository.save(user);
    }

    @Transactional
    public void addCarToUser(int userId, int carId) {
        var user = userRepository.getReferenceById(userId);
        user.addCar(carsRepository.getReferenceById(carId));
        userRepository.save(user);
    }
}
