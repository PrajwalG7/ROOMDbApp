package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText userID,lastName,firstName;
    Button saveToROOM,showRecords, deleteRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID= findViewById(R.id.userid);
        firstName= findViewById(R.id.ed1);
        lastName= findViewById(R.id.ed2);
        saveToROOM= findViewById(R.id.btn1);
        showRecords= findViewById(R.id.showRecords);
        deleteRecord= findViewById(R.id.btnDelete);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db1").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        final Boolean[] check = new Boolean[1];

        saveToROOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userID.getText().toString().equals("")) {
                     check[0] = userDao.is_exists(Integer.parseInt(userID.getText().toString()));
                    if (check[0] == false) {
                        userDao.insertrecord(new User(Integer.parseInt(userID.getText().toString()), firstName.getText().toString(), lastName.getText().toString()));
                        resetFields();
                        Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                       resetFields();
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


        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userID.getText().toString().equals("")) {
                    check[0] = userDao.is_exists(Integer.parseInt(userID.getText().toString()));
                    if (check[0] == true) {
                        userDao.delete(new User(Integer.parseInt(userID.getText().toString()), firstName.getText().toString(), lastName.getText().toString()));
                        resetFields();
                        Toast.makeText(MainActivity.this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                       resetFields();
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void resetFields(){
        firstName.setText("");
        lastName.setText("");
        userID.setText("");
    }


}

