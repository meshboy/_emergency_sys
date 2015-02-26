package com.example.root.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by root on 2/24/15.
 */
public class emergency_service extends ActionBarActivity implements View.OnClickListener
{
    private Button fire;
    private Button police;
    private Button ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.emergency_services);


        fire = (Button) findViewById(R.id.Fire);
        police = (Button) findViewById(R.id.Police);
        ambulance = (Button) findViewById(R.id.ambulance);

        fire.setOnClickListener(this);
        police.setOnClickListener(this);
        ambulance.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.Fire)
        {
            Intent intent = new Intent(emergency_service.this, com.example.root.emergency.fire.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.Police)
        {
            Intent intent = new Intent(emergency_service.this, com.example.root.emergency.police.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ambulance)
        {
            Intent intent = new Intent(emergency_service.this, com.example.root.emergency.ambulance.class);
            startActivity(intent);
        }

    }
}
