package com.example.user.app13_consumoserviciosw02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/2/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GestorContactos";
    private static final String TABLE_CONTACTS = "contactos";

    private static final String KEY_COD="cod";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DIRECCION = "direccion";
    private static final String KEY_GENERO = "genero";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
                " CREATE TABLE "+ TABLE_CONTACTS +
                        " ("+
                            KEY_COD+"INTEGER PRIMARY KEY, "+
                            KEY_ID+" TEXT, "+
                            KEY_NOMBRE+" TEXT, "+
                            KEY_EMAIL+" TEXT, "+
                            KEY_DIRECCION+" TEXT,"+
                            KEY_GENERO+" TEXT"+
                        " )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,contact.getId());
        values.put(KEY_NOMBRE, contact.getNombre());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_DIRECCION, contact.getDireccion());
        values.put(KEY_GENERO, contact.getGenero());

        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

/*    public Contact getContact(String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.query(TABLE_CONTACTS,
                new String[]{KEY_ID,
                             KEY_NOMBRE,
                             KEY_EMAIL,
                             KEY_DIRECCION,
                             KEY_GENERO},
                KEY_ID+"=?",
                new String[]{id},
                 null,
                  null,
                 null,
                   null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(cursor.getString(0),
                                      cursor.getString(1),
                                      cursor.getString(2),
                                      cursor.getString(3),
                                      cursor.getString(4));
        return contact;
    }*/

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();

        String seleccion = "SELECT * FROM "+TABLE_CONTACTS;

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery(seleccion,null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Contact contact = new Contact();
                contact.setId(cursor.getString(0));
                contact.setNombre(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setDireccion(cursor.getString(3));
                contact.setGenero(cursor.getString(4));
                contactList.add(contact);
            }
        }
        return contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, contact.getNombre());
        values.put(KEY_EMAIL,contact.getEmail());
        values.put(KEY_DIRECCION,contact.getDireccion());
        values.put(KEY_GENERO,contact.getGenero());

        return DB.update(TABLE_CONTACTS,values,KEY_ID+"=?",
                         new String[]{contact.getId()});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase DB = this.getWritableDatabase();

        DB.delete(TABLE_CONTACTS,KEY_ID+"=?",
                  new String[]{contact.getId()});

        DB.close();
    }



}
