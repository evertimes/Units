package unit7.repository;

import java.sql.SQLException;
import unit7.connection.ConnectionProvider;
import unit7.domain.Car;

public class CarRepository {

    private final static String INSERT_CAR = "INSERT INTO cars VALUES (?,?,?)";
    private final static String UPDATE_CAR = "UPDATE cars SET number = ?, brand = ? WHERE id = ?";
    private final static String DELETE_CAR = "DELETE from cars WHERE id = ?";
    private final static String SELECT_CAR = "SELECT id, number, brand from cars WHERE id = ?";

    private ConnectionProvider connectionProvider = new ConnectionProvider();

    public void save(Car car) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(INSERT_CAR)) {
            ps.setInt(1, car.getId());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getNumber());
            var insertCount = ps.executeUpdate();
            System.out.printf("Inserted %d entries\n", insertCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Car findById(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(SELECT_CAR)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                rs.next();
                return new Car(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Car car) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(UPDATE_CAR)) {
            ps.setString(1, car.getNumber());
            ps.setString(2, car.getBrand());
            ps.setInt(3, id);
            var updateCount = ps.executeUpdate();
            System.out.printf("Updated %d entries\n", updateCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(DELETE_CAR)) {
            ps.setInt(1, id);
            var deleteCount = ps.executeUpdate();
            System.out.printf("Deleted %d entries\n", deleteCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
