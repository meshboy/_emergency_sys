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

    private static final String database_name = "new_emergency.db";

    private static final String create_contact = "CREATE TABLE " + contactProfile.contact_TABLE + "("

            + contactProfile.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + contactProfile.KEY_NAME + " TEXT, "
            + contactProfile.KEY_PHONE + " TEXT, "
            + contactProfile.KEY_EMAIL + " TEXT )";

    private static final String create_medical ="CREATE TABLE " + med_hist.Medical_table+ "(" +

            med_hist.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            med_hist.name + " TEXT, " +
            med_hist.nation + " TEXT, " +
            med_hist.cond + " TEXT, " +
            med_hist.allergy + "  TEXT, " +
            med_hist.bloodGrp + " TEXT, " +
            med_hist.diabetic + " TEXT, " +
            med_hist.insurance +  " TEXT, " +
            med_hist.medication + " TEXT )";


    public DbHelper(Context context)
    {
        super(context, database_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //all tables goes here

        db.execSQL(create_contact);
        db.execSQL(create_medical);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        //if db exists before drop
        db.execSQL("DROP TABLE IF EXITS "  + contactProfile.contact_TABLE);
        db.execSQL("DROP TABLE IF EXITS " + med_hist.Medical_table);
//
        //re-create table
        onCreate(db);

    }
}
