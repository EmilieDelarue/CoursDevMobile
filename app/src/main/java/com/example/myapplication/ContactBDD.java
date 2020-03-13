package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "contact.db";

    private static final String TABLE_CONTACTS = "table_contacts";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME= "Name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_FIRSTNAME = "FirstName";
    private static final int NUM_COL_FIRSTNAME = 2;
    private static final String COL_NUMBER = "Number";
    private static final int NUM_COL_NUMBER = 3;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public ContactBDD(Context context){
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertContact(Contact contact){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, contact.getName());
        values.put(COL_FIRSTNAME, contact.getFirstName());
        values.put(COL_NUMBER, contact.getNumber());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CONTACTS, null, values);
    }

    public int updateContact(int id, Contact contact){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, contact.getName());
        values.put(COL_FIRSTNAME, contact.getFirstName());
        values.put(COL_NUMBER, contact.getNumber());
        return bdd.update(TABLE_CONTACTS, values, COL_ID + " = " +id, null);
    }

    public int removeContactWithID(int id){
        return bdd.delete(TABLE_CONTACTS, COL_ID + " = " +id, null);
    }

    public Contact getContactWithName(String name){
        Cursor c = bdd.query(TABLE_CONTACTS, new String[] {COL_ID, COL_NAME, COL_FIRSTNAME, COL_NUMBER}, COL_NAME + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToContact(c);
    }

    private Contact cursorToContact(Cursor c){
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Contact contact = new Contact();
        contact.setId(c.getInt(NUM_COL_ID));
        contact.setName(c.getString(NUM_COL_NAME));
        contact.setFirstName(c.getString(NUM_COL_FIRSTNAME));
        contact.setNumber(c.getString(NUM_COL_NUMBER));

        c.close();

        return contact;
    }
}
