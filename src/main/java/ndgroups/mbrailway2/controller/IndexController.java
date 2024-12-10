package ndgroups.mbrailway2.controller;

import jakarta.validation.Valid;
import ndgroups.mbrailway2.model.PersonForm;
import ndgroups.mbrailway2.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class IndexController implements WebMvcConfigurer {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String getHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/contact")
    public String getContact(PersonForm personForm, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "pages/contact";
    }
    @PostMapping("/contact")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("greeting", "message sent successfully");
        if (bindingResult.hasErrors()) {
            return "pages/contact";
        }
        return "redirect:/results";
    }
    @GetMapping("/results")
    public String getResult() {
        return "results";
    }

    @GetMapping("/about")
    public String getAbout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/about";
    }
    @GetMapping("/faq")
    public String getFAQ(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/FAQ";
    }
    @GetMapping("/timetable")
    public String getTrainTimetable(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "pages/trainTimetable";
    }
    @GetMapping("/terms")
    public String getTeamsPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/termsAndConditions";
    }
    @GetMapping("/privacy")
    public String getPrivacyPolicy(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/privacyPolicy";
    }
    @GetMapping("/cookies")
    public String getCookiesPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/cookies";
    }
    @GetMapping("/disclaimer")
    public String getDisclaimerPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/disclaimer";
    }
    @GetMapping("/blog")
    public String getBlogPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return  "pages/blog";
    }

}
