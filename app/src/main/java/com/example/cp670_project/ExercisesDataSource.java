package com.example.cp670_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExercisesDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteOpenHelper dbOpenHelper;
    private String[] columns = { MySQLiteOpenHelper.ID,
            MySQLiteOpenHelper.ITEM_NAME };

    private static final String TAG = "myExerciseDB";

    // call to database constructor
    ExercisesDataSource(Context context) {
        dbOpenHelper = new MySQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbOpenHelper.getWritableDatabase();
        Log.d(TAG, "open() called");
        //database = dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        dbOpenHelper.close();
    }

    public Exercise createExercise(String item, String ownerId, String type, double weight,
                                   double distance, double lengthOfTime, int caloriesOut,
                                   int repetitions, int sets, int image) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.OWNER_ID, ownerId);
        values.put(MySQLiteOpenHelper.TYPE, type);
        values.put(MySQLiteOpenHelper.WEIGHT, weight);
        values.put(MySQLiteOpenHelper.DISTANCE, distance);
        values.put(MySQLiteOpenHelper.LENGTH_OF_TIME, lengthOfTime);
        values.put(MySQLiteOpenHelper.CALORIES_OUT, caloriesOut);
        values.put(MySQLiteOpenHelper.REPETITIONS, repetitions);
        values.put(MySQLiteOpenHelper.SETS, sets);
        values.put(MySQLiteOpenHelper.IMAGE, image);
        long insertId = database.insert(MySQLiteOpenHelper.TABLE_OF_EXERCISES, null,
                values);

        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_EXERCISES,
                columns, MySQLiteOpenHelper.ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);

        // Log the item stored
        Log.d(TAG, "item = " + cursorToExercise(cursor).toString()
                + " insert ID = " + insertId);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise exercise) {
        String id = exercise.getId();
        Log.d(TAG, "delete item = " + id);
        System.out.println("Exercise deleted with id: " + id);

        database.delete(MySQLiteOpenHelper.TABLE_OF_EXERCISES, MySQLiteOpenHelper.ID
                + " = " + id, null);

    }

    public void deleteAllExercises() {
        System.out.println("Exercise deleted all");
        Log.d(TAG, "delete all = ");

        database.delete(MySQLiteOpenHelper.TABLE_OF_EXERCISES, null, null);
    }

    public List<Exercise> getAllExercises() {
        List <Exercise> exercises = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_EXERCISES,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            Log.d(TAG, "get item = " + cursorToExercise(cursor).toString());
            exercises.add(exercise);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return exercises;
    }

    public long count() {
        long count = DatabaseUtils.queryNumEntries(database, MySQLiteOpenHelper.TABLE_OF_EXERCISES);
        return count;
    }

    public Exercise updateExercise(String id, String item, String ownerId, String type, double weight,
                                   double distance, double lengthOfTime, int caloriesOut,
                                   int repetitions, int sets, int image) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.OWNER_ID, ownerId);
        values.put(MySQLiteOpenHelper.TYPE, type);
        values.put(MySQLiteOpenHelper.WEIGHT, weight);
        values.put(MySQLiteOpenHelper.DISTANCE, distance);
        values.put(MySQLiteOpenHelper.LENGTH_OF_TIME, lengthOfTime);
        values.put(MySQLiteOpenHelper.CALORIES_OUT, caloriesOut);
        values.put(MySQLiteOpenHelper.REPETITIONS, repetitions);
        values.put(MySQLiteOpenHelper.SETS, sets);
        values.put(MySQLiteOpenHelper.IMAGE, image);

        // Set datetime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        values.put(MySQLiteOpenHelper.DATE_TIME, currentDateandTime);

        // Update database
        database.update(MySQLiteOpenHelper.TABLE_OF_EXERCISES, values, MySQLiteOpenHelper.ID
                + " = " + id, null);

        // Update object
        Exercise exercise = new Exercise();
        exercise.setExercise(item);
        exercise.setId(id);
        exercise.setOwner(ownerId);
        exercise.setType(type);
        exercise.setWeight(weight);
        exercise.setDistance(distance);
        exercise.setLengthOfTime(lengthOfTime);
        exercise.setCaloriesOut(caloriesOut);
        exercise.setRepetitions(repetitions);
        exercise.setSets(sets);
        exercise.setImage(image);
        exercise.setDate(new Date());

        return exercise;
    }

    private Exercise cursorToExercise(Cursor cursor) {
        Exercise exercise = new Exercise();
        exercise.setId(cursor.getString(0));
        exercise.setExercise(cursor.getString(1));
        return exercise;
    }

    // Meals
    public Meal createMeal(String item, String ownerId, int caloriesIn) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.OWNER_ID, ownerId);
        values.put(MySQLiteOpenHelper.CALORIES_IN, caloriesIn);
        values.put(MySQLiteOpenHelper.DATE_TIME, String.valueOf(new Date()));

        long insertId = database.insert(MySQLiteOpenHelper.TABLE_OF_MEALS, null,
                values);

        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_MEALS,
                columns, MySQLiteOpenHelper.ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Meal newMeal = cursorToMeal(cursor);

        // Log the item stored
        Log.d(TAG, "item = " + cursorToMeal(cursor).toString()
                + " insert ID = " + insertId);
        cursor.close();
        return newMeal;
    }

    public void deleteMeal(Meal meal) {
        String id = meal.getId();
        Log.d(TAG, "delete item = " + id);
        System.out.println("Meal deleted with id: " + id);

        database.delete(MySQLiteOpenHelper.TABLE_OF_MEALS, MySQLiteOpenHelper.ID
                + " = " + id, null);

    }

    public void deleteAllMeals() {
        System.out.println("Meal deleted all");
        Log.d(TAG, "delete all = ");

        database.delete(MySQLiteOpenHelper.TABLE_OF_MEALS, null, null);
    }

    public List<Meal> getAllMeals() {
        List <Meal> meals = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_MEALS,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Meal meal = cursorToMeal(cursor);
            Log.d(TAG, "get item = " + cursorToMeal(cursor).toString());
            meals.add(meal);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return meals;
    }

    public long countMeals() {
        long count = DatabaseUtils.queryNumEntries(database, MySQLiteOpenHelper.TABLE_OF_MEALS);
        return count;
    }

    public Meal updateMeal(String id, String item, String ownerId, int caloriesIn) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.OWNER_ID, ownerId);
        values.put(MySQLiteOpenHelper.CALORIES_IN, caloriesIn);
        values.put(MySQLiteOpenHelper.DATE_TIME, String.valueOf(new Date()));

        // Set datetime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        values.put(MySQLiteOpenHelper.DATE_TIME, currentDateandTime);

        // Update database
        database.update(MySQLiteOpenHelper.TABLE_OF_MEALS, values, MySQLiteOpenHelper.ID
                + " = " + id, null);

        // Update object
        Meal meal = new Meal();
        meal.setCaloriesIn(caloriesIn);
        meal.setId(id);
        meal.setCaloriesIn(caloriesIn);
        meal.setDate(new Date());

        return meal;
    }

    private Meal cursorToMeal(Cursor cursor) {
        Meal meal = new Meal();
        meal.setId(cursor.getString(0));
        meal.setName(cursor.getString(1));
        return meal;
    }

    // Accounts
    public Account createAccount(String item, int age, double height, double weight,
                                 String email, String username, String hashedPassword,
                                 String salt) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.AGE, age);
        values.put(MySQLiteOpenHelper.HEIGHT, height);
        values.put(MySQLiteOpenHelper.WEIGHT, weight);
        values.put(MySQLiteOpenHelper.EMAIL, email);
        values.put(MySQLiteOpenHelper.USERNAME, username);
        values.put(MySQLiteOpenHelper.HASHED_PASSWORD, hashedPassword);
        values.put(MySQLiteOpenHelper.SALT, salt);

        long insertId = database.insert(MySQLiteOpenHelper.TABLE_OF_ACCOUNTS, null,
                values);

        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_ACCOUNTS,
                columns, MySQLiteOpenHelper.ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Account newAccount = cursorToAccount(cursor);

        // Log the item stored
        Log.d(TAG, "item = " + cursorToAccount(cursor).toString()
                + " insert ID = " + insertId);
        cursor.close();

        return newAccount;
    }

    public void deleteAccount(Account meal) {
        String id = meal.getId();
        Log.d(TAG, "delete item = " + id);
        System.out.println("Account deleted with id: " + id);

        database.delete(MySQLiteOpenHelper.TABLE_OF_MEALS, MySQLiteOpenHelper.ID
                + " = " + id, null);

    }

    public void deleteAllAccounts() {
        System.out.println("Account deleted all");
        Log.d(TAG, "delete all = ");

        database.delete(MySQLiteOpenHelper.TABLE_OF_MEALS, null, null);
    }

    public List<Account> getAllAccounts() {
        List <Account> meals = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_OF_MEALS,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account meal = cursorToAccount(cursor);
            Log.d(TAG, "get item = " + cursorToAccount(cursor).toString());
            meals.add(meal);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return meals;
    }

    public long countAccounts() {
        long count = DatabaseUtils.queryNumEntries(database, MySQLiteOpenHelper.TABLE_OF_ACCOUNTS);
        return count;
    }

    public Account updateAccount(String id, String item, int age, double height, double weight,
                                 String email, String username, String hashedPassword,
                                 String salt) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.ITEM_NAME, item);
        values.put(MySQLiteOpenHelper.AGE, age);
        values.put(MySQLiteOpenHelper.HEIGHT, height);
        values.put(MySQLiteOpenHelper.WEIGHT, weight);
        values.put(MySQLiteOpenHelper.EMAIL, email);
        values.put(MySQLiteOpenHelper.USERNAME, username);
        values.put(MySQLiteOpenHelper.HASHED_PASSWORD, hashedPassword);
        values.put(MySQLiteOpenHelper.SALT, salt);

        // Update database
        database.update(MySQLiteOpenHelper.TABLE_OF_ACCOUNTS, values, MySQLiteOpenHelper.ID
                + " = " + id, null);

        // Update object
        Account account = new Account();
        account.setName(item);
        account.setAge(age);
        account.setHeight(height);
        account.setWeight(weight);
        account.setEmailAddress(email);
        account.setUsername(username);
        account.setHashedSaltedPassword(hashedPassword);
        account.setSalt(salt);

        return account;
    }

    private Account cursorToAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getString(0));
        account.setName(cursor.getString(1));
        return account;
    }
}
