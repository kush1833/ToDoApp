package com.example.kushagra.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<TaskItems> mTaskList;

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1;
        public TextView mTextView2;

        public TaskViewHolder(View itemView){
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.taskTextView);
            mTextView2 = itemView.findViewById(R.id.timeTextView);
        }
    }

    public TaskAdapter(ArrayList<TaskItems> taskList){
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup,false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        TaskItems currentItem = mTaskList.get(i);

        taskViewHolder.mTextView1.setText(currentItem.getTaskText());
        taskViewHolder.mTextView2.setText(currentItem.getTimeText());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}
