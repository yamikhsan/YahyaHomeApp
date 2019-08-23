package com.banana.yahya.homestay;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseCenter extends RoomDatabase {

    public abstract UserDao barangDao();

    private static DatabaseCenter INSTANCE;

    public static DatabaseCenter getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseCenter.class, "db_center")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
