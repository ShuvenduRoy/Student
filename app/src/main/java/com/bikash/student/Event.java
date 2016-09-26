package com.bikash.student;

/**
 * Created by bikash on 9/27/16.
 */
public class Event {

    public String date;
    public String time;
    public String event;

    public Event() {

    }

    public  Event(String date, String time, String event){

        this.date = date;
        this.event = event;
        this.time = time;

    }

    public String getTime(){
        return time;
    }
    public String getDate(){
        return date;
    }
    public String getEvent(){
        return event;
    }
}
