package com.banana.yahya.homestay;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User u);

    @Query("SELECT * FROM User ORDER BY id ASC")
    LiveData<List<User>> readAll();

}
