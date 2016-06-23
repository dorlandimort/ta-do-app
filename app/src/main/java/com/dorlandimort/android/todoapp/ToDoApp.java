package com.dorlandimort.android.todoapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Dorlan on 22/06/2016.
 */
public class ToDoApp extends Application {

    private String FIREBASE_CHILD = "items";
    private DatabaseReference reference;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.reference = database.getReference(FIREBASE_CHILD);
    }

    public DatabaseReference getReference() {
        return this.reference;
    }

}
