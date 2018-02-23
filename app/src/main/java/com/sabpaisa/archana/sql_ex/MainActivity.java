package com.sabpaisa.archana.sql_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DatabaseHandler(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addcontact(new Contacts("Ravi", "9100000000"));
        db.addcontact(new Contacts("9898989898", "Srinivas"));
        db.addcontact(new Contacts("Tommy", "9522222222"));
        db.addcontact(new Contacts("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contacts> contacts = db.getAllContacts();

        for (Contacts cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
