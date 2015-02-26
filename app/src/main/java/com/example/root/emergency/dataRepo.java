package com.example.root.emergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 2/19/15.
 */
public class dataRepo
{
    contactProfile myprofile;

    private DbHelper dbHelper;

    public dataRepo(Context context)
    {
        dbHelper = new DbHelper(context);


    }

    /**
     * insert contact
     */


    public int insertContact (contactProfile profile)
    {
        //open db connection

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(contactProfile.KEY_NAME, profile.contactName);
        values.put(contactProfile.KEY_EMAIL, profile.contactEmail);
        values.put(contactProfile.KEY_PHONE, profile.contactPhone);


        //insert row
        long profile_id = db.insert(contactProfile.contact_TABLE, null, values);
        db.close();

        return (int) profile_id;

    }

    /**
     * insert medical history
     *
     */

    public int insertMedHistory (med_hist med_hist)
    {
        //open db
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(com.example.root.emergency.med_hist.allergy, med_hist.med_allergy);
        values.put(com.example.root.emergency.med_hist.bloodGrp, med_hist.med_bloodGrp);
        values.put(com.example.root.emergency.med_hist.condtion, med_hist.med_condition);
        values.put(com.example.root.emergency.med_hist.diabetic, med_hist.med_diabetic);
        values.put(com.example.root.emergency.med_hist.insurance, med_hist.med_insurance);
        values.put(com.example.root.emergency.med_hist.medication, med_hist.med_medication);
        values.put(com.example.root.emergency.med_hist.nationality, med_hist.med_nationality);
        values.put(com.example.root.emergency.med_hist.name, med_hist.med_name);

        long med_id = db.insert(com.example.root.emergency.med_hist.Medical_table, null, values);
        db.close();

        return (int) med_id;
    }

    /**
     *
     * delete contact
     */

    public void deleteContact (int mycontact_id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(contactProfile.contact_TABLE, contactProfile.KEY_ID + "= ?", new String[] {String.valueOf(mycontact_id)});
        db.close();

    }

    /**
     *
     * delete medical history
     */

    public void deleteMed (int med_id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(med_hist.Medical_table, med_hist.id + "=?", new String[] {String.valueOf(med_id)});

    }

    /**
     *
     * update contact profile
     */

    public void  updateContact (contactProfile profile)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(contactProfile.KEY_NAME, profile.contactName);
        values.put(contactProfile.KEY_EMAIL, profile.contactEmail);
        values.put(contactProfile.KEY_PHONE, profile.contactPhone);


        db.update(contactProfile.contact_TABLE, values, contactProfile.contact_TABLE + "= ?", new String[] {String.valueOf(profile.contatcId)});

        db.close();

    }

    /**
     *
     * update medical history
     */

    public void updateMed(med_hist med_hist)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(com.example.root.emergency.med_hist.allergy, med_hist.med_allergy);
        values.put(com.example.root.emergency.med_hist.bloodGrp, med_hist.med_bloodGrp);
        values.put(com.example.root.emergency.med_hist.condtion, med_hist.med_condition);
        values.put(com.example.root.emergency.med_hist.diabetic, med_hist.med_diabetic);
        values.put(com.example.root.emergency.med_hist.insurance, med_hist.med_insurance);
        values.put(com.example.root.emergency.med_hist.medication, med_hist.med_medication);
        values.put(com.example.root.emergency.med_hist.nationality, med_hist.med_nationality);
        values.put(com.example.root.emergency.med_hist.name, med_hist.med_name);

        db.update(com.example.root.emergency.med_hist.Medical_table, values,
                com.example.root.emergency.med_hist.id + "= ?", new String[] {String.valueOf(med_hist.med_id)});

        db.close();


    }

    public ArrayList<HashMap<String, String>>  getContactList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                contactProfile.KEY_ID + "," +
                contactProfile.KEY_NAME + "," +
                contactProfile.KEY_EMAIL + "," +
                contactProfile.KEY_PHONE +
                " FROM " + contactProfile.contact_TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(contactProfile.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(contactProfile.KEY_NAME)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }
    public contactProfile getContactById(int id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                contactProfile.KEY_ID + "," +
                contactProfile.KEY_NAME + "," +
                contactProfile.KEY_EMAIL + "," +
                contactProfile.KEY_PHONE +
                " FROM " + contactProfile.contact_TABLE
                + " WHERE " +
                contactProfile.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        contactProfile contact = new contactProfile();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id) } );

        if (cursor.moveToFirst()) {
            do {
                contact.contatcId =cursor.getInt(cursor.getColumnIndex(contactProfile.KEY_ID));
                contact.contactName =cursor.getString(cursor.getColumnIndex(contactProfile.KEY_NAME));
                contact.contactEmail  =cursor.getString(cursor.getColumnIndex(contactProfile.KEY_EMAIL));
                contact.contactPhone =cursor.getString(cursor.getColumnIndex(contactProfile.KEY_PHONE));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contact;
    }




    public med_hist getHistoryId (int id)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        String sql = "SELECT " +
                      med_hist.name + ", " +
                      med_hist.nationality + ", " +
                      med_hist.allergy + "," +
                      med_hist.bloodGrp + ", " +
                      med_hist.diabetic + ", " +
                      med_hist.condtion + ", " +
                      med_hist.insurance + ", "+
                      med_hist.medication + ", " +
                      " FROM "  + med_hist.Medical_table +
                      " WHERE " + med_hist.id + "=?";


        med_hist med = new med_hist();

        Cursor cursor = db.rawQuery(sql, new String[] {String.valueOf(id)});

        if(cursor.moveToFirst())
        {
            do{
                med.med_id = cursor.getInt(cursor.getColumnIndex(med_hist.id));
                med.med_name = cursor.getString(cursor.getColumnIndex(med_hist.name));
                med.med_nationality =  cursor.getString(cursor.getColumnIndex(med_hist.nationality));
                med.med_allergy = cursor.getString(cursor.getColumnIndex(med_hist.allergy));
                med.med_bloodGrp= cursor.getString(cursor.getColumnIndex(med_hist.bloodGrp));
                med.med_diabetic = cursor.getString(cursor.getColumnIndex(med_hist.diabetic));
                med.med_condition  = cursor.getString(cursor.getColumnIndex(med_hist.condtion));
                med.med_insurance = cursor.getString(cursor.getColumnIndex(med_hist.insurance));
                med.med_medication = cursor.getString(cursor.getColumnIndex(med_hist.medication));

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return med;




    }


}
