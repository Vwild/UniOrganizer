package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TimetableElement {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int timetableId;
    @ColumnInfo(name = "lecture_name")
    private String lectureName;
    private String lectureLocation;
    @ColumnInfo(name = "week_day")
    private String weekDay;


    @ColumnInfo(name = "beginning")
    //zeit als integer um probleme beim anzeigen in der Listview zu vermeiden
    private int beginn;
    private int ending;

    public TimetableElement(){


    }

    @NonNull
    public int getTimetableId() {return timetableId;}
    public void setTimetableId(@NonNull int timetableId){this.timetableId = timetableId;}
    public String getLectureName() {return lectureName;}
    public void setLectureName( String lectureName){ this.lectureName = lectureName;}
    public String getLectureLocation() {return lectureLocation;}
    public void setLectureLocation( String lectureLocation){ this.lectureLocation = lectureLocation;}
    public int getBeginn() {return beginn;}
    public void setBeginn( int beginn){ this.beginn = beginn;}
    public int getEnding() {return ending;}
    public void setEnding( int ending){ this.ending = ending;}
    public String getWeekDay() {return weekDay;}
    public void setWeekDay( String weekDay){ this.weekDay = weekDay; }

}

