package com.ekspeace.todolist.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {
    private String Message;
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Date;
    private String Title;

    public Todo() {
    }

    public Todo(String message, int id, String date, String title) {
        Message = message;
        Id = id;
        Date = date;
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
