package cz.uhk.tj_strat_rk.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "Events")
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String type;
    private String note;
    private Date start;
    private Date end;
    private Timestamp timestamp;
    @ManyToOne
    private Member ministration;

    public Event(String type, String note, Date start, Date end, Member ministration) {
        this.type = type;
        this.note = note;
        this.start = start;
        this.end = end;
        this.ministration = ministration;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Member getMinistration() {
        return ministration;
    }

    public void setMinistration(Member ministration) {
        this.ministration = ministration;
    }
}
