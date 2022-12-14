package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Match;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.MatchRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MatchController {
    private final MatchRepository matchRepository;

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
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @PostMapping("/matches")
    public Match addMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @JsonView(View.AllMatch.class)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @PutMapping("/matches")
    public Match updateMatch(@RequestBody Match match) {
        Match update = matchRepository.getReferenceById(match.getId());

        update.update(match);

        return matchRepository.save(update);
    }

    @JsonView(View.AllMatch.class)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @DeleteMapping("/matches/{id}")
    public void deleteMatchById(@PathVariable int id){
        matchRepository.deleteById(id);
    }
}
