package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;


@Database(entities = {TimetableElement.class }, version = 1, exportSchema = false)

public abstract class TimetableDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //
        }
    };

}
