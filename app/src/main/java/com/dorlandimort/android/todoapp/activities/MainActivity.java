package com.dorlandimort.android.todoapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.dorlandimort.android.todoapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputItem)
    EditText inputItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnHola)
    public void onClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("items").setValue(inputItem.getText().toString());
    }

    private void setupUsername() {
        SharedPreferences sp = this.getApplication().getSharedPreferences("ToDoprefs", 0);
        String username = sp.getString("username", null);
        if (username == null) {
            Random r = new Random(100000);
            username = "AndroidUser" + r.nextInt();
            sp.edit().putString("username", username).commit();
        }

    }
}
