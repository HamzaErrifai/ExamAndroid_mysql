package com.myapplicationsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gestion_clients.db";

    private static final String TABLE_CLIENT = "client";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CLIENT + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);

        onCreate(db);
    }

    Client getClient(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(TABLE_CLIENT,
                new String[]{"id", "name", "email", "password"},
                "email=?", new String[]{email},
                null, null, null, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            Client client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return client;
        }
        return new Client("","","");

    }

    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_CLIENT;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(cursor.getInt(0));
                client.setName(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                client.setPassword(cursor.getString(3));
                clientList.add(client);
            } while (cursor.moveToNext());
        }

        return clientList;
    }

    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("password", client.getPassword());

        long x = db.insert(TABLE_CLIENT, null, values);
        db.close();
    }

}
