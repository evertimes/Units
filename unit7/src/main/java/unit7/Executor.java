package unit7;

import unit7.domain.Address;
import unit7.domain.Car;
import unit7.domain.User;
import unit7.repository.AddressRepository;
import unit7.repository.CarRegistryRepository;
import unit7.repository.CarRepository;
import unit7.repository.UserRepository;

public class Executor {

    private AddressRepository addressRepository;
    private CarRepository carRepository;
    private UserRepository userRepository;
    private CarRegistryRepository carRegistryRepository;

    public Executor() {
        addressRepository = new AddressRepository();
        carRepository = new CarRepository();
        userRepository = new UserRepository();
        carRegistryRepository = new CarRegistryRepository();
    }

    public void executeAll() {
        System.out.println("получение количества автомобилей по каждому клиенту (на экран вывести id клиента, "
                           + "ФИО и количество автомобилей; отсортировать результат по ФИО в алфавитном порядке)");
        System.out.println(carRegistryRepository.getClientCarStatistics());
        System.out.println("переделать предыдущий запрос таким образом, чтобы на экран вывелись клиенты, "
                           + "у которых больше 2 машин\n");
        System.out.println(carRegistryRepository.getClientCarStatisticsMoreThatTwoCars());
        System.out.println("получение ФИО всех клиентов, у которых есть автомобиль марки BMW");
        System.out.println(carRegistryRepository.getUserNamesByCarBrand("BMW"));
        System.out.println("Получение автомобилей по данным клиента\n");
        System.out.println(carRegistryRepository.getCarsByUser(new User(1, null, 0)));
        System.out.println("удаление всех записей об автомобилях у определенного клиента и получение снова");
        carRegistryRepository.deleteCarsByUser(new User(1, null, 0));
        System.out.println(carRegistryRepository.getCarsByUser(new User(1, null, 0)));

        System.out.println("\nUSERS CRUD\n");
        userRepository.save(new User(6, "Misha", 1));
        userRepository.update(6, new User(6, "Misha", 2));
        System.out.println(userRepository.findById(6));
        userRepository.delete(6);

        System.out.println("\nADDRESS CRUD\n");
        addressRepository.save(new Address(10, "new address"));
        addressRepository.update(10, new Address(10, "Ryazan, Gorkovo st. d. 12"));
        System.out.println(addressRepository.findById(10));
        addressRepository.delete(10);

        System.out.println("\nCARS CRUD\n");
        carRepository.save(new Car(15, "1HBT23", "BMW"));
        carRepository.update(15, new Car(15, "A109ET", "BMW"));
        System.out.println(carRepository.findById(15));
        carRepository.delete(15);
    }
}
