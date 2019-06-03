package com.example.kushagra.todo;


public class TaskItems {
    private String mtaskText;
    private String mtimeText;

    public TaskItems(String task, String time){
        mtaskText = task;
        mtimeText = time;
    }


    public String getTaskText(){
        return mtaskText;
    }

    public String getTimeText(){
        return mtimeText;
    }
}
