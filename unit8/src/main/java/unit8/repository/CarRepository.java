package unit8.repository;

import unit8.data.domain.CarsEntity;
import unit8.db.SessionFactorySingleton;

public class CarRepository {

    public void save(CarsEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.persist(car);

    }

    public CarsEntity findById(int id) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        return session.find(CarsEntity.class, id);

    }

    public void update(CarsEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.merge(car);

    }

    public void delete(CarsEntity car) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.remove(car);

    }
}
