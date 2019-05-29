package com.example.kushagra.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "CreateTask";
    private TextView mdateTextView;
    private TextView mtimeTextView;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        mdateTextView = (TextView)findViewById(R.id.dateTextView);
        mtimeTextView = (TextView)findViewById(R.id.timeTextView);

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
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mtimeTextView.setText(hourOfDay+":"+minute);
    }

}
