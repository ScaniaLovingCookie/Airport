package com.example.christian.viennaeast;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class Password extends Activity{


    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        SharedPreferences settings = getSharedPreferences("PASS_SET", 0);
        password = settings.getString("Pass", "");

        if(password.equals("")){
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("Pass", "Scania4life");
            edit.apply();
            password = settings.getString("Pass", "");
        }

        setContentView(R.layout.activity_password);


        Button sumbitButton = (Button) findViewById(R.id.submitbutton);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                EditText passwordEditText = (EditText) findViewById(R.id.passwordedittext);
                if(passwordEditText.getText().toString().equals(password)){

                    passwordEditText.setText("");
                    startActivity(new Intent(Password.this, MainActivity.class));
                    finish();
                }}});
    }}