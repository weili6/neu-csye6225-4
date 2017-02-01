package team4.repository;


import team4.domain.Booking;

import java.util.List;

/**
 * Created by liren
 */
public interface BookingRepository {

    void save(Booking booking);

    public List<Booking> findAll();

}
