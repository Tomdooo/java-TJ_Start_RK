package cz.uhk.tj_start_rk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
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

    private int track;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Team team;

    //Todo Timestamp and Header?

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
}
