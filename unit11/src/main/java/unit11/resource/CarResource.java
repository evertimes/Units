package unit11.resource;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit11.data.dto.CarDtoRequest;
import unit11.data.dto.CarDtoResponse;
import unit11.data.dto.UserCarResponse;
import unit11.service.CarService;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarResource {

    private CarService carService;

    @GetMapping("/{carId}")
    @Operation(summary = "getCar")
    public CarDtoResponse getCar(@PathVariable("carId") int id){
        return carService.getCar(id);
    }

    @PutMapping("/{carId}")
    @Operation(summary = "updateCar")
    public void updateCar(@PathVariable("carId") int id, @RequestBody CarDtoRequest carDto){
        carService.updateCar(id, carDto);
    }

    @PostMapping
    @Operation(summary = "addCar")
    public void addCar(@RequestBody CarDtoRequest carDto){
        carService.addCar(carDto);
    }

    @DeleteMapping("/{carId}")
    @Operation(summary = "deleteCar")
    public void deleteCar(@PathVariable("carId") int id){
        carService.deleteCar(id);
    }
}
