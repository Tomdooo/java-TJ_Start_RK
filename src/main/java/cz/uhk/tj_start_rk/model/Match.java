package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name="matches")
public class Match {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;

    @JsonView(View.AllMatch.class)
    @NotNull
    private String league;
    @JsonView(View.AllMatch.class)
    private String note;

    @JsonView(View.AllMatch.class)
    @NotNull
    private Date start;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonView(View.AllMatch.class)
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonView(View.AllMatch.class)
    private Team awayTeam;

    // CONSTRUCTORS
    public Match(String league, String note, Team homeTeam, Team awayTeam, Date start) {
        this.league = league;
        this.note = note;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.start = start;
    }

    public Match() {

    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getLeague() {
        return league;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getStart() {
        return start;
    }

    // SETTERS
    public void setNote(String note) {
        this.note = note;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    // UPDATE
    public void update(Match match) {
        this.note = match.getNote();
        this.league = match.getLeague();
        this.homeTeam = match.getHomeTeam();
        this.awayTeam = match.getAwayTeam();
        this.start = match.getStart();
    }
}
