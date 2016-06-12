package com.example.moore.updatetimesheet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {

    EditText emailtoInput;
    EditText usernameInput;
    EditText passwordInput;
    TextView dispInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        emailtoInput = (EditText) findViewById(R.id.emailtoInput);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        dispInfo = (TextView) findViewById(R.id.dispInfo);


        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String emailto = sharedPref.getString("emailto", "");
        String name = sharedPref.getString("username", "");
        String pw = sharedPref.getString("password", "");
        emailtoInput.setText(emailto);
        usernameInput.setText(name);
        passwordInput.setText(pw);
    }


    //Button return_btn = (Button) findViewById(R.id.return_btn);
    public void returnToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    //save the users info
    public void saveOnClick(View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editoz = sharedPref.edit();
        editoz.putString("username", usernameInput.getText().toString());
        editoz.putString("password", passwordInput.getText().toString());
        editoz.putString("emailto", emailtoInput.getText().toString());
        editoz.apply();

        Toast.makeText(this, "saved", Toast.LENGTH_LONG).show();
    }

    public void dispOnClick(View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPref.getString("username", "");
        String pw = sharedPref.getString("password", "");
        dispInfo.setText(name + " " + pw);

    }

}
