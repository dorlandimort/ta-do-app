package com.dorlandimort.android.todoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorlandimort.android.todoapp.R;
import com.dorlandimort.android.todoapp.models.ToDoItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dorlan on 22/06/2016.
 */
public class ToDoItemsRecyclerAdapter extends FirebaseRecyclerAdapter<ToDoItem, ToDoItemsRecyclerAdapter.ToDoItemsViewHolder> {


    public ToDoItemsRecyclerAdapter(int modelLayout,DatabaseReference ref) {
        super(ToDoItem.class, modelLayout, ToDoItemsViewHolder.class, ref);
    }

    @Override
    public ToDoItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);
        return new ToDoItemsViewHolder(view);
    }

    @Override
    protected void populateViewHolder(ToDoItemsViewHolder viewHolder, ToDoItem model, int position) {
        String itemDescription = model.getItem();
        String username = model.getUsername();
        viewHolder.item.setText(itemDescription);
        viewHolder.username.setText(username);
        if (model.isCompleted())
            viewHolder.imgDone.setVisibility(View.VISIBLE);
        else
            viewHolder.imgDone.setVisibility(View.INVISIBLE);
    }

    class ToDoItemsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtItem)
        TextView item;
        @BindView(R.id.txtUser)
        TextView username;
        @BindView(R.id.imgDone)
        ImageView imgDone;

        public ToDoItemsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
