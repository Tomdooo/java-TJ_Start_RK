package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "members")
public class Member {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;

    @JsonView(View.AllMember.class)
    private String role;
    @JsonView(View.AllMember.class)
    private String firstName;
    @JsonView(View.AllMember.class)
    private String lastName;

    @JsonView(View.AllMember.class)
    private String password;

    @ManyToOne
    @JsonView(View.AllMember.class)
    private Team team;

    @OneToMany(mappedBy = "member")
    @JsonView(View.AllMember.class)
    private List<Training> trainings = new ArrayList<>();

    @OneToMany(mappedBy = "ministration")
    @JsonView(View.AllMember.class)
    private List<Event> events = new ArrayList<>();

    //CONSTRUCTOR
    public Member(String role, String firstName, String lastName, String password) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Member(String role, String firstName, String lastName, String password, Team team) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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
