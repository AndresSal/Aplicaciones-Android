package com.example.user.laywerbusiness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21/12/2017.
 */

public class DataBaseSQLite extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="clientManager";
    public static final String TABLE_CLIENTS="clients";

    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_START_DATE="start_date";
    public static final String KEY_END_DATE="end_date";
    public static final String KEY_STATE="state";

    public DataBaseSQLite(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String CREATE_CLIENTS_TABLE="CREATE TABLE "+TABLE_CLIENTS+"("+KEY_ID+" INTEGER PRIMARY KEY, "
             +KEY_NAME+" TEXT,"+KEY_START_DATE+" TEXT,"+KEY_END_DATE+" TEXT,"+KEY_STATE+" TEXT"+")";
     db.execSQL(CREATE_CLIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLIENTS);
    onCreate(db);
    }

    public void addClient(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,client.get_name());
        values.put(KEY_START_DATE,client.get_start_date());
        values.put(KEY_END_DATE,client.get_end_date());
        values.put(KEY_STATE,client.get_state());

        db.insert(TABLE_CLIENTS,null, values);
        db.close();
    }

    public Client getClientByID(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_CLIENTS, new String[]{KEY_ID,KEY_NAME,KEY_START_DATE,KEY_END_DATE,KEY_STATE},
                KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null) ;
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        Client client = new Client(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return client;
    }

    public Client getClientByName(String name)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor =db.query(TABLE_CLIENTS,new String[]{KEY_ID,KEY_NAME,KEY_START_DATE,KEY_END_DATE,KEY_STATE},
                KEY_NAME+"=?", new String[]{name},null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        Client client= new Client(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return client;
    }

    public ArrayList<Client> getAllClients()
    {
        ArrayList<Client>client_list=new ArrayList<Client>();
        String sql_select = "SELECT * FROM "+TABLE_CLIENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);

        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                Client client = new Client();
                client.set_id(Integer.parseInt(cursor.getString(0)));
                client.set_name(cursor.getString(1));
                client.set_start_date(cursor.getString(2));
                client.set_end_date(cursor.getString(3));
                client.set_state(cursor.getString(4));
                client_list.add(client);
            }
        }

        return client_list;
    }

    public int updateClientByID(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,client.get_name());
        values.put(KEY_START_DATE, client.get_start_date());
        values.put(KEY_END_DATE, client.get_end_date());
        values.put(KEY_STATE,client.get_state());

        return db.update(TABLE_CLIENTS, values, KEY_ID+"=?", new String[]{String.valueOf(client.get_id())});
    }

    public int updateClientByName(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID, client.get_id());
        values.put(KEY_NAME,client.get_name());
        values.put(KEY_START_DATE,client.get_start_date());
        values.put(KEY_END_DATE,client.get_end_date());
        values.put(KEY_STATE,client.get_state());

        return db.update(TABLE_CLIENTS, values, KEY_ID+"=?"+" AND "+KEY_NAME+"=?", new String[]{String.valueOf(client.get_id()),client.get_name()});
    }

    public void deleteClient(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CLIENTS,KEY_ID+"=?", new String[]{String.valueOf(String.valueOf(client.get_id()))});
        db.close();
    }

}
