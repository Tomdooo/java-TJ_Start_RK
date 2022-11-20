package cz.uhk.tj_start_rk.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "members")
public class Member {
    @Id
    @GeneratedValue
    private int id;

    private String role;
    private String firstName;
    private String lastName;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Training> trainings = new ArrayList<>();

    @OneToMany(mappedBy = "ministration")
    private List<Event> events = new ArrayList<>();

    //CONSTRUCTOR
    public Member(String role, String firstName, String lastName) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Member(String role, String firstName, String lastName, Team team) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public Member() {
    }

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // METHODS
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", team=" + team +
                ", trainings=" + trainings +
                ", events=" + events +
                '}';
    }
}
