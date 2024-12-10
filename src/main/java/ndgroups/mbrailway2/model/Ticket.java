package ndgroups.mbrailway2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "reservation_id")
//    private Train reservation;
    @CreationTimestamp
    private LocalDateTime bookingTime;
    private Integer numberOfPassengers;

//    public Ticket(User user, Train reservation) {
//        this.user = user;
//        this.reservation = reservation;
//    }
}
