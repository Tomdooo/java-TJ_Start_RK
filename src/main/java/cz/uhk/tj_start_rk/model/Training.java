package cz.uhk.tj_start_rk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name="trainings")
public class Training {
    @Id
    @GeneratedValue
    private int id;

    private Date start;
    private Date end;
    private Timestamp timestamp;

    private String header;
    private String note;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Team team;

    // CONSTRUCTORS
    public Training(Date start, Date end, String header, String note, Member member) {
        this.start = start;
        this.end = end;
        this.header = header;
        this.note = note;
        this.member = member;
    }

    public Training(Date start, Date end, String header, String note, Team team) {
        this.start = start;
        this.end = end;
        this.header = header;
        this.note = note;
        this.team = team;
    }

    public Training() {

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

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
