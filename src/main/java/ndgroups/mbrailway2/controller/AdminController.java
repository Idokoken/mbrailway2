package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.Reservation;
import ndgroups.mbrailway2.model.Train;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.service.ReservationService;
import ndgroups.mbrailway2.service.TrainService;
import ndgroups.mbrailway2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TrainService trainService;

    @GetMapping
    public String getAdminDashboard(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        List<User>users  =  userService.getAllUsers();
        List<Train>trains  =  trainService.getAllTrains();
        model.addAttribute("reservations", reservations);
        model.addAttribute("users", users);
        model.addAttribute("trains", trains);

        return "admin/adminDashboard";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users= userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/listUser";
    }



}
