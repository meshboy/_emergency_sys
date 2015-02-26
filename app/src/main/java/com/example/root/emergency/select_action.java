package com.example.root.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by root on 2/20/15.
 */
public class select_action extends ActionBarActivity implements View.OnClickListener
{
    private ImageButton run;
    private ImageButton service;
    private ImageButton history;
    private ImageButton sos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_action);



        run = (ImageButton) findViewById(R.id.run);
        service = (ImageButton) findViewById(R.id.service);
        history = (ImageButton) findViewById(R.id.history);
        sos = (ImageButton) findViewById(R.id.sos);

        run.setOnClickListener(this);
        service.setOnClickListener(this);
        history.setOnClickListener(this);
        sos.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.run)
        {
            Intent intent = new Intent(this, com.example.root.emergency.list_contact.class);
            startActivity(intent);

        }

        if(v.getId() == R.id.service)
        {
            Intent intent = new Intent(this, com.example.root.emergency.emergency_service.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.history)
        {

        }
        if(v.getId() == R.id.run)
        {

        }

    }
}
