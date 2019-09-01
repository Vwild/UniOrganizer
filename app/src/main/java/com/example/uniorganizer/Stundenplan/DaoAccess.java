package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM timetableelement")
    List<TimetableElement>getAll();

    @Query("SELECT * FROM timetableelement WHERE timetableId IN(:timetableIds)")
    List<TimetableElement>loadALllByIDs(int[] timetableIds);


    @Query("SELECT * FROM timetableelement WHERE week_day LIKE :day")
    List<TimetableElement> LoadAllLecturesByDay(String day);


    @Query("SELECT * FROM timetableelement WHERE lecture_name LIKE :name")
    TimetableElement findLectureByName(String name);

    @Query("SELECT*FROM timetableelement WHERE week_day LIKE :day AND beginning LIKE :time")
    TimetableElement findLectureByDateAndTime(String day, int time);


    @Insert
    void insertOnlyOneElement(TimetableElement tableElement);

    @Insert
    void insertAll(TimetableElement...timetableElements);

    @Delete
    void deleteOnlyOneElement(TimetableElement tableElement);

    @Delete
    void deleteAll(TimetableElement...timetableElements);

    }
