package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MaBaseSQLite extends SQLiteOpenHelper {
    private static final String TABLE_CONTACTS = "table_contacts";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_FIRSTNAME = "FirstName";
    private static final String COL_NUMBER = "Number";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_CONTACTS + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, "
            + COL_FIRSTNAME + " TEXT NOT NULL, " + COL_NUMBER + " TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CONTACTS + ";");
        onCreate(db);
    }
}
