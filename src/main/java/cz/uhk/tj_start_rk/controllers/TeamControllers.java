package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamControllers {
    private TeamRepository teamRepository;

    public TeamControllers(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @JsonView(View.AllTeam.class)
    @GetMapping("/teams")
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    @JsonView(View.AllTeam.class)
    @GetMapping("/teams/{id}")
    public Optional<Team> getTeams(@PathVariable int id){
        return teamRepository.findById(id);
    }
}
