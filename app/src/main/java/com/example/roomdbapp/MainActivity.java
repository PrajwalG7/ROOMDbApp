package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText userID,lastName,firstName;
    TextView labelMsg;
    Button saveToROOM,showRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID= (EditText) findViewById(R.id.userid);
        firstName= (EditText) findViewById(R.id.ed1);
        lastName= (EditText) findViewById(R.id.ed2);
        saveToROOM= (Button) findViewById(R.id.btn1);
        labelMsg=(TextView) findViewById(R.id.labelMsg);
        showRecords= (Button) findViewById(R.id.showRecords);


        saveToROOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db1").allowMainThreadQueries().build();

                UserDao userDao = db.userDao();
                Boolean check=userDao.is_exists(Integer.parseInt(userID.getText().toString()));
                if(check==false){
                    userDao.insertrecord(new User(Integer.parseInt(userID.getText().toString()), firstName.getText().toString(), lastName.getText().toString()));
               firstName.setText("");
               lastName.setText("");
               userID.setText("");
               labelMsg.setText("Inserted Successfully");
                }else {
                    firstName.setText("");
                    lastName.setText("");
                    userID.setText("");
                    labelMsg.setText("Something went wrong");
                }

            }
        });
    }



}

