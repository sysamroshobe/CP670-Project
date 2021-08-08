package com.example.cp670_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // Exercises
    public static final String TABLE_OF_EXERCISES = "Exercise";
    public static final String ID = "_id";
    public static final String ITEM_NAME = "name";
    public static final String OWNER_ID = "ownerId";
    public static final String TYPE = "type";
    public static final String WEIGHT = "weight";
    public static final String DISTANCE = "distance";
    public static final String LENGTH_OF_TIME = "lengthOfTime";
    public static final String CALORIES_OUT = "caloriesOut";
    public static final String REPETITIONS = "repetitions";
    public static final String SETS = "sets";
    public static final String IMAGE = "image";
    public static final String DATE_TIME = "date";

    // Meals
    public static final String TABLE_OF_MEALS = "Meal";
    public static final String CALORIES_IN = "caloriesIn";

    // Accounts
    public static final String TABLE_OF_ACCOUNTS = "Account";
    public static final String AGE = "age";
    public static final String HEIGHT = "height";
    public static final String EMAIL = "emailAddress";
    public static final String USERNAME = "username";
    public static final String HASHED_PASSWORD = "hashedSaltedPassword";
    public static final String SALT = "salt";

    // Database
    public static final String DATABASE_NAME = "myFitnessAppDatabase.db";
    private static final int DATABASE_VERSION = 2;

    // Table/Database creation statement
    private static final String EXERCISES_TABLE_CREATE = "create table "
            + TABLE_OF_EXERCISES + "(" + ID
            + " integer primary key autoincrement, "
            + ITEM_NAME + " text not null, "
            + OWNER_ID + " text, "
            + TYPE + " text, "
            + WEIGHT + " REAL, "
            + DISTANCE + " REAL, "
            + LENGTH_OF_TIME + " REAL, "
            + CALORIES_OUT + " INT, "
            + REPETITIONS + " INT, "
            + SETS + " INT, "
            + IMAGE + " INT, "
            + DATE_TIME + " DATETIME);";

    private static final String MEALS_TABLE_CREATE = "create table "
            + TABLE_OF_MEALS + "(" + ID
            + " integer primary key autoincrement, "
            + ITEM_NAME + " text not null, "
            + OWNER_ID + " text, "
            + CALORIES_IN + " text, "
            + DATE_TIME + " DATETIME);";

    private static final String ACCOUNTS_TABLE_CREATE = "create table "
            + TABLE_OF_ACCOUNTS + "(" + ID
            + " integer primary key autoincrement, "
            + ITEM_NAME + " text not null, "
            + AGE + " text, "
            + HEIGHT + " REAL, "                
            + WEIGHT + " REAL, "
            + EMAIL + " text, "
            + USERNAME + " text, "
            + HASHED_PASSWORD + " text, "
            + SALT + " text, "                                
            + DATE_TIME + " DATETIME);";

    // super constructor call

    MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EXERCISES_TABLE_CREATE);
        database.execSQL(MEALS_TABLE_CREATE);
        database.execSQL(ACCOUNTS_TABLE_CREATE);
        addTable (database) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_MEALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_ACCOUNTS);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(MySQLiteOpenHelper.class.getName(),
                "Downgrading database from version " + newVersion + " to "
                        + oldVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_EXERCISES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_MEALS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_ACCOUNTS);

        onCreate(database);
    }

    public long countExercises(SQLiteDatabase database) {
        long count = 0;
        database.execSQL("select count(*) " + " from " + "  TABLE_OF_EXERCISES");
        return count;
    }

    public long countMeals(SQLiteDatabase database) {
        long count = 0;
        database.execSQL("select count(*) " + " from " + "  TABLE_OF_MEALS");
        return count;
    }

    public long countAccounts(SQLiteDatabase database) {
        long count = 0;
        database.execSQL("select count(*) " + " from " + "  TABLE_OF_ACCOUNTS");
        return count;
    }

    public void addTable (SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + "contact");

        // Table/Database creation statement
        final String contact_table = "create table "
                + "contacts" + " ( " +
                "firstName" + " text primary key , " +
                "lastName"  + " text , " +
                "email"    + " text , " +
                " phoneNumber" + " number " +
                ");";

        database.execSQL(contact_table);

        ContentValues values = new ContentValues();
        values.put( "firstName", "Roger");
        values.put( "lastName", "Moore");
        values.put( "email", "rmoore@unis.com");
        values.put("phoneNumber", "1234567890");
        long insertId = database.insert("contacts", null,
                values);
    }
}
