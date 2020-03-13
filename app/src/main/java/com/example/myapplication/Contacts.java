package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {
    String contact;
    private Button returnToMainButton;
    ArrayList<String> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contactList = new ArrayList<String>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        returnToMainButton = (Button)findViewById(R.id.returnToMain);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


        Intent intent = getIntent();
        contact = intent.getStringExtra("CONTACT");
        intent.getStringExtra("CONTACT");
/*        FileInputStream fis = null;
        try {
            fis = openFileInput("contactFile.txt");
            fis.read(contact.getBytes());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        contactList.add(contact);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contactList);

        ListView listView =(ListView)findViewById(R.id.contactList);
        listView.setAdapter(adapter);
    };

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("CONTACT_LIST", contactList);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contactList = savedInstanceState.getStringArrayList("CONTACT_LIST");
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
};
