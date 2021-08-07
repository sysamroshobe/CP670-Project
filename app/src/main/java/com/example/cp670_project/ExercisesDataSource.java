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
        values.put(MySQLiteOpenHelper.ID, id + "");
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
}
