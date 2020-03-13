package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private EditText nameEditText;
    private EditText firstNameEditText;
    private EditText numberEditText;
    private String contact;
    private Button send;
    private int counter = 1;

    private String initContact(){
        nameEditText =(EditText)findViewById(R.id.name);
        firstNameEditText = (EditText)findViewById(R.id.firstName);
        numberEditText =(EditText)findViewById(R.id.number);
        String name = nameEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String number = numberEditText.getText().toString();

        return name + " " + firstName + " " + number;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.counter);
        textView.setText("La page a été visitée " + counter + " fois");

        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Contacts.class);
                contact = initContact();
                intent.putExtra("CONTACT", contact);
                startActivityForResult(intent,1);
/*                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("contactFile.txt", Context.MODE_APPEND);
                    fos.write(initContact().getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        ContactBDD contactBDD = new ContactBDD(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        counter += 1;
        TextView textView = (TextView)findViewById(R.id.counter);
        textView.setText("La page a été visitée " + counter + " fois");
    }
}
