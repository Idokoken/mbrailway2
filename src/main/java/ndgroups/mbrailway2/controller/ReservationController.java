package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.CustomUserDetails;
import ndgroups.mbrailway2.model.Reservation;
import ndgroups.mbrailway2.model.Train;
import ndgroups.mbrailway2.service.ReservationService;
import ndgroups.mbrailway2.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TrainService trainService;

    //    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{trainId}")
    public String getCreateReservationPage(@PathVariable Integer trainId, Model model){
        Train train = trainService.getOneTrain(trainId);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        model.addAttribute("train", train);
//        model.addAttribute("user", userDetails);
        return "admin/bookings/checkout";
    }


//    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{trainId}/{userId}")
    public String createReservation(@PathVariable Integer trainId, @PathVariable Integer userId,
                                    @ModelAttribute("reservation") Reservation reservation, Model model){
        reservationService.createReservation(trainId, userId, reservation);
        return "admin/bookings/checkout";
    }

    @GetMapping
//    @PreAuthorize("hasAuthority(ADMIN)")
    public String getReservation(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservation", reservations);
        return "admin/bookings/bookingList";
    }
    @GetMapping("/code/{code}")
    public String getByConfirmationCode(@PathVariable String code) {
        Reservation reservation = reservationService.findReservationByConfirmationCode(code);
        return "admin/bookings/searchCode";
    }
    @GetMapping("/cancel/{reservationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelReservation(@PathVariable Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservations/all";
    }



}

