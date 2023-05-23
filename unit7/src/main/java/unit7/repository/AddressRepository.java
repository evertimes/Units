package unit7.repository;

import java.sql.SQLException;
import unit7.connection.ConnectionProvider;
import unit7.domain.Address;

public class AddressRepository {

    private final static String INSERT_ADDRESS = "INSERT INTO addresses VALUES (?,?,false)";
    private final static String UPDATE_ADDRESS = "UPDATE addresses SET address = ?, version = version + 1 WHERE id = ? "
                                                 + "AND version = ?";
    private final static String DELETE_ADDRESS = "UPDATE addresses SET deleted = true, version = version + 1 "
                                                 + "WHERE id =? AND version = ?";
    private final static String SELECT_ADDRESS = "SELECT id, address from addresses WHERE id = ? AND deleted = false";
    private final static String GET_VERSION = "SELECT version from addresses where id = ?";

    private ConnectionProvider connectionProvider = new ConnectionProvider();

    public void save(Address address) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(INSERT_ADDRESS)) {
            ps.setInt(1, address.getId());
            ps.setString(2, address.getAddress());
            var insertCount = ps.executeUpdate();
            System.out.printf("Inserted %d entries\n", insertCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Address findById(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(SELECT_ADDRESS)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                rs.next();
                return new Address(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Address address) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(UPDATE_ADDRESS)) {
            ps.setString(1, address.getAddress());
            ps.setInt(2, id);
            ps.setInt(3, getVersion(id));
            var updateCount = ps.executeUpdate();
            if (updateCount == 0) {
                System.err.println("Optimistic locking failure");
            } else {
                System.out.printf("Inserted %d entries\n", updateCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(DELETE_ADDRESS)) {
            ps.setInt(1, id);
            ps.setInt(2, getVersion(id));

            var deleteCount = ps.executeUpdate();
            if (deleteCount == 0) {
                System.err.println("Optimistic locking failure");
            } else {
                System.out.printf("Deleted %d entries\n", deleteCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getVersion(int id) {
        try (var connection = connectionProvider.getConnection();
            var ps = connection.prepareStatement(GET_VERSION)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
