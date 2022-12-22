package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.TeamRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @JsonView(View.AllTeam.class)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @PostMapping("/teams")
    public Team addTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @JsonView(View.AllTeam.class)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @PutMapping("/teams")
    public Team updateTeam(@RequestBody Team team) {
        Team update = teamRepository.getReferenceById(team.getId());

        update.update(team);

        return teamRepository.save(update);
    }

    @JsonView(View.AllTeam.class)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'MODERATOR'})")
    @DeleteMapping("/teams/{id}")
    public void deleteTeamById(@PathVariable int id){
        teamRepository.deleteById(id);
    }
}
