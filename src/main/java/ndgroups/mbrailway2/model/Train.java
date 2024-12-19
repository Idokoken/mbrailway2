package ndgroups.mbrailway2.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainId;
    private String trainCoach;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private Integer availableSeats;
    private Double price;
    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();


    public Train(Integer trainId, String trainCoach, String origin, String destination, LocalDate departureDate,
                 LocalTime departureTime, Integer availableSeats, Double price,
                 List<Reservation> reservations) {
        this.trainId = trainId;
        this.trainCoach = trainCoach;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.price = price;
        this.reservations = reservations;
    }
    public Train() {
    }

    public Integer getTrainId() {
        return trainId;
    }

    public String getTrainCoach() {
        return trainCoach;
    }

    public void setTrainCoach(String trainCoach) {
        this.trainCoach = trainCoach;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
