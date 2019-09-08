package com.example.uniorganizer.Stundenplan;

public class TimetableEntryItem {
    private String title;
    private String timeperiod;
    private String description;

    public TimetableEntryItem(String title, String timeperiod, String description){
        this.title = title;
        this.timeperiod = timeperiod;
        this.description = description;
    }
    public String getTitle(){return title;}
    public String getTimeperiod(){return timeperiod;}
    public String getDescription(){return description;}
}
