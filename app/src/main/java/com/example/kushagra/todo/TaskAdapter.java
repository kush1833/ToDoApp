package com.example.kushagra.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<TaskItems> mTaskList;
    private Context mContext;

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mOptionTextView;
        public CardView mCardView;

        public TaskViewHolder(View itemView){
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.taskTextView);
            mTextView2 = itemView.findViewById(R.id.timeTextView);
            mCardView = itemView.findViewById(R.id.itemCardView);
            mOptionTextView = itemView.findViewById(R.id.optionTextView);

        }
    }

    public TaskAdapter(ArrayList<TaskItems> taskList,Context mContext){
        mTaskList = taskList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup,false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder taskViewHolder, final int i) {
        final TaskItems currentItem = mTaskList.get(i);

        taskViewHolder.mTextView1.setText(currentItem.getTaskText());
        taskViewHolder.mTextView2.setText(currentItem.getTimeText());
        taskViewHolder.mOptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display option menu
                PopupMenu popupMenu = new PopupMenu(mContext,taskViewHolder.mOptionTextView);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_delete:
                                Toast.makeText(mContext,"Task Deleted Successfully",Toast.LENGTH_SHORT).show();
                                DatabaseHelper db = new DatabaseHelper(mContext);

                                db.deleteData(currentItem.getTaskText(),currentItem.getTimeText());
                                mTaskList.remove(i);
                                notifyDataSetChanged();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }


}
