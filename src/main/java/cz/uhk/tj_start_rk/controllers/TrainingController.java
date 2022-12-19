package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.model.Training;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.TrainingRepository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class TrainingController {

    private TrainingRepository trainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @JsonView(View.AllTraining.class)
    @GetMapping("/trainings")
    public List<Training> getTrainings(){
        return trainingRepository.findAll();
    }

    @JsonView(View.AllTraining.class)
    @GetMapping("/trainings/{id}")
    public Optional<Training> getTrainings(@PathVariable int id){
        return trainingRepository.findById(id);
    }
    @JsonView(View.AllTraining.class)
    @PostMapping("/trainings")
    public Training addTeam(@RequestBody Training training) {
        return trainingRepository.save(training);
    }
}
