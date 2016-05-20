package com.example.moore.updatetimesheet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static RadioGroup radio_g;
    private static RadioButton radio_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_g = (RadioGroup) findViewById(R.id.radio_choices);
                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Select option", Toast.LENGTH_SHORT).show();
                } else {
                    int selected_id = radio_g.getCheckedRadioButtonId();
                    radio_choice = (RadioButton) findViewById(selected_id);
                    String optInfo = radio_choice.getText().toString();
                    sendEmail(optInfo);
                }
            }
        });
    }

//---------------------------add info send email-------------------------------
    private void sendEmail(String optInfo) {

        //working sendEmail function
        Log.i("Send email", "");
        String[] TO = {"vpmoore.is@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, optInfo);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
//--------------------------------tests-----------------------------------

//**//*//**//**//**//** Called when the user clicks the Send button *//**//**//**//**//**//**//**//*
    public void setRecipient(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, SetRecipient.class);
    }

//----------------------option menus----------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.menu_item_1:
                Toast.makeText(getApplicationContext(), "Option 1 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_2:
                Toast.makeText(getApplicationContext(), "Option 1 selected", Toast.LENGTH_SHORT).show();

                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
