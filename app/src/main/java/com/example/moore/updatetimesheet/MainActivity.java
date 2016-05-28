package com.example.moore.updatetimesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static RadioGroup radio_g;
    private static RadioButton radio_choice;
    TextView dispInfo;
    TextView dispEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dispEmail = (TextView) findViewById(R.id.dispEmail);
        dispInfo = (TextView) findViewById(R.id.dispInfo);
        //-----------------pre load text view---------------------------//
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String emailto = sharedPref.getString("emailto", "");
        dispEmail.setText(emailto);


        // Get the value for the run counter
        int counter = sharedPref.getInt("counter", 0);
        // Update the TextView
        dispInfo.setText("This app has been started " + counter + " times.");
        // Increment the counter
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("counter", ++counter);
        editor.apply(); // Very important



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

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String emailto = sharedPref.getString("emailto", "");

        //working sendEmail function
        //  Log.i("Send email", "");
        String[] TO = {emailto};

        //String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        // emailIntent.putExtra(Intent.EXTRA_CC, CC);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_1:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent i = new Intent(this, Preferences.class);
                startActivity(i);
                //.makeText(getApplicationContext(), "Option 1 selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_item_2:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intent = new Intent(this, SetRecipient.class);
                startActivity(intent);
                //Toast.makeText(this, View), "Option 1 selected", Toast.LENGTH_SHORT).show();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
