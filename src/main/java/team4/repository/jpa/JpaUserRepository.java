package team4.repository.jpa;

import org.springframework.stereotype.Repository;
import team4.repository.UserRepository;
import team4.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liren
 */
@Repository
@Transactional
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return (List<User>) em.createQuery("from User", User.class).getResultList();
    }

    public User findByUsername(String username) {
        return (User) em.createQuery(
                "select u from User u where u.username = ?1").setParameter(1, username)
                .getSingleResult();
    }
}
