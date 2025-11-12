package com.example.project_1;

import com.example.project_1.Classes.Task;

public class TestClass {

    public static void main(String[] args) {

        Task[] tasks = {new Task("Study algo",  3, 9),
                new Task("Watch Lecture",2, 6),
                new Task("Do Homework",4, 12),new Task("Watch Lecture",2, 5),
                new Task("Read Book",1, 3)};

        Algorithm.TotalProductivity[][] tot = Algorithm.Knapsack(24);

        for (Algorithm.TotalProductivity[] row : tot) {
            for (Algorithm.TotalProductivity column : row) {
                System.out.print(column.getTotalProductivity() + " " + column.getLastTask()+" ");
            }
            System.out.println();
        }

        Task[] finalTasks = Algorithm.getTasks(tot, 8);

        for (Task task : finalTasks) {
            System.out.println(task);
        }


    }
}
