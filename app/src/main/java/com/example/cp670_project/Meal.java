package com.example.cp670_project;
import java.util.Date;
import java.util.UUID;

public class Meal {
    private String id;
    private String ownerId;
    private String name;
    private int caloriesIn;
    private Date date;

    private String createMealId() {
        return UUID.randomUUID().toString();
    }

    public Meal() {
        this.id = this.createMealId();
        this.ownerId = "";
        this.name = "";
        this.caloriesIn = 0;
        this.date = new Date();
    }

    public Meal(String ownerId, String name, int caloriesIn) {
        this.id = this.createMealId();
        this.ownerId = ownerId;
        this.name = name;
        this.caloriesIn = caloriesIn;
        this.date = new Date();
    }

    public Meal(String id, String ownerId, String name, int caloriesIn, Date date) {
        this.id = this.createMealId();
        this.ownerId = ownerId;
        this.name = name;
        this.caloriesIn = caloriesIn;
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

    public int getCaloriesIn() {
        return this.caloriesIn;
    }

    public Date getDate() {
        return this.date;
    }

    public void setId(String id) { this.id = id; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaloriesIn(int caloriesIn) {
        this.caloriesIn = caloriesIn;
    }
}
