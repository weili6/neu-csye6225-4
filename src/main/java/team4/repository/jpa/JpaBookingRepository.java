package team4.repository.jpa;

import org.springframework.stereotype.Repository;
import team4.repository.BookingRepository;
import team4.domain.Booking;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liren
 */
@Repository
@Transactional
public class JpaBookingRepository implements BookingRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Booking booking) {
        em.persist(booking);
    }

    public List<Booking> findAll() {
        return (List<Booking>) em.createQuery("from Booking", Booking.class).getResultList();
    }
}
