package com.example.buoi4;

import java.io.Serializable;
import java.util.Date;

public class ToDoItem implements Serializable {
    private String title;
    private String Description;
    private int day;
    private int month;
    private int year;
    private boolean done;
    public ToDoItem(String t, String d, int day, int month, int year, boolean c)
    {
        this.title = t;
        this.Description = d;
        this.day = day;
        this.month = month;
        this.year = year;
        this.done = c;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.Description;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getDone()
    {
        return this.done;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
