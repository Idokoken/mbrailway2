package ndgroups.mbrailway2.repository;

import ndgroups.mbrailway2.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
//    @Query("SELECT t FROM Train t WHERE t.origin = :origin AND t.destination = :destination AND " +
//            "t.departureDate = :departureDate")
   @Query("SELECT t FROM Train t WHERE " +
           "t.departureDate = :departureDate AND " +
           "(t.origin = :origin OR :origin IS NULL OR :origin = '' OR t.origin IS NOT NULL) AND " +
           "(t.destination = :destination OR :destination IS NULL OR :destination = '' OR t.destination IS NOT NULL)")

   List<Train> findAvailableTrainsByOriginAndDestinationAndDepartureDate(
            @Param("origin") String origin,
            @Param("destination") String destination,
            @Param("departureDate") LocalDate departureDate
    );
}
