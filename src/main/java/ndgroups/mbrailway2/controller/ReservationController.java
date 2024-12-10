package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.Reservation;
import ndgroups.mbrailway2.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


//    @PostMapping("/{trainId}{userId}")
//    public String createReservation(@PathVariable Integer trainId, @PathVariable Integer userId,
//                                    @ModelAttribute("reservation") Reservation reservation, Model model){
//        reservationService.createReservation(trainId, userId, reservation);
//        return "admin/bookings/checkout";
//    }

    @GetMapping
//    @PreAuthorize("hasAuthority(ADMIN)")
    public String getReservation(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservation", reservations);
        return "admin/bookings/bookingList";
    }
    @GetMapping("/{code}")
    public String getByConfirmationCode(@PathVariable String code) {
        Reservation reservation = reservationService.findReservationByConfirmationCode(code);
        return "admin/bookings/searchCode";
    }
    @GetMapping("/cancel/{reservationId}")
    //    @PreAuthorize("hasAuthority(ADMIN)")
    public String cancelReservation(@PathVariable Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservations/all";
    }



}

