package com.dorlandimort.android.todoapp.models;

/**
 * Created by Dorlan on 22/06/2016.
 */
public class ToDoItem {

    private String item;
    private String username;
    private boolean completed;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
