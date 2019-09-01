package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {TimetableElement.class }, version = 1, exportSchema = false)

public abstract class TimetableDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

}
