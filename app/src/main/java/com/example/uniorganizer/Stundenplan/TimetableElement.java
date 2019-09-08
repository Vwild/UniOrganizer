package com.example.uniorganizer.Stundenplan;



public class TimetableElement {


    private int timetableId;
    private String lectureName;
    private String lectureLocation;
    private String timeperiod;
    private String weekDay;
    //zeit als integer um probleme beim anzeigen in der Listview zu vermeiden
    private int beginningHour;
    private int beginningMinute;
    private int endingHour;
    private int endingMinute;

    public TimetableElement(String lectureName, String lectureLocation, String timeperiod){
        this.lectureName = lectureName;
        this.lectureLocation = lectureLocation;
        this.timeperiod = timeperiod;


    }


    public int getTimetableId() {return timetableId;}
    public void setTimetableId( int timetableId){this.timetableId = timetableId;}
    public String getLectureName() {return lectureName;}
    public void setLectureName( String lectureName){ this.lectureName = lectureName;}
    public String getLectureLocation() {return lectureLocation;}
    public void setLectureLocation( String lectureLocation){ this.lectureLocation = lectureLocation;}
    public int getBeginningHour() {return beginningHour;}
    public void setBeginningHour( int beginn){ this.beginningHour = beginn;}
    public int getBeginningMinute() {return beginningMinute;}
    public void setBeginningMinute( int beginning){ this.beginningMinute = beginning;}
    public int getEndingHour() {return endingHour;}
    public void setEndingHour( int ending){ this.endingHour = ending;}
    public int getEndingMinute() {return endingMinute;}
    public void setEndingMinute( int ending){ this.endingMinute = ending;}
    public String getWeekDay() {return weekDay;}
    public void setWeekDay( String weekDay){ this.weekDay = weekDay; }


}

