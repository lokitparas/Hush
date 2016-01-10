package com.example.lokit.hush;

        import java.util.ArrayList;
        import java.util.UUID;

public class Record {

    // Note: An entity's partition and row key uniquely identify the entity in
    // the table.
    // Entities with the same partition key can be queried faster than those
    // with different partition keys.
    public String event_name;
    public String event_type;
    public String start_time;
    public String end_time;
    public ArrayList<Integer> weekdays = new ArrayList<Integer>(7);
    public Integer mute;
    public Integer vibrate;
    public ArrayList<Double> location = new ArrayList<Double>(3);

    public void setename(String event_name) {
        this.event_name = event_name ;
    }

    public void setetype(String event_type) {
        this.event_type = event_type;
    }
    public String getstime() {
        return this.start_time;
    }

    public String getename() {
        return this.event_name;
    }
    public String getetype() {
        return this.event_type;
    }

    public void setstime(String email) {
        this.start_time = email;
    }

    public String getetime() {
        return this.end_time;
    }

    public void setetime(String email) {
        this.end_time = email;
    }

    public Integer getmute() {
        return this.mute;
    }

    public void setmute(Integer email) {
        this.mute = email;
    }

    public Integer getvibr() {
        return this.vibrate;
    }

    public void setvibr(Integer email) {
        this.vibrate = email;
    }

    public ArrayList<Double> getLocation(){
        return this.location;
    }

    public void setLocation(ArrayList<Double> email){
        this.location = new ArrayList<Double>();
        location.add(email.get(0));
        location.add(email.get(1));
    }

    public ArrayList<Integer> getWeekdays(){
        return this.weekdays;
    }

    public void setWeekdays(ArrayList<Integer> email){
        this.weekdays = new ArrayList<Integer>(7);
        weekdays.set(0, email.get(0));
        weekdays.set(1,email.get(1));
        weekdays.set(2,email.get(2));
        weekdays.set(3,email.get(3));
        weekdays.set(4,email.get(4));
        weekdays.set(5,email.get(5));
        weekdays.set(6,email.get(6));

    }
}

