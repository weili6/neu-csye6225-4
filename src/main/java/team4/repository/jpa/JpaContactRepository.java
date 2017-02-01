package team4.repository.jpa;

import org.springframework.stereotype.Repository;
import team4.repository.ContactRepository;
import team4.domain.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liren
 */
@Repository
@Transactional
public class JpaContactRepository implements ContactRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Contact contact) {
        em.persist(contact);
    }

    public List<Contact> findAll() {
        return (List<Contact>) em.createQuery("from Contact", Contact.class).getResultList();
    }
}