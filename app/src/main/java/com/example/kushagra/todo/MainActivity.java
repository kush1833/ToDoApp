package com.example.kushagra.todo;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button addTaskBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ArrayList<TaskItems> taskList = new ArrayList<>();
        taskList.add(new TaskItems());



    }

    public void toCreateTask(){
        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);
    }
}
