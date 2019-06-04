package com.example.kushagra.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    //public static final String EXTRA_LIST = "com.example.kushagra.todo.EXTRA_LIST";

    private static final String TAG = "CreateTask";

    DatabaseHelper db;
    private TextView mdateTextView;
    private TextView mtimeTextView;
    private TextInputLayout mtaskTextInputLayout;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private Button maddBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        db = new DatabaseHelper(this);

        mdateTextView = (TextView)findViewById(R.id.dateTextView);
        mtimeTextView = (TextView)findViewById(R.id.timeTextView);
        mtaskTextInputLayout = (TextInputLayout)findViewById(R.id.taskTextInputLayout);
        maddBtn = (Button)findViewById(R.id.addBtn);



        maddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });

        mdateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateTask.this,mOnDateSetListener,year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mtimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });



        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSet: date: "+dayOfMonth+"/"+month+"/"+year);
                String date = dayOfMonth+"/"+month+"/"+year;
                mdateTextView.setText(date);
            }
        };

    }

    public void backToMainActivity(){
        String date = mdateTextView.getText().toString();
        String time = mtimeTextView.getText().toString();
        String task = mtaskTextInputLayout.getEditText().getText().toString().trim();
        boolean isInserted = db.insertData(task,date,time);


        if(isInserted == true) {
            Toast.makeText(CreateTask.this,"Task Added",Toast.LENGTH_LONG).show();
            mdateTextView.setText("");
            mtimeTextView.setText("");
            mtaskTextInputLayout.getEditText().setText("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(CreateTask.this,"Error while adding Task",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mtimeTextView.setText(hourOfDay+":"+minute);
    }


}
