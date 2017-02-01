package team4.repository.jpa;

import org.springframework.stereotype.Repository;
import team4.repository.SpotRepository;
import team4.domain.Spot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liren
 */
@Repository
@Transactional
public class JpaSpotRepository implements SpotRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Spot spot) {
        em.persist(spot);
    }

    public List<Spot> findAll() {
        return (List<Spot>) em.createQuery("from Spot", Spot.class).getResultList();
    }
}
