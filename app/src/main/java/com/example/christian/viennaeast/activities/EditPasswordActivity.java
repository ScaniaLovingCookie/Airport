package com.example.christian.viennaeast.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.christian.viennaeast.R;

public class EditPasswordActivity extends AppCompatActivity {

    EditText passwordEditText2, passwordEditText3;
    Button sumbitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        passwordEditText2 = (EditText) findViewById(R.id.passwordedittext2);
        passwordEditText3 = (EditText) findViewById(R.id.passwordedittext3);

        sumbitButton = (Button) findViewById(R.id.submitbutton);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(passwordEditText2.getText().toString().equals("") || passwordEditText3.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter Passwords!", Toast.LENGTH_LONG).show();
                }else if(passwordEditText2.getText().toString().equals(passwordEditText3.getText().toString())){

                    SharedPreferences.Editor edit = getSharedPreferences("PASS_SET", 0).edit();
                    edit.putString("Pass", passwordEditText2.getText().toString());
                    edit.apply();
                    startActivity(new Intent(EditPasswordActivity.this, Password.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG).show();
                }

            }});
    }
}
