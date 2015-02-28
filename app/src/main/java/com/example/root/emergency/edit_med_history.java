package com.example.root.emergency;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by root on 2/26/15.
 */
public class edit_med_history extends ActionBarActivity implements View.OnClickListener
{
    EditText name, allergy, bldgrp, med_cond;
    RadioGroup natnty, diabetic, insurance, medication;
    RadioButton nija, other, dia_yes, dia_no,  yesIn, noIn, yesMed, noMed;
    Button delete, save;
    int history_id =0;
    private String nation_value, diabetic_value, insurace_value, medication_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_med_history);

        delete = (Button) findViewById(R.id.med_delete);
        save = (Button) findViewById(R.id.med_save);

        delete.setOnClickListener(this);
        save.setOnClickListener(this);

        name = (EditText) findViewById(R.id.med_name);
        allergy = (EditText) findViewById(R.id.allergy);
        bldgrp = (EditText) findViewById(R.id.bldgrp);
        med_cond = (EditText) findViewById(R.id.med_con);

        natnty = (RadioGroup) findViewById(R.id.nationgrp_med);
        int selectNation = natnty.getCheckedRadioButtonId();
        nija = (RadioButton) findViewById(selectNation);
        other = (RadioButton) findViewById(R.id.other);


        diabetic = (RadioGroup) findViewById(R.id.diabetic_group);
        int selectDiabetic = diabetic.getCheckedRadioButtonId();
        dia_yes = (RadioButton) findViewById(selectDiabetic);
        dia_no =(RadioButton) findViewById(R.id.dia_no);


        insurance = (RadioGroup) findViewById(R.id.med_ins);
        int select_insurance = insurance.getCheckedRadioButtonId();
        yesIn = (RadioButton) findViewById(R.id.yesIn);
        noIn = (RadioButton) findViewById(select_insurance);

        medication = (RadioGroup) findViewById(R.id.medication);
        int select_medication = medication.getCheckedRadioButtonId();
        yesMed = (RadioButton) findViewById(R.id.medon_yes);
        noMed = (RadioButton) findViewById(select_medication);

        view();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.med_save)
        {
            dataRepo repo = new dataRepo(this);
            med_hist history = new med_hist();

            history.med_allergy = allergy.getText().toString();
            history.med_name = name.getText().toString();
            history.med_bloodGrp = bldgrp.getText().toString();
            history.med_condition = med_cond.getText().toString();

            //get nationality

            if (other.isChecked())
            {
                history.med_nationality = "others";
            }else{
                history.med_nationality = nija.getText().toString();
            }


            //get diabetic

            if (dia_yes.isChecked())
            {
                history.med_diabetic = "yes";
            }else{
                history.med_diabetic = dia_no.getText().toString();
            }

            //get insuarce
            if (yesIn.isChecked())
            {
                history.med_insurance = "yes";
            }else{
                history.med_insurance = noIn.getText().toString();
            }

            //get medication
            if(yesMed.isChecked())
            {
                history.med_medication = "yes";
            }else{
                history.med_medication = noMed.getText().toString();
            }


            //save data
            history.med_id = history_id;

            if (history_id ==0)
            {
                history_id = repo.insertMedHistory(history);
                Toast.makeText(this, "Saved successfully" , Toast.LENGTH_SHORT).show();
            }

            else {
                repo.updateMed(history);
                Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
            }

        }

        if (v.getId() == R.id.med_delete)
        {
            dataRepo repo = new dataRepo(this);

            medical_history history = new medical_history();

            repo.deleteMed(history_id);
            Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();


        }

    }

    public void view()
    {
//        contact_id =0;
//
//        Intent intent = getIntent();
//
//        contact_id = intent.getIntExtra("contact", 0);
//
//        dataRepo getContactData = new dataRepo(this);
//
//        contactProfile contact = new contactProfile();
//
//        contact = getContactData.getContactById(contact_id);
//
//        name.setText(contact.contactName);
//        phone.setText(contact.contactPhone);
//        email.setText(contact.contactEmail);

        history_id =0;

        dataRepo repo = new dataRepo(this);

        med_hist history = new med_hist();

        history = repo.getHistoryId();

        name.setText(history.med_name);
        allergy.setText(history.med_allergy);
        med_cond.setText(history.med_condition);
        bldgrp.setText(history.med_bloodGrp);

        Toast.makeText(this, history.med_nationality, Toast.LENGTH_SHORT).show();

        if(!(history.med_nationality == nija.getText().toString()))
        {
            other.setChecked(true);
        }

        if (!(history.med_diabetic == "No"))
        {
            dia_yes.setChecked(true);
        }

        if(!(history.med_insurance == "No"))
        {
            yesIn.setChecked(true);
        }

        if(!(history.med_medication == "No"))
        {
            yesMed.setChecked(true);
        }





    }
}
