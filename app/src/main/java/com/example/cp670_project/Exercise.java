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
    private int repetitions;
    private int sets;
    private int image;
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
        this.repetitions = 0;
        this.sets = 0;
        this.image = 0;
        this.date = new Date();
    }

    public Exercise(String ownerId, String name, String type, double weight, double distance,
                    double lengthOfTime, int caloriesOut, int repetitions, int sets, int image) {
        this.id = this.createExerciseId();
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.distance = distance;
        this.lengthOfTime = lengthOfTime;
        this.caloriesOut = caloriesOut;
        this.repetitions = repetitions;
        this.sets = sets;
        this.image = image;
        this.date = new Date();
    }

    public Exercise(String id, String ownerId, String name, String type, double weight, double distance,
                    double lengthOfTime, int caloriesOut, int repetitions, int sets,
                    int image, Date date) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.distance = distance;
        this.lengthOfTime = lengthOfTime;
        this.caloriesOut = caloriesOut;
        this.repetitions = repetitions;
        this.sets = sets;
        this.image = image;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public String getExercise() {
        return name;
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

    public int getRepetitions() {
        return this.repetitions;
    }

    public int getSets() {
        return this.sets;
    }       

    public Date getDate() {
        return this.date;
    }

    public void setId(String id) { this.id = id; }

    public void setExercise(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String ownerId) {
        this.ownerId = ownerId;
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

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString () {
        return name + "  " + id;
    }
}
