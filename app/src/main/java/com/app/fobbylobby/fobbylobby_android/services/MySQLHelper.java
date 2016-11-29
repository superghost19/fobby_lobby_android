package com.app.fobbylobby.fobbylobby_android.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.fobbylobby.fobbylobby_android.models.Agent;

import java.util.LinkedList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AgentDB";
    // Books table name
    private static final String TABLE_AGENTS = "agents";
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_RATING = "rating";

    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_AGENT_TABLE = "CREATE TABLE agents ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " +
                "address TEXT, " + "latitude DOUBLE, " + "longitude DOUBLE, " + "rating INTEGER )";
        // create books table
        db.execSQL(CREATE_AGENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS agents");
        // create fresh books table
        this.onCreate(db);
    }

    /*CRUD operations (create "add", read "get", update, delete) */
    public void addAgent(Agent agent) {
        Log.d("addAgent", agent.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, agent.getName()); // get name
        values.put(KEY_ADDRESS, agent.getAddress()); // get address
        values.put(KEY_LATITUDE, agent.getLatitute());
        values.put(KEY_LONGITUDE, agent.getLongitute());
        values.put(KEY_RATING, agent.getRating());
        // 3. insert
        db.insert(TABLE_AGENTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values
        // 4. Close dbase
        db.close();
    }

    // Get All Books
    public List<Agent> getAllAgents() {
        List<Agent> agents = new LinkedList<>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_AGENTS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        Agent agent = null;
        if (cursor.moveToFirst()) {
            do {
                agent = new Agent();
                agent.setId(Integer.parseInt(cursor.getString(0)));
                agent.setName(cursor.getString(1));
                agent.setAddress(cursor.getString(2));
                agent.setLatitute(cursor.getDouble(3));
                agent.setLongitute(cursor.getDouble(4));
                agent.setRating(cursor.getInt(5));
                // Add agent to agents
                agents.add(agent);
            } while (cursor.moveToNext());
        }
        Log.d("getAllAgents()", agents.toString());
        return agents; // return agents
    }

    // Updating single book
    public int updateBook(Agent agent, String newName, String newAddress, double newLatitude, double newLongitude,
                          int newRating) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName); // get name
        values.put(KEY_ADDRESS, newAddress); // get address
        values.put(KEY_LATITUDE, newLatitude);
        values.put(KEY_LONGITUDE, newLongitude);
        values.put(KEY_RATING, newRating);
        // 3. updating row
        int i = db.update(TABLE_AGENTS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(agent.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdateBook", agent.toString());
        return i;
    }

    // Deleting single book
    public void deleteAgent(Agent agent) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_AGENTS, KEY_ID + " = ?",
                new String[]{String.valueOf(agent.getId())});
        // 3. close
        db.close();
        Log.d("deleteAgent", agent.toString());
    }

    public int getIds()
    {
        String selectQuery = "SELECT id FROM agents";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        return total;
    }
}
