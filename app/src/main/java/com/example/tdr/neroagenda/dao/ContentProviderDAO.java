package com.example.tdr.neroagenda.dao;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.tdr.neroagenda.contracts.ContactContract.ContactEntry;
import com.example.tdr.neroagenda.models.Contact;

import java.util.ArrayList;

public class ContentProviderDAO {

    private ContentResolver resolver;
    private final String AUTHORITY = "com.example.androidstudio.contactsprovider.providers/",
            contactUri = "content://" + AUTHORITY + "contacts/";

    public ContentProviderDAO(Context context) {
        resolver = context.getContentResolver();
    }

    public long addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_NAME_NAME, contact.getmName());
        values.put(ContactEntry.COLUMN_NAME_NUMBER, contact.getmPhone());
        values.put(ContactEntry.COLUMN_NAME_EMAIL, contact.getmEmail());
        Uri uri = resolver.insert(Uri.parse("content://com.example.androidstudio.contactsprovider.providers/contacts"),
                values);
        return ContentUris.parseId(uri);
    }

    public int deleteContact(Contact contact) {
        return resolver.delete(Uri.parse(contactUri + contact.getId()), null, null);
    }

    public int updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_NAME_NAME, contact.getmName());
        values.put(ContactEntry.COLUMN_NAME_NUMBER, contact.getmPhone());
        values.put(ContactEntry.COLUMN_NAME_EMAIL, contact.getmEmail());
        return resolver.update(Uri.parse(contactUri + contact.getId()), values, null, null);
    }

    public ArrayList<Contact> retrieveContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        String[] projection = {
                ContactEntry.COLUMN_NAME_NAME,
                ContactEntry.COLUMN_NAME_NUMBER,
                ContactEntry.COLUMN_NAME_EMAIL,
                ContactEntry._ID
        };
        Cursor cursor = resolver.query(Uri.parse("content://com.example.androidstudio.contactsprovider.providers/contacts"), projection, null, null, null);
        assert cursor != null;
        if (cursor.getCount() != 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Contact contact = new Contact(cursor.getString(cursor.getColumnIndex(ContactEntry.COLUMN_NAME_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactEntry.COLUMN_NAME_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(ContactEntry.COLUMN_NAME_EMAIL)),
                        cursor.getLong(cursor.getColumnIndex(ContactEntry._ID)));
                contacts.add(contact);
            }
        }
        cursor.close();
        return contacts;
    }


}
