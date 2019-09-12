package com.example.uniorganizer.Stundenplan;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TimetableDataElement.class}, version=1, exportSchema = false)
public abstract class TimetableDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
