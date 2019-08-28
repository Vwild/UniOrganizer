package com.example.uniorganizer.Stundenplan;

public class TimetableEntry {

    private String title,description,timePeriod,dayname;

    public TimetableEntry (String title,String description,String timePeriod,String dayname){
        this.title = title;
        this.description = description;
        this.timePeriod = timePeriod;
        this.dayname = dayname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }
}
