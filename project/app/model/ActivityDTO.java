package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Boss on 12/06/2016.
 */
public class ActivityDTO {

    @JsonDeserialize
    protected String id;
    protected String subject; //Assunto
    protected String type; // ActivityTypes
    protected boolean done; // 0 not done, 1 done
    //protected LocalDate marked_as_done_time;
    //protected int org_id;
    //protected int person_id;
    protected String deal_id;
    //protected LocalDate due_date;
    //protected LocalTime due_time;
    //protected LocalTime duration;
    //protected String note;
    //protected int user_id;


    public ActivityDTO() {
    }

    public String validate() {
        if (subject == null) {
            return "Subject is required";
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public boolean isDone() {
        return done;
    }

}
