package unit8;

import org.hibernate.Transaction;
import unit8.data.domain.AddressEntity;
import unit8.data.domain.CarsEntity;
import unit8.data.domain.UsersEntity;
import unit8.db.SessionFactorySingleton;
import unit8.repository.AddressRepository;
import unit8.repository.CarRegistryRepository;
import unit8.repository.CarRepository;
import unit8.repository.UserRepository;

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
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("получение количества автомобилей по каждому клиенту (на экран вывести id клиента, "
                               + "ФИО и количество автомобилей; отсортировать результат по ФИО в алфавитном порядке)");
            System.out.println(carRegistryRepository.getClientCarStatistics());
            System.out.println("переделать предыдущий запрос таким образом, чтобы на экран вывелись клиенты, "
                               + "у которых больше 2 машин\n");
            System.out.println(carRegistryRepository.getClientCarStatisticsMoreThatTwoCars());
            System.out.println("получение ФИО всех клиентов, у которых есть автомобиль марки BMW");
            System.out.println(carRegistryRepository.getUserNamesByCarBrand("BMW"));
            System.out.println("Получение автомобилей по данным клиента\n");
            var user = userRepository.findById(1);
            var carsByUser = carRegistryRepository.getCarsByUser(user);
            System.out.println(carsByUser);
            System.out.println("удаление всех записей об автомобилях у определенного клиента и получение снова");
            carRegistryRepository.deleteCarsByUser(userRepository.findById(1));
            System.out.println(carRegistryRepository.getCarsByUser(userRepository.findById(1)));

            System.out.println("\nUSERS CRUD\n");
            UsersEntity misha = UsersEntity.builder().name("Misha").addressId(addressRepository.findById(1)).build();
            userRepository.save(misha);
            misha = userRepository.findById(6);
            misha.setAddressId(addressRepository.findById(2));
            userRepository.update(misha);
            System.out.println(userRepository.findById(6));
            userRepository.delete(misha);

            System.out.println("\nADDRESS CRUD\n");
            var address = AddressEntity.builder().address("new address").build();
            addressRepository.save(address);
            address.setAddress("Ryazan, Gorkovo st. d. 12");
            addressRepository.update(address);
            System.out.println(addressRepository.findById(address.getId()));
            addressRepository.delete(address);

            System.out.println("\nCARS CRUD\n");
            var car = CarsEntity.builder().brand("BMW").number("1HBT23").build();
            carRepository.save(car);
            car.setNumber("1HBT23");
            carRepository.update(car);
            System.out.println(car.getId() + " CAR ID");
            System.out.println(carRepository.findById(car.getId()));
            carRepository.delete(car);
        } finally {
            tx.rollback();
            session.close();
        }
    }
}
