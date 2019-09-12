package com.example.uniorganizer.Stundenplan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DaoAccess {

/*
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
        */
    }
