package unit11.resource;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit11.data.dto.UserCarResponse;
import unit11.service.CarRegistrationService;

@RequestMapping("/car-registration")
@AllArgsConstructor
@RestController
public class CarRegistrationResource {

    private CarRegistrationService carRegistrationService;

    @GetMapping("/{userId}")
    @Operation(summary = "getUserCars")
    public UserCarResponse getUserCars(@PathVariable("userId") int userId) {
        return carRegistrationService.getUserCars(userId);
    }

    @DeleteMapping("/{userId}/{carId}")
    public void removeCarFromUser(@PathVariable("userId") int userId, @PathVariable("carId") int carId) {
        carRegistrationService.removeCarFromUser(userId, carId);
    }

    @PostMapping("/{userId}/{carId}")
    public void addCarForUser(@PathVariable("userId") int userId, @PathVariable("carId") int carId) {
        carRegistrationService.addCarToUser(userId, carId);
    }
}
