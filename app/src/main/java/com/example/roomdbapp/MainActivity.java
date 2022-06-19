package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText firstName,lastName;
    Button saveToROOM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName= (EditText) findViewById(R.id.ed1);
        lastName= (EditText) findViewById(R.id.ed2);
        saveToROOM= (Button) findViewById(R.id.btn1);


        saveToROOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundThread().start();
            }
        });
    }


    class BackgroundThread extends  Thread{
        public void run(){
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "room_db1").build();

            UserDao userDao = db.userDao();
            //TODO Add  @PrimaryKey(autoGenerate = true) to User class uid property
            userDao.insertrecord(new User(0,firstName.getText().toString(),lastName.getText().toString()));




        }
    }
}

