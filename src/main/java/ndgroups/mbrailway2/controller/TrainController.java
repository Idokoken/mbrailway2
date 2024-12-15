package ndgroups.mbrailway2.controller;

import ndgroups.mbrailway2.model.Train;
import ndgroups.mbrailway2.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping("/add")
    public String getAddTrainPage(@ModelAttribute("train") Train train) {
        return "admin/train/addTrain";
    }
    @PostMapping("/add")
    public String AddTrain(@ModelAttribute("train") Train train, Model model) {
        Train newTrain  =  trainService.saveTrain(train);
        model.addAttribute("message", "Train successfully created");
        return "admin/train/addTrain";
    }

    @GetMapping
    public String getAllTrain(Model model) {
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getOneTrain(@PathVariable Integer id, Model model) {
        Train train = trainService.getOneTrain(id);
        model.addAttribute("train", train);
        return "admin/train/singleTrain";
    }

    @GetMapping("/update/{id}")
    public String getUpdateTrainPage(@PathVariable Integer id, Model model) {
        Train getTrain  = trainService.getOneTrain(id);
        model.addAttribute("train", getTrain);
        return "admin/train/editTrain";
    }
    @PostMapping("/update/{id}")
    public String updateTrain(Model model, @PathVariable Integer id, @ModelAttribute("train") Train
            updateTrain)  {
        trainService.updateTrain(id, updateTrain);
        return "redirect:/admin?success";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTrain(Model model, @PathVariable Integer id)  {
        trainService.deleteTrain(id);
        return "redirect:/admin?delmessage";
    }

}
