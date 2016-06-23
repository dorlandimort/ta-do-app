package com.dorlandimort.android.todoapp.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dorlandimort.android.todoapp.R;
import com.dorlandimort.android.todoapp.ToDoApp;
import com.dorlandimort.android.todoapp.adapters.ToDoItemsRecyclerAdapter;
import com.dorlandimort.android.todoapp.models.ToDoItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputItem)
    EditText inputItem;
    @BindView(R.id.recyclerViewItems)
    RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter adapter;
    private ChildEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.setupUsername();
        SharedPreferences sp = this.getApplication().getSharedPreferences("ToDoPrefs", 0);
        String username = sp.getString("username", null);
        this.setTitle(username);
        ToDoApp app = (ToDoApp) this.getApplicationContext();
        databaseReference = app.getReference();

        this.adapter = new ToDoItemsRecyclerAdapter(R.layout.recyclerview_item, databaseReference);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.adapter);

        this.listener = databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.foaAdd)
    public void onFloatingActionButtonClick() {
        SharedPreferences sp = this.getSharedPreferences("ToDoPrefs", 0);
        String username = sp.getString("username", null);
        String itemText = this.inputItem.getText().toString().trim();
        if (! itemText.isEmpty()) {
            this.inputItem.setText("");
            InputMethodManager inputMethodManager =
                    (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setItem(itemText);
            toDoItem.setUsername(username);
            this.databaseReference.push().setValue(toDoItem);
        }
    }

    private void setupUsername() {
        SharedPreferences sp = this.getApplication().getSharedPreferences("ToDoPrefs", 0);
        String username = sp.getString("username", null);
        if (username == null) {
            Random r = new Random(100000);
            username = "AndroidUser" + r.nextInt();
            sp.edit().putString("username", username).commit();
        }

    }
}
