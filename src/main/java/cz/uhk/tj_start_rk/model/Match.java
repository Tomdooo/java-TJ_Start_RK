package cz.uhk.tj_start_rk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name="matches")
public class Match {
    @Id
    @GeneratedValue
    private int id;

    private String header;
    private String note;

    @ManyToMany
    private List<Team> teams;
    //TODO není lepší dva týmy jako dopmací a hosté než seznam týmů
    //TODO date

    // CONSTRUCTORS
    public Match(String header, String note, List<Team> teams) {
        this.header = header;
        this.note = note;
        this.teams = teams;
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
}
