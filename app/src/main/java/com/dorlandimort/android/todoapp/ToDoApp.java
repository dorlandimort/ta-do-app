package com.dorlandimort.android.todoapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Dorlan on 22/06/2016.
 */
public class ToDoApp extends Application {

    private FirebaseDatabase firebaseDatabase;

    public void r() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");
        myRef.child("hco").setValue("oeoe");
    }

}
