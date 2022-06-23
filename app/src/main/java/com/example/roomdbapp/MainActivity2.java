package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getRoomData();

    }

    public void getRoomData(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db1").allowMainThreadQueries().build();

        recyclerView=findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserDao userDao = db.userDao();
        List<User> users=userDao.getAllUsers();
        myadapter myadapter= new myadapter(users);
        recyclerView.setAdapter(myadapter);
    }
}