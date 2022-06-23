package com.example.roomdbapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    List<User> users;

    public myadapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("names",users.get(position).getLastName());
        holder.userId.setText(String.valueOf(users.get(position).getUid()));
        holder.firstName.setText(users.get(position).getFirstName());
        holder.lastName.setText(users.get(position).getLastName());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userId,firstName,lastName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userId=itemView.findViewById(R.id.userId);
            firstName=itemView.findViewById(R.id.firstName);
            lastName=itemView.findViewById(R.id.lastName);


        }
    }
}
