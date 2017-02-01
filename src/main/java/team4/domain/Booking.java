package team4.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by liren
 */
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name="spotId")
    private Spot spot;
    @ManyToOne
    @JoinColumn(name="renterId")
    private User renter;
    private double rate;
    private double total;
    private ZonedDateTime orderedOn;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    protected Booking(){

    }

    public Booking(Long bookingId, Spot spot, User renter, double rate, double total, ZonedDateTime orderedOn, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.bookingId = bookingId;
        this.spot = spot;
        this.renter = renter;
        this.rate = rate;
        this.total = total;
        this.orderedOn = orderedOn;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ZonedDateTime getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(ZonedDateTime orderedOn) {
        this.orderedOn = orderedOn;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }
}
