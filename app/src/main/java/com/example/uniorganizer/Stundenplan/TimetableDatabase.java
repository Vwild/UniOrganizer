package com.example.uniorganizer.Stundenplan;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Code By: Vincent Eichenseher
//Datenbankklasse die von RoomDatabase erbt und eine instanz von DaoAccess implementiert

@Database(entities = {TimetableDataElement.class}, version=2, exportSchema = false)
public abstract class TimetableDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
