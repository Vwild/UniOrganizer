package com.example.uniorganizer.Stundenplan;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TimetableDataElement {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int timetableId;
    @ColumnInfo(name = "lecture_name")
    private String lectureName;
    @ColumnInfo(name = "lecture_location")
    private String lectureLocation;
    @ColumnInfo(name = "week_day")
    private String weekDay;
    //zeit als integer um probleme beim anzeigen in der Listview zu vermeiden
    @ColumnInfo(name = "beginning_hour")
    private int beginningHour;
    @ColumnInfo(name = "beginning_minute")
    private int beginningMinute;
    @ColumnInfo (name = "ending_hour")
    private int endingHour;
    @ColumnInfo (name = "ending_minute")
    private int endingMinute;

    public TimetableDataElement(){

    }


    public int getTimetableId() {return timetableId;}
    public void setTimetableId(@NonNull int timetableId){this.timetableId = timetableId;}
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


