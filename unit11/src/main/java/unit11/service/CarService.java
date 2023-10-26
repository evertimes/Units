package unit11.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import unit11.data.domain.CarsEntity;
import unit11.data.dto.CarDtoRequest;
import unit11.data.dto.CarDtoResponse;
import unit11.data.dto.UserCarResponse;
import unit11.repository.CarsRepository;

@Service
@AllArgsConstructor
public class CarService {

    private CarsRepository carsRepository;

    public CarDtoResponse getCar(@PathVariable("carId") int id) {
        var car = carsRepository.getReferenceById(id);
        return new CarDtoResponse(car.getId(), car.getNumber(), car.getBrand());
    }

    public void updateCar(@PathVariable("carId") int id, @RequestBody CarDtoRequest carDto) {
        var car = carsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        car.setBrand(carDto.getBrand());
        car.setNumber(carDto.getNumber());
        carsRepository.save(car);
    }

    public void addCar(@RequestBody CarDtoRequest carDto) {
        var carEntity = CarsEntity.builder().brand(carDto.getBrand()).number(carDto.getNumber()).build();
        carsRepository.save(carEntity);
    }

    public void deleteCar(@PathVariable("carId") int id) {
        carsRepository.deleteById(id);
    }
}
