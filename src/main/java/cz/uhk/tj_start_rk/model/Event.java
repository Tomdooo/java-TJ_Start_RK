package cz.uhk.tj_start_rk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.json_view.View;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "events")
public class Event {
    @Id
    @GeneratedValue
    @JsonView(View.Base.class)
    private int id;
    @JsonView(View.AllEvent.class)
    private String type;
    @JsonView(View.AllEvent.class)
    private String note;
    @JsonView(View.AllEvent.class)
    private Date start;
    @JsonView(View.AllEvent.class)
    private Date end;
    @JsonView(View.AllEvent.class)
    private Timestamp timestamp;

    @ManyToOne
//    @JsonBackReference
    @JsonView(View.AllEvent.class)
    private Member ministration;

    // Constructors
    public Event(String type, String note, Date start, Date end, Member ministration) {
        this.type = type;
        this.note = note;
        this.start = start;
        this.end = end;
        this.ministration = ministration;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public Event() {
        this.timestamp = new Timestamp(new Date().getTime());
    }

    //
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
