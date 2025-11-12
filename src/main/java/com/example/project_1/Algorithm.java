package com.example.project_1;

import com.example.project_1.Classes.Task;
import com.example.project_1.DataStructures.List;
import com.example.project_1.DataStructures.Node;
import com.example.project_1.JavaFX.AlertException;
import com.example.project_1.JavaFX.Main;

//The class that have the main functionality that do most of the work
public class Algorithm {

    public static TotalProductivity[][] Knapsack(int totalHours) {

        Task[] tasks = Main.tasks;

        //The array that will have the optimal solutions
        TotalProductivity[][] dp = (TotalProductivity[][]) new TotalProductivity[tasks.length + 1][totalHours + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = new TotalProductivity();
            }
        }

        //To go through all the activities
        for (int i = 0; i < tasks.length; i++) {
            //To go through all the hours I have in every tasks
            //Note: started it from the max hour to not calculate the same
            //tasks twice since I use tasks already used in the less hours

            for (int j = totalHours; j >= 1; j--) {

                //To check if the hours i have enough to do the tasks
                if (j - tasks[i].getHours() >= 0) {
                    //To give the current hours i have the max value of productivity
                    //for the tasks I reach

                    long newValue = dp[i][j - tasks[i].getHours()].totalProductivity + tasks[i].getProductivity();

                    if (dp[i][j].totalProductivity < newValue) {
                        //The +1 to start from 1 to task.length
                        dp[i + 1][j].totalProductivity = newValue;
                        dp[i + 1][j].lastTask = tasks[i];
                    } else {
                        dp[i + 1][j].totalProductivity = dp[i][j].totalProductivity;
                        dp[i + 1][j].lastTask = dp[i][j].lastTask;
                    }
                } else {
                    dp[i + 1][j].totalProductivity = dp[i][j].totalProductivity;
                    dp[i + 1][j].lastTask = dp[i][j].lastTask;
                }
            }
        }
        return dp;
    }

    //This method is to get the tasks in the optimal solution from the dp array in a certain amount of hours
    public static Task[] getTasks(TotalProductivity[][] array, int totalHours) {

        if (totalHours >= array[0].length)
            throw new AlertException("The amount of hours is bigger than the array size!");

        Task[] tasks = new Task[array.length - 1];

        long totalProductivity = array[array.length - 1][totalHours].totalProductivity;

        int i = array.length - 1;
        int j = totalHours;

        //To stop when the total productivity is 0 which mean I already go through all the tasks in the time provided
        while (totalProductivity > 0) {
            //To remove the value of the task I already reached
            totalProductivity -= array[i][j].lastTask.getProductivity();

            //To add the last task in that hour to the array
            tasks[array.length - 1 - i] = array[i][j].lastTask;

            //To get the new remaining hours, which the current productivity depends on it productivity
            j = j - array[i--][j].lastTask.getHours();
        }

        //To resize for the number of tasks I have
        Task[] finalTasks = new Task[array.length - i - 1];
        for (int k = 0; k < finalTasks.length; k++) {
            finalTasks[k] = tasks[k];
        }

        return finalTasks;
    }

    //To delete a task from the main array
    public static void deleteTask(Task task) {
        if (task == null)
            throw new AlertException("Please enter the task that you want to delete!");

        Task[] tasks = new Task[Main.tasks.length - 1];

        for (int i = 0, j = 0; i <= tasks.length; i++) {
            if (!Main.tasks[i].equals(task))
                tasks[j++] = Main.tasks[i];
        }
        Main.tasks = tasks;
    }

    //To save the total productivity of the tasks in a certain amount of hours with the last task added
    public static class TotalProductivity {

        private long totalProductivity;
        private Task lastTask;

        public long getTotalProductivity() {
            return totalProductivity;
        }

        public Task getLastTask() {
            return lastTask;
        }

    }

}
