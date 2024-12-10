package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable Integer id, Model model) {
        User user = userService.getOneUser(id);
        model.addAttribute("user", user);
        return "user/userDashboard";
    }

    @GetMapping("/edit/{id}")
    public String getUpdateUserPage(@PathVariable Integer id, Model model) {
        User getUser  = userService.getOneUser(id);
        model.addAttribute("user", getUser);
        return "user/editProfile";
    }
    @PostMapping("/edit/{id}")
    public String updateUser(Model model, @PathVariable Integer id, @ModelAttribute("user") User
            updateUser)  {
        userService.updateUser(id, updateUser);
        return "redirect:/users/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


}
