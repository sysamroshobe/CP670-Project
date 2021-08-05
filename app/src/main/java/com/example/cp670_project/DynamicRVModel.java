package com.example.cp670_project;

public class DynamicRVModel {

    String name;
    private int image;
    int pos;
    private String details;

    public DynamicRVModel(String name, int image, int pos, int calories, Boolean caloriesInFlag) {
        this.name = name;
        this.image = image;
        this.pos = pos;
        this.details = caloriesInFlag ? "Calories IN: " + calories :  "Calories OUT: " + calories;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getImage(){
        return image;
    }

    public int getPos(){
        return pos;
    }
}
