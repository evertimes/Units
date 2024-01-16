package unit10.repository;

import unit10.data.domain.AddressEntity;
import unit10.db.SessionFactorySingleton;

public class AddressRepository {

    public void save(AddressEntity address) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.persist(address);
    }

    public AddressEntity findById(int id) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        return session.find(AddressEntity.class, id);
    }

    public void update(AddressEntity addressEntity) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.merge(addressEntity);

    }

    public void delete(AddressEntity addressEntity) {
        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        session.remove(addressEntity);

    }

}
