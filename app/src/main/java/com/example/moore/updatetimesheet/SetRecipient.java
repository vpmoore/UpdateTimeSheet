package com.example.moore.updatetimesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetRecipient extends AppCompatActivity {

    EditText ticketInput;
    TextView dispInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_recipient);

        ticketInput = (EditText) findViewById(R.id.ticketInput);
        dispInfo = (TextView) findViewById(R.id.dispInfo);


//        //-----------------pre load text view---------------------------//
//        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        int ticketNumber = sharedPref.getInt("ticketNumber", DEFAULT_KEYS_DIALER);
//
//        ticketInput.setText(String.valueOf(ticketNumber));
//        //----------------counter----------------------------------------//
//        // Get the value for the run counter
//        int counter = sharedPref.getInt("counter", 0);
//        // Update the TextView
//        TextView text = (TextView) findViewById(R.id.text);
//        text.setText("This app has been started " + counter + " times.");
//        // Increment the counter
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt("counter", ++counter);
//        editor.apply(); // Very important

    }

    //save the users info...SAVES INTEGERS!!!
    public void saveOnClick(View view) {

        int tnum = (Integer.parseInt(ticketInput.getText().toString()));
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editoz = sharedPref.edit();
        editoz.putInt("ticketNumber", tnum);
        editoz.apply();
        Toast.makeText(this, "saved", Toast.LENGTH_LONG).show();
    }

    public void dispOnClick(View view) {

        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        int ticketNumber = sharedPref.getInt("ticketNumber", -1);
        dispInfo.setText(String.valueOf(ticketNumber));

    }


    public void returnToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}