package unit8.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import unit8.data.domain.CarsEntity;
import unit8.data.domain.UsersEntity;
import unit8.data.dto.ClientCarStatisticsDto;
import unit8.db.SessionFactorySingleton;

public class CarRegistryRepository {

    private final String GET_NAMES_BY_CAR_BRAND = "SELECT user from UsersEntity user "
                                                  + "JOIN FETCH user.cars car "
                                                  + "WHERE car.brand = ?1 ";
    private final String CAR_STAT_GROUP_BY_USERS = "SELECT "
                                                   + "new unit8.data.dto.ClientCarStatisticsDto(user.id,COUNT(car.id)) "
                                                   + "from UsersEntity user "
                                                   + "JOIN user.cars car "
                                                   + "GROUP BY user.id";
    private final String CAR_STAT_GROUP_BY_USERS_MORE_THAN_TWO_CARS =
        "SELECT new unit8.data.dto.ClientCarStatisticsDto(user.id,COUNT(car.id)) "
        + "from UsersEntity user "
        + "JOIN user.cars car "
        + "GROUP BY user.id "
        + "HAVING COUNT(car.id) > 2"
        + "ORDER BY user.name";

    public Set<CarsEntity> getCarsByUser(UsersEntity user) {
        var result = user.getCars();
        return result;
    }

    public Set<String> getUserNamesByCarBrand(String brand) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        var query = session.createQuery(GET_NAMES_BY_CAR_BRAND, UsersEntity.class);
        query.setParameter(1, brand);
        var result = query.list().stream().map(UsersEntity::getName).collect(Collectors.toSet());
        return result;

    }

    public void deleteCarsByUser(UsersEntity user) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        user.setCars(new HashSet<>());
        session.merge(user);
    }

    public List<ClientCarStatisticsDto> getClientCarStatistics() {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        var query = session.createQuery(CAR_STAT_GROUP_BY_USERS, ClientCarStatisticsDto.class);
        var result = query.list();
        return result;

    }

    public List<ClientCarStatisticsDto> getClientCarStatisticsMoreThatTwoCars() {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        var query = session.createQuery(CAR_STAT_GROUP_BY_USERS_MORE_THAN_TWO_CARS, ClientCarStatisticsDto.class);
        var result = query.list();
        return result;

    }
}
