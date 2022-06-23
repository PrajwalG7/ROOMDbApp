package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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
        showRecords= (Button) findViewById(R.id.showRecords);


        saveToROOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db1").allowMainThreadQueries().build();

                UserDao userDao = db.userDao();
                if(!userID.getText().toString().equals("")) {
                    Boolean check = userDao.is_exists(Integer.parseInt(userID.getText().toString()));
                    if (check == false) {
                        userDao.insertrecord(new User(Integer.parseInt(userID.getText().toString()), firstName.getText().toString(), lastName.getText().toString()));
                        firstName.setText("");
                        lastName.setText("");
                        userID.setText("");
                        Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        firstName.setText("");
                        lastName.setText("");
                        userID.setText("");
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Please enter userId", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //TODO: instead of creating a new database object in MainActivity class and fetch the data to pass it to myadapter class, do pass the data from mainactivity to mainactivtiy2.
        showRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
    }



}

