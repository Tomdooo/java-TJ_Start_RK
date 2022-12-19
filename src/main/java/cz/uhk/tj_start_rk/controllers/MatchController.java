package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Match;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.repositories.MatchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MatchController {
    private MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }
    @GetMapping("/matches/{id}")
    public Optional<Match> getTeams(@PathVariable int id){
        return matchRepository.findById(id);
    }
}
