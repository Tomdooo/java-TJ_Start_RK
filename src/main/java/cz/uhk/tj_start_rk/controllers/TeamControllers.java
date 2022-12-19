package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.repositories.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TeamControllers {
    private TeamRepository teamRepository;

    public TeamControllers(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }
}
