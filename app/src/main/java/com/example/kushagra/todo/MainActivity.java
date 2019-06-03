package com.example.kushagra.todo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static ArrayList<TaskItems> taskList;
    public static ArrayList<String> taskArrayList;
    private static RecyclerView mtaskRecyclerView;
    public static  RecyclerView.Adapter mRecyclerViewAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;

    private Button addTaskBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtaskRecyclerView = (RecyclerView)findViewById(R.id.taskRecyclerView);
        addTaskBtn = (Button)findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCreateTask();
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("ToDo");
        setSupportActionBar(toolbar);

        taskList = new ArrayList<>();

        buildRecyclerView();

        if(taskArrayList != null) {
            for (String s : taskArrayList) {
                int i = s.indexOf(',');
                String newTask = s.substring(0, i);
                String newTime = s.substring(i + 1);
                TaskItems obj = new TaskItems(newTask, newTime);
                taskList.add(obj);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        }


    }

    private void buildRecyclerView() {
        mtaskRecyclerView.setHasFixedSize(true);
        mLayoutManager= new LinearLayoutManager(this);
        mRecyclerViewAdapter = new TaskAdapter(taskList);

        mtaskRecyclerView.setLayoutManager(mLayoutManager);
        mtaskRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
    public void toCreateTask(){
        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);
        this.recreate();
    }


}
