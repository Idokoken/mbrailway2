//package ndgroups.mbrailway.controller;
//
//import ndgroups.mbrailway.model.Train;
//import ndgroups.mbrailway.model.Ticket;
//import ndgroups.mbrailway.model.User;
//import ndgroups.mbrailway.service.ReservationService;
//import ndgroups.mbrailway.service.TicketService;
//import ndgroups.mbrailway.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/tickets")
//public class TicketController {
//    @Autowired
//    private TicketService ticketService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ReservationService reservationService;
//
//    @GetMapping("/{id}")
//    public String viewUserTickets(@PathVariable Integer id, Model model) {
//        List<Ticket> tickets = ticketService.getTicketsForReservation(id);
//        model.addAttribute("tickets", tickets);
//        return "user/userTickets";
//    }
//
////    @PostMapping("/book")
////    public Ticket bookTicket(@ModelAttribute Ticket ticket) {
////        // Retrieve user by ID (authentication required)
////        User user = userService.getOneUser(ticket.getId());
////        // Retrieve train journey by ID
////        Train reservation = reservationService.getOneReservation(ticket.getId());
////        return ticketService.bookTicket(user, reservation);
////    }
//
//    @DeleteMapping("/{ticketId}")
//    public void cancelTicket(@PathVariable Integer ticketId) {
//        ticketService.cancelTicket(ticketId);
//    }
//}
