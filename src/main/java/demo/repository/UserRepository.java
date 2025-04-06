package demo.repository;

import demo.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    @Inject
    EntityManager entityManager;

    public boolean doseNotExist(String sub) {
        return entityManager
            .createQuery(
                "select case when count(u) > 0 then false else true end from User u where idpSub = :sub",
                Boolean.class)
            .setParameter("sub", sub)
            .getSingleResult();
    }
}
