package unit7.repository;

import java.sql.SQLException;
import unit7.connection.ConnectionProvider;
import unit7.domain.User;

public class UserRepository {

    private final static String INSERT_USER = "INSERT INTO users VALUES (?,?,?)";
    private final static String UPDATE_USER = "UPDATE users SET name = ?, address_id = ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE from users WHERE id = ?";
    private final static String SELECT_USER = "SELECT id, name, address_id FROM users WHERE id = ?";

    private ConnectionProvider connectionProvider = new ConnectionProvider();

    public void save(User user) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(INSERT_USER)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getAddressId());
            var insertCount = ps.executeUpdate();
            System.out.printf("Inserted %d entries\n", insertCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(SELECT_USER)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                rs.next();
                return new User(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, User user) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAddressId());
            ps.setInt(3, id);
            var updateCount = ps.executeUpdate();
            System.out.printf("Updated %d entries\n", updateCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, id);
            var deleteCount = ps.executeUpdate();
            System.out.printf("Deleted %d entries\n", deleteCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
