package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.sql.Timestamp;

@Entity(name="trainings")
public class Training {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;

    @JsonView(View.AllTraining.class)
    @NotNull
    private Date start;
    @JsonView(View.AllTraining.class)
    @NotNull
    private Date end;
    @JsonView(View.AllTraining.class)
    @NotNull
    private Timestamp timestamp;

    @JsonView(View.AllTraining.class)
    @NotNull
    private String header;
    @JsonView(View.AllTraining.class)
    private String note;

    @JsonView(View.AllTraining.class)
    @NotNull
    private int track;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "member_id")
    @JsonView(View.AllTraining.class)
    private Member member;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonView(View.AllTraining.class)
    private Team team;


    // CONSTRUCTORS
    public Training(Date start, Date end, String header, String note, int track, Member member) {
        this.start = start;
        this.end = end;
        this.header = header;
        this.note = note;
        this.track = track;
        this.member = member;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public Training(Date start, Date end, String header, String note, int track, Team team) {
        this.start = start;
        this.end = end;
        this.header = header;
        this.note = note;
        this.track = track;
        this.team = team;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public Training() {
        this.timestamp = new Timestamp(new Date().getTime());
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getHeader() {
        return header;
    }

    public String getNote() {
        return note;
    }

    public int getTrack() {
        return track;
    }

    public Member getMember() {
        return member;
    }

    public Team getTeam() {
        return team;
    }

    // SETTERS
    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // UPDATE
    public void update(Training training) {
        this.start = training.getStart();
        this.end = training.getEnd();
        this.header = training.getHeader();
        this.note = training.getNote();
        this.track = training.getTrack();
        this.member = training.getMember();
        this.team = training.getTeam();
    }
}
