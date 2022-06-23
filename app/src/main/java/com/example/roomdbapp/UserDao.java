package com.example.roomdbapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Insert
    void insertrecord(User users);

    @Query("SELECT EXISTS(SELECT * FROM User WHERE uid=:userId)")
    Boolean is_exists(int userId);


    @Delete
    void delete(User user);
}
