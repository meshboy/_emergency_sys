package com.example.root.emergency;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 2/20/15.
 */
public class list_contact extends ListActivity  implements android.view.View.OnClickListener
{

    Button add;
    Button fetchAll;
    TextView contactview;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this, edit_contact.class);
            intent.putExtra("contact",0);
            startActivity(intent);

        }else {

            dataRepo repo = new dataRepo(this);

            ArrayList<HashMap<String, String>> studentList =  repo.getContactList();
            if(studentList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        contactview = (TextView) view.findViewById(R.id.contact_id);
                        String contactId = contactview.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), edit_contact.class);
                        objIndent.putExtra("contact", Integer.parseInt( contactId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( list_contact.this,studentList, R.layout.view_contacts_entry, new String[] { "id","name"}, new int[] {R.id.contact_id, R.id.contact_name});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No student!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        add = (Button) findViewById(R.id.btnAdd);
        fetchAll = (Button) findViewById(R.id.btnGetAll);

        add.setOnClickListener(this);
        fetchAll.setOnClickListener(this);
    }


}
