package ndgroups.mbrailway2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainId;
    private String trainName;
    private String trainCoach;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private Double price;
    @CreationTimestamp
    private LocalDateTime reservationTime;
    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}
