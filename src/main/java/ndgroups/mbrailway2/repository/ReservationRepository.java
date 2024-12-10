package ndgroups.mbrailway2.repository;

import ndgroups.mbrailway2.model.Reservation;
import ndgroups.mbrailway2.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findBySeatNumber(Integer seatNumber);
    List<Reservation> findByTrain(Train train);
    Optional<Reservation> findByBookingConfirmationCode(String confirmationCode);
    List<Reservation>findByUserId(Integer userId);

}
