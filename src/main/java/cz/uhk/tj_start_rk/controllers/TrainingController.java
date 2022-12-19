package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Training;
import cz.uhk.tj_start_rk.repositories.TrainingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingController {

    private TrainingRepository trainingRepository;

    @GetMapping("/Trainings")
    public List<Training> getTrainings(){
        return trainingRepository.findAll();
    }
}
