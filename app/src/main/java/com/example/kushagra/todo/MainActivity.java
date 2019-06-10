package com.example.kushagra.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    public static ArrayList<TaskItems> taskList;
    private static RecyclerView mtaskRecyclerView;
    public static  RecyclerView.Adapter mRecyclerViewAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;

    private Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

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
        registerForContextMenu(mtaskRecyclerView);



        displayTasks();

    }

    private void buildRecyclerView() {
        mtaskRecyclerView.setHasFixedSize(true);
        mLayoutManager= new LinearLayoutManager(this);
        mRecyclerViewAdapter = new TaskAdapter(taskList,this);

        mtaskRecyclerView.setLayoutManager(mLayoutManager);
        mtaskRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
    public void toCreateTask(){
        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);

    }

    public void displayTasks(){
        Cursor result = db.getAllData();
        taskList = new ArrayList<>();
        buildRecyclerView();
        if(result.getCount() == 0){
            //no data

        }
        else{
            //display in recycler view

            while(result.moveToNext()){
                String task = result.getString(1);
                String date = result.getString(2);
                String time = result.getString(3);
                TaskItems t = new TaskItems(task, date+" at "+time);
                taskList.add(t);
            }
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }


}
