package com.example.root.emergency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 2/19/15.
 */
public class DbHelper extends SQLiteOpenHelper
{

    private static final int db_version = 4;


    //database name

    private static final String database_name = "emergency.db";


    public DbHelper(Context context)
    {
        super(context, database_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //all tables goes here

        String create_contact = "CREATE TABLE " + contactProfile.contact_TABLE + "("

                       + contactProfile.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                       + contactProfile.KEY_NAME + " TEXT, "
                       + contactProfile.KEY_PHONE + " TEXT, "
                       + contactProfile.KEY_EMAIL + " TEXT )";






        String create_medHistory ="CREATE TABLE " + medical_history.Medical_table+ "(" +

                         medical_history.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         medical_history.name + " TEXT, " +
                         medical_history.allergy + "  TEXT, " +
                         medical_history.bloodGrp + " TEXT, " +
                         medical_history.diabetic + " TEXT, " +
                         medical_history.insurance +  " TEXT, " +
                         medical_history.medication + " TEXT )";


        db.execSQL(create_contact);
        db.execSQL(create_medHistory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        //if db exists before drop
        db.execSQL("DROP TABLE IF EXITS "  + contactProfile.contact_TABLE);
        db.execSQL("DROP TABLE IF EXITS " + medical_history.Medical_table);
//
//        //re-create table
//        onCreate(db);

    }
}
