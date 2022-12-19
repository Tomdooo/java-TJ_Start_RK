package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Match;
import cz.uhk.tj_start_rk.repositories.MatchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
