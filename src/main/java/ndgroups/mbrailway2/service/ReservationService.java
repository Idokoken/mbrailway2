package ndgroups.mbrailway2.service;

import ndgroups.mbrailway2.exception.OurException;
import ndgroups.mbrailway2.model.Reservation;
import ndgroups.mbrailway2.model.Train;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.repository.ReservationRepository;
import ndgroups.mbrailway2.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ndgroups.mbrailway2.repository.TrainRepository;
import ndgroups.mbrailway2.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainRepository trainRepository;



//    public Reservation createReservation(Integer trainId, Integer userId, Reservation reservationRequest) {
//        Reservation reservation = new Reservation();
//        try {
//            User user = userRepository.findById(userId).orElseThrow(() -> new OurException("user not found"));
//            Train train = trainRepository.findById(trainId).orElseThrow(() -> new OurException("user not found"));
//
//            //            List<Reservation>existingReservations = train.getReservations();
//
////            if(!roomIsAvailable(bookingRequest, existingBookings)){
////                throw new OurException("Room not available for selected date range");
////            }
//            reservationRequest.setTrain(train);
//            reservationRequest.setUser(user);
//            String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
//            reservationRequest.setBookingConfirmationCode(bookingConfirmationCode);
//            reservation = reservationRepository.save(reservationRequest);
//
//        }catch (Exception e){
//            throw new IllegalArgumentException("Error saving the booking " + e.getMessage());
//        }
//        return reservation;
//    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getOneReservation(Integer id) {
        return reservationRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("no reservation with the given id: " + id));
    }

//    public List<Reservation> searchReservations(String origin, String destination, LocalDate filterDate) {
//        List<Reservation> listReservations = reservationRepository
//                .findByOriginAndDestinationAndDepartureDateAndAvailableSeats(origin, destination, filterDate);
//        return listReservations;
//    }

    public Reservation findReservationByConfirmationCode(String confirmationCode) {
            Reservation reservation = reservationRepository.findByBookingConfirmationCode(confirmationCode).
                    orElseThrow(() -> new OurException("reservation not found"));
            return reservation;

    }


    public void cancelReservation(Integer id) {
        if(!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("no reservation with the id of" + id);
        }
        reservationRepository.deleteById(id);
    }





//    private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {
//        return existingBookings.stream().noneMatch(existingBooking ->
//                bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
//
//                        || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
//                        || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
//                        && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
//                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
//
//                        && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
//                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
//
//                        && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
//
//                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
//                        && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
//
//                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
//                        && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
//        );
//    }
//

}
