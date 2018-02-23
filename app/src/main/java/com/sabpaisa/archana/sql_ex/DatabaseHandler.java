package com.sabpaisa.archana.sql_ex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archana on 20/11/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="contacts.db";
    private static final String TABLE_NAME="ContactsTable";
    private static final String Col_1="id";
    private static final String Col_2="name";
    private static final String col_3="number";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME +"(" + Col_1 + " INTEGER PRIMARY KEY," + Col_2 + " TEXT,"+ col_3 + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void addcontact(Contacts contacts)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(col_3,contacts.getNumber());
        contentValues.put(Col_2,contacts.getName());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    // Getting single contact
    Contacts getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { Col_1,
                        Col_2, col_3 },Col_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts contact = new Contacts(cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }
    // Getting All Contacts
    public List<Contacts> getAllContacts() {
        List<Contacts> contactList = new ArrayList<Contacts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    // Updating single contact
    public int updateContact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Col_2, contact.getName());
        values.put(col_3, contact.getNumber());

        // updating row
        return db.update(TABLE_NAME, values, Col_1 + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Col_1 + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}

