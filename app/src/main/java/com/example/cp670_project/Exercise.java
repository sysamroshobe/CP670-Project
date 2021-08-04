package com.example.cp670_project;
import java.util.Date;
import java.util.UUID;

public class Exercise {
    private String id;
    private String ownerId;
    private String name;
    private String type;
    private double weight;
    private double distance;
    private double lengthOfTime;
    private int caloriesOut;
    private Date date;

    private String createExerciseId() {
        return UUID.randomUUID().toString();
    }

    public Exercise() {
        this.id = this.createExerciseId();
        this.ownerId = "";
        this.name = "";
        this.type = "";
        this.weight = 0.0;
        this.distance = 0.0;
        this.lengthOfTime = 0.0;
        this.caloriesOut = 0;
        this.date = new Date();
    }

    public Exercise(String ownerId, String name, String type, double weight, double distance, double lengthOfTime, int caloriesOut) {
        this.id = this.createExerciseId();
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.distance = distance;
        this.lengthOfTime = lengthOfTime;
        this.caloriesOut = caloriesOut;
        this.date = new Date();
    }

    public Exercise(String id, String ownerId, String name, String type, double weight, double distance, double lengthOfTime, int caloriesOut, Date date) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.distance = distance;
        this.lengthOfTime = lengthOfTime;
        this.caloriesOut = caloriesOut;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getLengthOfTime() {
        return this.lengthOfTime;
    }

    public int getCaloriesOut() {
        return this.caloriesOut;
    }

    public Date getDate() {
        return this.date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setLengthOfTime(double lengthOfTime) {
        this.lengthOfTime = lengthOfTime;
    }

    public void setCaloriesOut(int caloriesOut) {
        this.caloriesOut = caloriesOut;
    }
}
