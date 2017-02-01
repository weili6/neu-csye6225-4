package team4.repository;

import team4.domain.Spot;

import java.util.List;

/**
 * Created by liren
 */
public interface SpotRepository {

    void save(Spot spot);

    public List<Spot> findAll();
}
