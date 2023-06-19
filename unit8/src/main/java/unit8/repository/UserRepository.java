package unit8.repository;

import unit8.data.domain.UsersEntity;
import unit8.db.SessionFactorySingleton;

public class UserRepository {

    public void save(UsersEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.persist(car);
    }

    public UsersEntity findById(int id) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        var result = session.find(UsersEntity.class, id);
        return result;

    }

    public void update(UsersEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.merge(car);

    }

    public void delete(UsersEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.remove(car);

    }
}
