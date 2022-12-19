package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Event;
import cz.uhk.tj_start_rk.model.Match;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MatchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MatchController {
    private MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @JsonView(View.AllMatch.class)
    @GetMapping("/matches")
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @JsonView(View.AllMatch.class)
    @GetMapping("/matches/{id}")
    public Optional<Match> getTeams(@PathVariable int id){
        return matchRepository.findById(id);
    }
    @JsonView(View.AllMatch.class)
    @PostMapping("/matches")
    public Match addMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }
}
