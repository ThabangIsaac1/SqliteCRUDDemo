package com.sidume.freedmores.sqlitecruddemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "country_currencies";

    // Table columns
    public static final String _ID = "_id";
    public static final String COUNTRY_NAME = "country_name";
    public static final String CONTINENT = "continent";
    public static final String POPULATION = "population";
    public static final String CURRENCY = "currency";

    // Database Name
    static final String DB_NAME = "currencies.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COUNTRY_NAME + " TEXT NOT NULL, " +
            CONTINENT + " TEXT NOT NULL, " +
            POPULATION + " REAL NOT NULL, " +
            CURRENCY + " TEXT);";

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insert(String c_name, String cont_name,double pop, String curr) {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COUNTRY_NAME, c_name);
        contentValue.put(CONTINENT, cont_name);
        contentValue.put(POPULATION, pop);
        contentValue.put(CURRENCY, curr);
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }


    public Cursor fetch() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{ _ID, COUNTRY_NAME,CONTINENT, POPULATION,CURRENCY};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }

    public int update(int _id, String c_name, String cont_name, double pop, String curr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNTRY_NAME, c_name);
        contentValues.put(CONTINENT, cont_name);
        contentValues.put(POPULATION, pop);
        contentValues.put(CURRENCY, curr);
        int i = db.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        db.close();
        return i;

    }

    public void delete(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        db.close();
    }

    public List<Country> getAllCountries() {
        List<Country> countries = new LinkedList<Country>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_NAME;
        // 2. get reference to writable DB
        SQLiteDatabase db= getWritableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        // 3. go over each row, build country and add it to list
        Country country= null;
        if (cursor!= null && cursor.moveToFirst()) {
            do {
                country = new Country();
                country.setId(Integer.parseInt(cursor.getString(0)));
                country.setCountry_name(cursor.getString(1));
                country.setCountry_continent(cursor.getString(2));
                country.setPopulation(Double.valueOf(cursor.getString(3)));
                country.setCurrency(cursor.getString(4));
                // Add country to countries

                countries.add(country);
            } while (cursor.moveToNext());}

        // return books
        cursor.close();
        return countries;
    }

}
