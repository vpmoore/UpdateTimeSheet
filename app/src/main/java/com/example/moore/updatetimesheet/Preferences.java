package com.example.moore.updatetimesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {

    EditText docketInput;
    EditText emailtoInput;
    TextView dispInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        docketInput = (EditText) findViewById(R.id.docketInput);
        emailtoInput = (EditText) findViewById(R.id.emailtoInput);
        dispInfo = (TextView) findViewById(R.id.dispInfo);

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String emailto = sharedPref.getString("emailto", "");
        int docketNumber = sharedPref.getInt("docketNumber", -1);
        emailtoInput.setText(emailto);
        docketInput.setText(String.valueOf(docketNumber));

    }

    //save the users info
    public void saveOnClick(View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editoz = sharedPref.edit();
        editoz.putString("emailto", emailtoInput.getText().toString());
        //  int tnum = ;
        editoz.putInt("docketNumber", (Integer.parseInt(docketInput.getText().toString())));
        editoz.apply();

        Toast.makeText(this, "saved", Toast.LENGTH_LONG).show();
    }

    public void dispOnClick(View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String emailto = sharedPref.getString("emailto", "");
        int docketNumber = sharedPref.getInt("docketNumber", -1);
        dispInfo.setText(emailto + "" + (String.valueOf(docketNumber)));

    }

    public void returnToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
