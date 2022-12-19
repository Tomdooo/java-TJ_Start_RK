package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity(name="matches")
public class Match {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;

    @JsonView(View.AllMatch.class)
    private String header;
    @JsonView(View.AllMatch.class)
    private String note;

    @JsonView(View.AllMatch.class)
    private Date start;

    @ManyToMany
    @JsonView(View.AllMatch.class)
    private List<Team> teams;
    //TODO není lepší dva týmy jako domácí a hosté než seznam týmů

    // CONSTRUCTORS
    public Match(String header, String note, List<Team> teams, Date start) {
        this.header = header;
        this.note = note;
        this.teams = teams;
        this.start = start;
    }

    public Match() {

    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getNote() {
        return note;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Date getStart() {
        return start;
    }

    // SETTERS
    public void setHeader(String header) {
        this.header = header;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
