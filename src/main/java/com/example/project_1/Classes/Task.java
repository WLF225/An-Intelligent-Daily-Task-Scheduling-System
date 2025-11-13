package com.example.project_1.Classes;

import com.example.project_1.JavaFX.AlertException;

//The Task class that have the task name, hours and productivity
public class Task implements Comparable<Task> {

    private String name;
    private int hours, productivity;
    //The ratio of productivity to hours used in the greedy algorithm
    private double ratio;

    public Task(String name, int hours, int productivity) {
        setName(name);
        setHours(hours);
        setProductivity(productivity);
        ratio = (double) productivity / hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        //To make sure the number of hours is positive
        if (hours <= 0)
            throw new AlertException("The number of hours must be positive!");
        this.hours = hours;
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        //To make sure the amount of productivity is positive
        if (productivity <= 0)
            throw new AlertException("The amount of productivity must be positive!");
        this.productivity = productivity;
    }

    public double getRatio() {
        return ratio;
    }

    public String toString() {
        return "Task: " + name + ", hours: " + hours + ", productivity: " + productivity;
    }

    public boolean equals(Task task) {
        return task.getName().equalsIgnoreCase(this.name)
                && task.getHours() == this.hours
                && task.getProductivity() == this.productivity;
    }

    //To sort the tasks by ratio
    public int compareTo(Task task) {
        if (this.ratio > task.getRatio())
            return 1;
        if (this.ratio < task.getRatio())
            return -1;

        return 0;
    }
}
