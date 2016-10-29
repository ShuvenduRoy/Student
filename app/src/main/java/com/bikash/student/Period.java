package com.bikash.student;

/**
 * Created by root on 10/29/16.
 */

public class Period {

    public String period;
    public String subject;

    Period(){
        this.period="";
        this.subject="";
    }

    Period(String p, String s){
        this.period=p;
        this.subject=s;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
