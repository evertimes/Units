package unit10.repository;

import org.hibernate.Transaction;
import unit10.data.domain.UsersEntity;
import unit10.db.SessionFactorySingleton;

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

    public void update(UsersEntity user) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.merge(user);

    }

    public void delete(UsersEntity user) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.remove(user);

    }
}
