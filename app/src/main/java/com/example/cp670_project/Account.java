package com.example.cp670_project;

import java.io.Serializable;
import java.util.UUID;

public class Account implements Serializable {
    private String id;
    private String name;
    private int age;
    private double height;
    private double weight;
    private String emailAddress;
    private String username;
    private String hashedSaltedPassword;
    private String salt;

    private Meal[] meals;
    private Exercise[] exercises;
    private CustomExercise[] customExercises;

    public void addMeal(Meal newMeal) {
        int size = this.meals.length;

        Meal[] newMeals = new Meal[size + 1];

        for (int i = 0; i < size; i++) {
            newMeals[i] = this.meals[i];
        }

        newMeals[size] = newMeal;

        this.meals = newMeals;
    }

    public void addExercise(Exercise newExercise) {
        int size = this.exercises.length;

        Exercise[] newExercises = new Exercise[size + 1];

        for (int i = 0; i < size; i++) {
            newExercises[i] = this.exercises[i];
        }

        newExercises[size] = newExercise;

        this.exercises = newExercises;
    }

    public void addCustomExercise(CustomExercise newCustomExercise) {
        int size = this.customExercises.length;

        CustomExercise[] newCustomExercises = new CustomExercise[size + 1];

        for (int i = 0; i < size; i++) {
            newCustomExercises[i] = this.customExercises[i];
        }

        newCustomExercises[size] = newCustomExercise;

        this.customExercises = newCustomExercises;
    }

    private String createAccountId() {
        return UUID.randomUUID().toString();
    }

    // TEMPORARY USE ONLY -- REMOVE ASAP
    public Account(String username, String salt, String hashedSaltedPassword) {
        this.username = username;
        this.salt = salt;
        this.hashedSaltedPassword = hashedSaltedPassword;
    }
    // TEMPORARY USE ONLY -- REMOVE ASAP

    public Account() {
        this.id = this.createAccountId();
        this.name = "";
        this.age = 0;
        this.height = 0.0;
        this.weight = 0.0;
        this.emailAddress = "";
        this.hashedSaltedPassword = "";
        this.salt = "";
        this.meals = new Meal[1];
        this.exercises = new Exercise[1];
        this.customExercises = new CustomExercise[1];
    }

    public Account(String name, int age, double height, double weight, String emailAddress, String hashedSaltedPassword, String salt, Meal[] meals, Exercise[] exercises, CustomExercise[] customExercises) {
        this.id = this.createAccountId();
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.emailAddress = emailAddress;
        this.hashedSaltedPassword = hashedSaltedPassword;
        this.salt = salt;
        this.meals = meals;
        this.exercises = exercises;
        this.customExercises = customExercises;
    }

    public Account(String id, String name, int age, double height, double weight, String emailAddress, String password, Meal[] meals, Exercise[] exercises, CustomExercise[] customExercises) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.emailAddress = emailAddress;
        this.hashedSaltedPassword = password;
        this.meals = meals;
        this.exercises = exercises;
        this.customExercises = customExercises;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getHashedSaltedPassword() {
        return this.hashedSaltedPassword;
    }

    public Meal[] getMeals() {
        return this.meals;
    }

    public Exercise[] getExercises() {
        return this.exercises;
    }

    public CustomExercise[] getCustomExercises() {
        return this.customExercises;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setHashedSaltedPassword(String hashedSaltedPassword) {
        this.hashedSaltedPassword = hashedSaltedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}