package com.example.uniorganizer.Stundenplan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoAccess {


    @Query("SELECT * FROM timetabledataelement")
    List<TimetableDataElement> getAll();

    @Query("SELECT * FROM timetabledataelement WHERE timetableId IN(:timetableIds)")
    List<TimetableDataElement>loadALllByIDs(int[] timetableIds);

    @Query("SELECT * FROM timetabledataelement WHERE week_day LIKE :day")
    List<TimetableDataElement> LoadAllLecturesByDay(String day);

    @Query("SELECT * FROM timetabledataelement WHERE lecture_name LIKE :name")
    TimetableDataElement findLectureByName(String name);

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
