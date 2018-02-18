package com.example.christian.viennaeast;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewActivity extends AppCompatActivity {

    Crush activeFlight = new Crush();
    Crush[] Flights;
    EditText ET_FirstName, ET_LastName, ET_IATA, ET_IACO, ET_Airline, ET_Callsign, ET_FlightNumber, ET_Gate, ET_Aircraft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeWithResult(false);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_delete);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeWithResult(true);
            }
        });
        fab2.setVisibility(View.INVISIBLE);

        ET_FirstName = (EditText) findViewById(R.id.editText0);
        ET_LastName = (EditText) findViewById(R.id.editText1);
        ET_IATA = (EditText) findViewById(R.id.editText2);
        ET_IACO = (EditText) findViewById(R.id.editText3);
        ET_Callsign = (EditText) findViewById(R.id.editText4);
        ET_Airline = (EditText) findViewById(R.id.editText8);
        ET_Gate = (EditText) findViewById(R.id.editText6);
        ET_Aircraft = (EditText) findViewById(R.id.editText7);
        ET_FlightNumber =(EditText) findViewById(R.id.editText5);

        Flights = XML.getAllActiveFlights();

        if(getIntent().getSerializableExtra("RequestType").equals("edit")){

            this.setTitle("EDIT FLIGHT");

            activeFlight = (Crush) getIntent().getSerializableExtra("Crush");

            ET_FirstName.setText(activeFlight.getFirstName());
            ET_LastName.setText(activeFlight.getLastName());
            ET_IATA.setText(activeFlight.getIATA());
            ET_IACO.setText(activeFlight.getIACO());
            ET_Callsign.setText(activeFlight.getCallsign());
            ET_Airline.setText(activeFlight.getAirline());
            ET_Gate.setText(activeFlight.getGate());
            ET_Aircraft.setText(activeFlight.getAircraft());
            ET_FlightNumber.setText(activeFlight.getFlightNumber());

            fab2.setVisibility(View.VISIBLE);

        }else if(getIntent().getSerializableExtra("RequestType").equals("delete")){
            activeFlight = (Crush) getIntent().getSerializableExtra("Crush");
            closeWithResult(true);
        }

    }

    private void closeWithResult(boolean delete) {

        int tmpIndex = activeFlight.getIndex();

        if(!delete) {
            activeFlight = new Crush(ET_FirstName.getText().toString(), ET_LastName.getText().toString(), ET_IATA.getText().toString(), ET_IACO.getText().toString(), ET_Airline.getText().toString(),
                    ET_Callsign.getText().toString(), ET_FlightNumber.getText().toString(), ET_Gate.getText().toString(), ET_Aircraft.getText().toString());
            activeFlight.setIndex(tmpIndex);

            if (getIntent().getSerializableExtra("RequestType").equals("new")) {

                Crush[] temp = Flights;
                int l = Flights.length;
                Flights = new Crush[l + 1];
                for (int i = 0; i < l; i++) {
                    Flights[i] = temp[i];
                }
                Flights[l] = activeFlight;

                Toast.makeText(this.getApplicationContext(), "new Crush saved", Toast.LENGTH_SHORT).show();

            } else if (getIntent().getSerializableExtra("RequestType").equals("edit")) {
                Flights[activeFlight.getIndex()] = activeFlight;

                Toast.makeText(this.getApplicationContext(), "Crush edited", Toast.LENGTH_SHORT).show();
            }
        }else {
            List<Crush> l = new ArrayList<Crush>();
            for(Crush c:Flights){
                if(!c.getFlightNumber().equals(activeFlight.getFlightNumber())){
                    l.add(c);
                }
            }
            Flights = new Crush[l.size()];
            int i = 0;
            for(Crush c:l){
                Flights[i] = c;
                i++;
            }

            Toast.makeText(this.getApplicationContext(), "Crush deleted", Toast.LENGTH_SHORT).show();
        }

        XML.saveXML(this, Flights);

        Intent intent = new Intent();
        intent.putExtra("crush", activeFlight);
        setResult(RESULT_OK, intent);
        finish();
    }



}
