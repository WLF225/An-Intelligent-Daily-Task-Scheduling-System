package com.example.project_1.Algorithms;

import com.example.project_1.Classes.Task;
import com.example.project_1.JavaFX.Main;

//The class that have the greedy algorithm for the knapsack problem
public class Greedy {

    public static Task[] greedySolution(int hours){
        //Making new copy from the main array to keep it unchanged
        Task[] tasks = new Task[Main.tasks.length];
        for (int i = 0; i < Main.tasks.length; i++) {
            tasks[i] = Main.tasks[i];
        }

        //Sort the new array
        Sort.heapSort(tasks);

        Task[] temp = new Task[Main.tasks.length];

        //To add the tasks based on the largest ratio first untile have no time
        int index = 0;
        for (int i = tasks.length-1; i >= 0; i--) {
            if (tasks[i].getHours() <= hours) {
                System.out.println(tasks[i].getName());
                hours -= tasks[i].getHours();
                temp[index++] = tasks[i];
            }
            if (hours == 0)
                break;
        }
        //To make it smaller to the needed size
        Task[] result = new Task[index];
        for (int i = 0; i < index; i++) {
            if (temp[i] == null)
                break;
            result[i] = temp[i];
        }
        return result;
    }
}
