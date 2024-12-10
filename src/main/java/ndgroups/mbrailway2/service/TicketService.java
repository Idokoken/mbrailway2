//package ndgroups.mbrailway.service;
//
//import jakarta.persistence.EntityNotFoundException;
//import ndgroups.mbrailway.model.Train;
//import ndgroups.mbrailway.model.Ticket;
//import ndgroups.mbrailway.model.User;
//import ndgroups.mbrailway.repository.ReservationRepository;
//import ndgroups.mbrailway.repository.TicketRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TicketService {
//    @Autowired
//    private TicketRepository ticketRepository;
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    public Ticket getTicketById(Integer id) {
//        return ticketRepository.findById(id).
//                orElseThrow(() -> new EntityNotFoundException("no ticket with id of: " + id));
//    }
//    public List<Ticket> getTicketsForReservation(Integer id) {
//        Train reservation = reservationRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Reservation not found"));
//        return ticketRepository.findByReservation(reservation);
//    }
//    public List<Ticket> getUsersTickets() {
//
//        return ticketRepository.findAll();
//    }
//
////    public Ticket bookTicket(User user, Train reservation) {
////        Ticket ticket = new Ticket();
////        ticket.setUser(user);
////        ticket.setReservation(reservation);
//////        ticket.setBookingTime(LocalDateTime.now());
////        return ticketRepository.save(ticket);
////    }
//
//    public void cancelTicket(Integer id) {
//        if (!ticketRepository.existsById(id)) {
//            throw new EntityNotFoundException("no ticket with id of: " + id);
//        }
//        ticketRepository.deleteById(id);
//    }
//}
