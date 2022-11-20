package cz.uhk.tj_strat_rk.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    @ManyToMany(mappedBy="teams")
    private List<Match> matches;

    // CONSTRUCTORS
    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
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

    public List<Match> getMatches() {
        return matches;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
