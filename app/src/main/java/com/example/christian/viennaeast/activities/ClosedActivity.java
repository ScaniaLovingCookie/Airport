package com.example.christian.viennaeast.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.christian.viennaeast.R;

public class ClosedActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed);

        prefs = getSharedPreferences("PASS_SET", 0);

        Button button = (Button) findViewById(R.id.button_open_again);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClosedActivity.this, MainActivity.class));
                finish();
//                VoiceProcessing.reopenAP(getApplicationContext(), ClosedActivity.this);
            }
        });

        TextView textView = (TextView) findViewById(R.id.textView47);
        textView.setText(String.format("Airport is closed! \n Plane in Hangar: \n%s\n\n Closed since: \n%s", prefs.getString("Hangar", ""), prefs.getString("Start", "")));
    }
}
