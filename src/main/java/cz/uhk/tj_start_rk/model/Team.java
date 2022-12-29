package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name="teams")
public class Team {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;

    @JsonView(View.Base.class)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.DETACH)
    @JsonView(View.AllTeam.class)
    private List<Member> members;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.DETACH)
    @JsonView(View.AllTeam.class)
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.DETACH)
    @JsonView(View.AllTeam.class)
    private List<Match> awayMatches;

    @PreRemove
    private void preRemove(){
        for (Member m : members){
         m.setTeam(null);
        }
        for (Match m:homeMatches){
            m.setHomeTeam(null);
        }
        for (Match m:awayMatches){
            m.setAwayTeam(null);
        }
    }

    // CONSTRUCTORS
    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }

    public Team(String name) {
        this.name = name;
    }

    public Team() {
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Match> getHomeMatches() {
        return homeMatches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setHomeMatches(List<Match> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    // UPDATE
    public void update(Team team) {
        this.name = team.getName();
        this.members = team.getMembers();
        this.homeMatches = team.getHomeMatches();
        this.awayMatches = team.getAwayMatches();
    }
}
