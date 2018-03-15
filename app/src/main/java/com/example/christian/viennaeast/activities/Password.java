package com.example.christian.viennaeast.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.christian.viennaeast.R;

import java.util.Map;


public class Password extends Activity{


    String password;
    EditText passwordEditText;
    Button sumbitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.activity_password);

        passwordEditText = (EditText) findViewById(R.id.passwordedittext);
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            sumbitButton.callOnClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        final SharedPreferences settings = getSharedPreferences("PASS_SET", 0);
        password = settings.getString("Pass", "");

        if(password.equals("")){
//            SharedPreferences.Editor edit = settings.edit();
//            edit.putString("Pass", "Scania4life");
//            edit.apply();
//            password = settings.getString("Pass", "");
            startActivity(new Intent(this, EditPasswordActivity.class));
            finish();
        }

        sumbitButton = (Button) findViewById(R.id.submitbutton);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(passwordEditText.getText().toString().equals(password)){

                    passwordEditText.setText("");
                    if(settings.getBoolean("Closed", false)){
                        startActivity(new Intent(Password.this, ClosedActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(Password.this, MainActivity.class));
                        finish();
                    }
                }}});
    }
}