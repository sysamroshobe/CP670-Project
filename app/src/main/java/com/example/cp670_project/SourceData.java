package com.example.cp670_project;

public class SourceData {
    public boolean addDefaultMeals(ExercisesDataSource datasource, Account account) {
        datasource.createMeal("Breakfast", account.getId(), 200);
        datasource.createMeal("Lunch", account.getId(), 400);
        datasource.createMeal("Supper", account.getId(), 600);
        datasource.createMeal("Snack", account.getId(), 100);

        return true;
    }

    public boolean addDefaultExercises(ExercisesDataSource datasource, Account account) {
        datasource.createExercise("Dumbbell Bench Press", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 5, 0);
        datasource.createExercise("Incline Dumbbell Bench Press", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 4, 0);
        datasource.createExercise("Dumbbell Floor Press", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 3, 0);
        datasource.createExercise("Standing Dumbbell Press", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 4, 0);
        datasource.createExercise("Dumbbell Lateral Raise", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 3, 0);
        datasource.createExercise("Dumbbell Tricep Kickback", account.getId(), "Dumbbell",
                50, 0, 0, 0, 8, 3, 0);

        return true;
    }
}
