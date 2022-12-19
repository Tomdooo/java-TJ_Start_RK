package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Training;
import cz.uhk.tj_start_rk.repositories.TrainingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class TrainingController {

    private TrainingRepository trainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/trainings")
    public List<Training> getTrainings(){
        return trainingRepository.findAll();
    }
    @GetMapping("/trainings/{id}")
    public Optional<Training> getTrainings(@PathVariable int id){
        return trainingRepository.findById(id);
    }
}
