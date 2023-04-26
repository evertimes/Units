package unit7.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unit7.connection.ConnectionProvider;
import unit7.domain.Car;
import unit7.domain.ClientCarStatisticsDto;
import unit7.domain.User;

public class CarRegistryRepository {

    private final String GET_CARS_BY_USER = "SELECT id, number, brand FROM cars "
                                            + "JOIN users_cars ON cars.id=users_cars.car_id  "
                                            + "WHERE user_id = ?";
    private final String GET_NAMES_BY_CAR_BRAND = "SELECT name from users "
                                                  + "JOIN users_cars ON users.id=users_cars.user_id "
                                                  + "JOIN cars ON users_cars.car_id=cars.id "
                                                  + "WHERE cars.brand = ?";
    private final String DELETE_CARS_BY_USER = "DELETE from users_cars WHERE user_id = ?";
    private final String CAR_STAT_GROUP_BY_USERS = "SELECT users.id,users.name,count(car_id) from users "
                                                   + "JOIN users_cars on users.id = users_cars.user_id "
                                                   + "GROUP BY users.id ORDER BY users.name";
    private final String CAR_STAT_GROUP_BY_USERS_MORE_THAN_TWO_CARS =
        "SELECT users.id,users.name, count(car_id) FROM users "
        + "JOIN users_cars on users.id = users_cars.user_id "
        + "GROUP BY users.id  HAVING count(car_id) > 2 ORDER BY users.name";

    private final ConnectionProvider connectionProvider = new ConnectionProvider();

    public List<Car> getCarsByUser(User user) {
        List<Car> cars = new ArrayList<>();
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(GET_CARS_BY_USER)) {
            ps.setInt(1, user.getId());
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    public List<String> getUserNamesByCarBrand(String brand) {
        List<String> names = new ArrayList<>();
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(GET_NAMES_BY_CAR_BRAND)) {
            ps.setString(1, brand);
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    names.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return names;
    }

    public void deleteCarsByUser(User user) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(DELETE_CARS_BY_USER)) {
            ps.setInt(1, user.getId());
            var countUpdated = ps.executeUpdate();
            System.out.printf("Deleted %d entries\n", countUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientCarStatisticsDto> getClientCarStatistics() {
        List<ClientCarStatisticsDto> statisticsDtos = new ArrayList<>();
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(CAR_STAT_GROUP_BY_USERS);
            var rs = ps.executeQuery()) {
            while (rs.next()) {
                statisticsDtos.add(new ClientCarStatisticsDto(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statisticsDtos;
    }

    public List<ClientCarStatisticsDto> getClientCarStatisticsMoreThatTwoCars() {
        List<ClientCarStatisticsDto> statisticsDtos = new ArrayList<>();
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(CAR_STAT_GROUP_BY_USERS_MORE_THAN_TWO_CARS);
            var rs = ps.executeQuery()) {
            while (rs.next()) {
                statisticsDtos.add(new ClientCarStatisticsDto(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statisticsDtos;
    }

}
