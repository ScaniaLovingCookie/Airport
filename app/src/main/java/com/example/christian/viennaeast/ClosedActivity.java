package com.example.christian.viennaeast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClosedActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed);

        prefs = getSharedPreferences("PASS_SET", 0);
        edit = prefs.edit();

        Button button = (Button) findViewById(R.id.button_open_again);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                edit.putBoolean("Closed", false);
                edit.putString("Hangar", "");
                edit.apply();
                startActivity(i);
                Log.e("Date", new SimpleDateFormat("ddMMyy", Locale.getDefault()).format(new Date()));
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.textView47);
        textView.setText("Airport is closed! \n Plane in Hangar: \n " + prefs.getString("Hangar", ""));
    }
}
