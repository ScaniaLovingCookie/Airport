package com.example.christian.viennaeast;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditEventActivity extends AppCompatActivity {

    private Crush activeFlight = new Crush();
    private Crush.Event activeEvent = activeFlight.new Event();
    private Crush[] Flights;
    private Crush.Event[] Events;

    private EditText ET_Date, ET_Info;
    private Spinner Type_Spinner;
    private String[] spinnerArray = activeEvent.Types;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                closeWithResult(false);
            }
        });

        Flights =  XML.getAllActiveFlights();

        if(!getIntent().getSerializableExtra("RequestType").equals("new") && ((Crush)getIntent().getSerializableExtra("Crush")).getEvents().length == 0){
            Intent i = new Intent(this, EventsActivity.class);
            i.putExtra("Crush", (Crush)getIntent().getSerializableExtra("Crush"));
            startActivity(i);
            finish();
        }else {

            if (getIntent().getSerializableExtra("RequestType").equals("edit")) {

                index = (int) getIntent().getSerializableExtra("Event");

                this.setTitle("EDIT EVENT");
                activeFlight = (Crush) getIntent().getSerializableExtra("Crush");
                Events = activeFlight.getEvents();
                activeEvent = Events[index];

            } else if (getIntent().getSerializableExtra("RequestType").equals("delete")) {

                index = (int) getIntent().getSerializableExtra("Event");

                activeFlight = (Crush) getIntent().getSerializableExtra("Crush");
                Events = activeFlight.getEvents();
                activeEvent = Events[index];
                closeWithResult(true);

            } else if (getIntent().getSerializableExtra("RequestType").equals("new")) {
                this.setTitle("NEW EVENT");
                activeFlight = (Crush) getIntent().getSerializableExtra("Crush");
                Events = activeFlight.getEvents();
                activeEvent = activeFlight.new Event();
            }

            Type_Spinner = (Spinner) findViewById(R.id.type_spinner);
            ET_Date = (EditText) findViewById(R.id.editText9);
            ET_Info = (EditText) findViewById(R.id.editText10);

            ET_Date.setText(activeEvent.getDate());
            ET_Info.setText(activeEvent.getInfo());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.spinner_item,
                    spinnerArray);

            Type_Spinner.setAdapter(adapter);
            Type_Spinner.setPopupBackgroundResource(R.color.colorPrimary);

        }

    }

    private void closeWithResult(boolean delete) {

        if(!delete) {

            activeEvent = activeFlight.new Event(ET_Date.getText().toString(), Type_Spinner.getSelectedItemPosition());
            activeEvent.setInfo(ET_Info.getText().toString());

            if (getIntent().getSerializableExtra("RequestType").equals("new")) {

                Crush.Event[] temp = Events;
                int l = Events.length;
                Events = new Crush.Event[l + 1];
                for (int i = 0; i < l; i++) {
                    Events[i] = temp[i];
                }
                Events[l] = activeEvent;
                activeFlight.setEvents(Events);
                Flights[activeFlight.getIndex()] = activeFlight;

                Toast.makeText(this.getApplicationContext(), "new Event saved", Toast.LENGTH_SHORT).show();

            } else if (getIntent().getSerializableExtra("RequestType").equals("edit")) {

                Events[index] = activeEvent;
                activeFlight.setEvents(Events);
                Flights[activeFlight.getIndex()] = activeFlight;

                Toast.makeText(this.getApplicationContext(), "Event edited", Toast.LENGTH_SHORT).show();
            }
        }else {
            List<Crush.Event> l = new ArrayList<Crush.Event>();
            Log.e("index", index + "");
            for(int i = 0; i<Events.length; i++){
                if(i != index){
                    l.add(Events[i]);
                }
            }
            Events = new Crush.Event[l.size()];
            int i = 0;
            for(Crush.Event e:l){
                Events[i] = e;
                i++;
            }

            activeFlight.setEvents(Events);
            Flights[activeFlight.getIndex()] = activeFlight;

            Toast.makeText(this.getApplicationContext(), "Event deleted", Toast.LENGTH_SHORT).show();
        }

        XML.saveXML(this, Flights);

        Intent i = new Intent(this, EventsActivity.class);
        i.putExtra("Crush", activeFlight);
        startActivity(i);

        finish();
    }

}
