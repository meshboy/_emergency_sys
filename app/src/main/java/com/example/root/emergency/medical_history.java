package com.example.root.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 2/26/15.
 */
public class medical_history extends ActionBarActivity implements View.OnClickListener
{
    Button edit;
    private TextView name, natn, allergy, bldgrp, diabetic, med_cond, med_ins, on_med;
    private int history_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_history);

     edit = (Button) findViewById(R.id.edit_button);

     edit.setOnClickListener(this);

     name = (TextView) findViewById(R.id.name);
     natn = (TextView) findViewById(R.id.nationality);
     allergy = (TextView) findViewById(R.id.allergy);
     bldgrp = (TextView) findViewById(R.id.blood_group);
     diabetic = (TextView) findViewById(R.id.diabetic);
     med_cond = (TextView) findViewById(R.id.med_condition);
     med_ins = (TextView) findViewById(R.id.insurance);
     on_med = (TextView) findViewById(R.id.on_med);


     dataRepo repo = new dataRepo(this);
     med_hist history = new med_hist();

     history = repo.getHistoryId();

     if(history != null)
     {
         name.setText(history.med_name);
         natn.setText(history.med_nationality);
         allergy.setText(history.med_allergy);
         bldgrp.setText(history.med_bloodGrp);
         diabetic.setText(history.med_diabetic);
         med_cond.setText(history.med_condition);
         med_ins.setText(history.med_insurance);
         on_med.setText(history.med_medication);
     }

//        Toast.makeText(this, String.valueOf(history), Toast.LENGTH_SHORT).show();

        Log.d("history", String.valueOf(history));

    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(medical_history.this, com.example.root.emergency.edit_med_history.class);
        startActivity(intent);

    }
}
