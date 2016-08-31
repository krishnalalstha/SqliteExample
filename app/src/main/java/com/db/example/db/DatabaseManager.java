package com.db.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.db.example.modal.Contact;

/**
 * Created by krishna on 8/31/16.
 */
public class DatabaseManager {
    private static DatabaseManager INSTANCE;
    SQLiteDatabase db;


    public DatabaseManager(Context mContext) {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(mContext);
        this.db = mDatabaseHelper.getWritableDatabase();
    }


    public static DatabaseManager newInstance(Context mContext) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseManager(mContext);
        }

        return INSTANCE;
    }

    public void addContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_ADDRESS, contact.getContactAddress());
        contentValues.put(DatabaseHelper.CONTACT_ID, contact.getContactId());
        contentValues.put(DatabaseHelper.CONTACT_NAME, contact.getContactName());
        contentValues.put(DatabaseHelper.GNEDER, contact.getGender());
        db.insert(DatabaseHelper.TBL_CONTACT, null, contentValues);
    }

    public void deleteContact(String contactId) {
        db.delete(DatabaseHelper.TBL_CONTACT, DatabaseHelper.CONTACT_ID + " = '" + contactId + "'", null);
    }

    public void upateContact(String contactId, String updatedName) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.CONTACT_NAME, updatedName);
        db.update(DatabaseHelper.TBL_CONTACT, cv, DatabaseHelper.CONTACT_ID + " = '" + contactId + "'", null);

    }

    public void fetchContact() {
        String query = "SELECT * FROM " + DatabaseHelper.TBL_CONTACT;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Log.e("Contact Name ", cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME))+"");
            Log.e("Contact ID ", cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_ID))+"");
            Log.e("Contact GENDER ", cursor.getString(cursor.getColumnIndex(DatabaseHelper.GNEDER))+"");
            Log.e("Contact Address ", cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_ADDRESS))+"");
        }
    }

}