package com.example.cp670_project;
import java.util.Date;

public class CustomExercise extends Exercise {
    private String description;

    public CustomExercise() {
        super();
        this.description = "";
    }

    public CustomExercise(String ownerId, String name, String type, double weight, double distance, double lengthOfTime, int caloriesOut, int repetitions, int sets,
                          int image, String description) {
        super(ownerId, name, type, weight, distance, lengthOfTime, caloriesOut, repetitions, sets, image);
        this.description = description;
    }

    public CustomExercise(String id, String ownerId, String name, String type, double weight, double distance, double lengthOfTime, int caloriesOut, int repetitions, int sets,
                          int image, String description, Date date) {
        super(id, ownerId, name, type, weight, distance, lengthOfTime, caloriesOut, repetitions, sets, image, date);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
