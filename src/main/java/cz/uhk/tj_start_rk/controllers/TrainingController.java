package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Training;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.TrainingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// TODO upravovat jen svoje tréninky
@CrossOrigin
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
    public Training addTraining(@RequestBody Training training) {
        return trainingRepository.save(training);
    }

    @JsonView(View.AllTraining.class)
    @PutMapping("/trainings")
    public Training updateTraining(@RequestBody Training training) {
        Training update = trainingRepository.getReferenceById(training.getId());

        update.update(training);

        return trainingRepository.save(update);
    }

    @JsonView(View.AllTraining.class)
    @DeleteMapping("/trainings/{id}")
    public void deleteTrainingById(@PathVariable int id){
       trainingRepository.deleteById(id);
    }
}
