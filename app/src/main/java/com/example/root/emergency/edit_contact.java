package com.example.root.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by root on 2/20/15.
 */
public class edit_contact extends ActionBarActivity implements View.OnClickListener
{
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button save;
    private Button delete;
    private Button exit;

    private int contact_id =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contacts);

        name = (EditText) findViewById(R.id.edit_name);
        phone = (EditText) findViewById(R.id.edit_phone);
        email = (EditText) findViewById(R.id.edit_email);

        save = (Button) findViewById(R.id.contact_save);
        delete = (Button) findViewById(R.id.contact_del);


        save.setOnClickListener(this);
        delete.setOnClickListener(this);



        contact_id =0;

        Intent intent = getIntent();

        contact_id = intent.getIntExtra("contact", 0);

        dataRepo getContactData = new dataRepo(this);

        contactProfile contact = new contactProfile();

        contact = getContactData.getContactById(contact_id);

        name.setText(contact.contactName);
        phone.setText(contact.contactPhone);
        email.setText(contact.contactEmail);





    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == (R.id.contact_save))
        {
            dataRepo getContactData = new dataRepo(this);

            contactProfile contact = new contactProfile();

            contact.contactName = name.getText().toString();
            contact.contactEmail = email.getText().toString();
            contact.contactPhone = phone.getText().toString();

            contact.contatcId = contact_id;

            if (contact_id == 0)
            {
                contact_id = getContactData.insertContact(contact);
                Toast.makeText(this, "New Contact has been added", Toast.LENGTH_SHORT).show();
            }

            else {
                getContactData.updateContact(contact);
                Toast.makeText(this, "Contact has been updated", Toast.LENGTH_SHORT).show();

            }


        }

        else if (v.getId() == (R.id.contact_del))
        {
            dataRepo getContactData = new dataRepo(this);

            contactProfile contact = new contactProfile();

            getContactData.deleteContact(contact_id);
            Toast.makeText(this, "Contact has been deleted", Toast.LENGTH_SHORT).show();
            finish();

        }

//        else if (v.getId() == (R.id.contact_exit))
//        {
////            Intent intent = new Intent(this, select_action.class);
////            startActivity(intent);
//        }


    }
}
