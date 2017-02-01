package team4.domain;

import javax.persistence.*;

/**
 * Created by liren
 */
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;
    @ManyToOne
    @JoinColumn(name="ownerId")
    private User owner;
    private double rate;
    private double size;
    @Column(nullable = false)
    private String address;

    protected Spot() {
    }

    public Spot(Long spotId, User owner, double rate, double size, String address) {
        this.spotId = spotId;
        this.owner = owner;
        this.rate = rate;
        this.size = size;
        this.address = address;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
