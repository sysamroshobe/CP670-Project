package com.example.cp670_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

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
    public static final String DATABASE_NAME = "myFitnessAppDatabase.db";
    private static final int DATABASE_VERSION = 2;


    // Table/Database creation statement
    private static final String DATABASE_CREATE = "create table "
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

    // super constructor call

    MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        addTable (database) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_EXERCISES);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(MySQLiteOpenHelper.class.getName(),
                "Downgrading database from version " + newVersion + " to "
                        + oldVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OF_EXERCISES);
        onCreate(database);
    }

    public long count(SQLiteDatabase database) {
        long count = 0;
        database.execSQL("select count(*) " + " from " + "  TABLE_Of_My_ITEMS");
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
