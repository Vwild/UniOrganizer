package com.example.uniorganizer.Stundenplan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Code By: Vincent Eichenseher
//Data Access Object zur Communication mit der Datenbank

@Dao
public interface DaoAccess {

    //Query die alle einträge für einen bestimmten Wochentag zurückgibt und nach beginnzeit sortiert
    @Query("SELECT*FROM timetabledataelement WHERE week_day LIKE :day ORDER BY beginning_hour ASC, beginning_minute ASC")
    List<TimetableDataElement> findLecturesByWeekday(String day);

    @Insert
    void insertOnlyOneElement(TimetableDataElement tableDataElement);

    @Insert
    void insertAll(TimetableDataElement...timetableDataElements);

    @Delete
    void deleteOnlyOneElement(TimetableDataElement tableDataElement);

    @Delete
    void deleteAll(TimetableDataElement...timetableDataElements);

    }
