package com.example.christian.viennaeast.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.christian.viennaeast.fragments.AirportFragment;
import com.example.christian.viennaeast.ADT.Crush;
import com.example.christian.viennaeast.fragments.FlightsFragment;
import com.example.christian.viennaeast.fragments.HistoryFragment;
import com.example.christian.viennaeast.fragments.ProceduresFragment;
import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.io.VoiceProcessing;
import com.example.christian.viennaeast.io.XML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar = null;
    NavigationView navigationView = null;

    FloatingActionButton fab, fab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRow();
            }
        });

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSharedPreferences("PASS_SET", 0).getBoolean("Closed", false)){
                    VoiceProcessing.reopenAP(getApplicationContext(), MainActivity.this);
                }else{
                    VoiceProcessing.closeAP(getApplicationContext(), MainActivity.this);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FlightsFragment flightsFragment = new FlightsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, flightsFragment);
        fragmentTransaction.commit();


        fab.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.INVISIBLE);
        if(getSharedPreferences("PASS_SET", 0).getBoolean("Closed", false)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    displayClosed();
                }
            }, 500);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getSharedPreferences("PASS_SET", 0).getBoolean("Closed", false)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    displayClosed();
                }
            }, 500);
        }
    }

    private void displayClosed(){
        final TextView tv = new TextView(this);
        tv.setText("AIRPORT CLOSED!");
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(getColor(R.color.colorAccent));
        tv.setBackgroundColor(Color.parseColor("#500000"));
        getWindowManager().addView(tv, getActionBarLayoutParams());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getWindowManager().removeViewImmediate(tv);
            }
        }, 5000);
    }

    private static final int[] ACTION_BAR_SIZE = new int[] {
            android.R.attr.actionBarSize
    };
    private WindowManager.LayoutParams getActionBarLayoutParams() {
        // Retrieve the height of the status bar
        final Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        final int statusBarHeight = rect.top;

        // Retrieve the height of the ActionBar
        final TypedArray actionBarSize = obtainStyledAttributes(ACTION_BAR_SIZE);
        final int actionBarHeight = actionBarSize.getDimensionPixelSize(0, 0);
        actionBarSize.recycle();

        // Create the LayoutParams for the View
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, actionBarHeight,
                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP;
        params.x = 0;
        params.y = statusBarHeight;
        return params;
    }

    private void newRow() {

        Intent i = new Intent(this, NewActivity.class);
        i.putExtra("RequestType", "new");
        startActivityForResult(i, 1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getSharedPreferences("PASS_SET", 0).edit().remove("Pass").apply();
            Toast.makeText(this, "Pass cleared", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_airport) {

            AirportFragment airportFragment = new AirportFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, airportFragment);
            fragmentTransaction.commit();

            fab.setVisibility(View.INVISIBLE);
            fab2.setVisibility(View.VISIBLE);


        } else if (id == R.id.nav_flights) {

            FlightsFragment flightsFragment = new FlightsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, flightsFragment);
            fragmentTransaction.commit();

            fab.setVisibility(View.VISIBLE);
            fab2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_history) {

            HistoryFragment historyFragment = new HistoryFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, historyFragment);
            fragmentTransaction.commit();

            fab.setVisibility(View.INVISIBLE);
            fab2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_procedures) {

            ProceduresFragment proceduresFragment = new ProceduresFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, proceduresFragment);
            fragmentTransaction.commit();

            fab.setVisibility(View.INVISIBLE);
            fab2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_voice){
            getSpeechInput();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void RowClick(View view) {
        TableRow row = (TableRow) view;
        TextView FlightNumber = (TextView) row.findViewById(R.id.Flight_view);
        String fn = FlightNumber.getText().toString();
        Crush tmp = XML.CrushByFlightNumber(fn);
        Intent i = new Intent(this, CrushActivity.class);
//        i.putExtra("actives", allActiveFlights);
        i.putExtra("RequestType", "edit");
        i.putExtra("Crush", tmp);
        startActivityForResult(i, 2);
    }

    public void AirportButtonClick(View view) {

        Button button = (Button) view;
        Intent i = new Intent(this, GroundActivity.class);

        switch (button.getText().toString()){
            case "Gate 01": i.putExtra("tab", 0); break;
            case "Apron One": i.putExtra("tab", 1); break;
            case "Apron Two": i.putExtra("tab", 2); break;
            case "Apron Three": i.putExtra("tab", 3); break;
            case "Pattern": i.putExtra("tab", 4); break;
            case "Gone": i.putExtra("tab", 5); break;
        }

        startActivity(i);
    }


    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.UK.toString());

        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                XML.readXML(this);
                XML.doTable(this, this.getCurrentFocus());
                break;
            case 2:
                XML.readXML(this);
                XML.doTable(this, this.getCurrentFocus());
                break;
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String t = "";
                    String t2 = "";
                    for(String s:result) {
                        String[] a = s.split(" ");
                        t2 = t2 + s + "\n";
                        if (a.length < 2) {
                            continue;
                        }
                        if ((a.length > 2 && (XML.getIATAs().contains(a[0].toUpperCase())) &&
                                (((a[1].toLowerCase().equals("move")) || (a[1].toLowerCase().equals("change"))) &&
                                        ((a.length > 3 && (a[2].toLowerCase().equals("apron")) && (Arrays.asList("one", "two", "too", "to", "three", "free").contains(a[3].toLowerCase())))
                                                || (a.length > 3 && (a[2].toLowerCase().equals("gate")) && (a[3].toLowerCase().equals("one"))) || (a[2].toLowerCase().equals("pattern")) || (a[2].toLowerCase().equals("gone"))
                                        ))
                        ) || ((a[0].toLowerCase().equals("airport")) && ((a[1].toLowerCase().equals("close")) || (a[1].toLowerCase().equals("reopen"))))
                                ){
                            t = s;
                        }

                        String toast = "";
                        if (a.length > 2 && (XML.getIATAs().contains(a[0].toUpperCase())) && ((a[1].toLowerCase().equals("move")) || (a[1].toLowerCase().equals("change")))) {
                            Crush c = XML.CrushByIATA(a[0].toUpperCase());
                            toast = toast + c.getFlightNumber() + "/" + c.getCallsign() + " is moved to:\n";
                            if (a.length > 3 && (a[2].toLowerCase().equals("apron"))) {
                                switch (a[3].toLowerCase()) {
                                    case "one":
                                        toast = toast + "AP1";
                                        VoiceProcessing.move(this, a[0].toUpperCase(), "AP1");
                                        break;
                                    case "two":
                                    case "too":
                                    case "to":
                                        toast = toast + "AP2";
                                        VoiceProcessing.move(this, a[0].toUpperCase(), "AP2");
                                        break;
                                    case "three":
                                    case "free":
                                        toast = toast + "AP3";
                                        VoiceProcessing.move(this, a[0].toUpperCase(), "AP3");
                                        break;
                                    default:
                                        continue;
                                }
                            } else if (a.length > 3 && (a[2].toLowerCase().equals("gate")) && (a[3].toLowerCase().equals("one"))) {
                                toast = toast + "G01";
                                VoiceProcessing.move(this, a[0].toUpperCase(), "G01");
                            } else if (a[2].toLowerCase().equals("pattern")) {
                                toast = toast + "PAT";
                                VoiceProcessing.move(this, a[0].toUpperCase(), "Pattern");
                            } else if (a[2].toLowerCase().equals("gone")) {
                                toast = toast + "\"GONE\"";
                                VoiceProcessing.move(this, a[0].toUpperCase(), "GONE");
                            } else {
                                continue;
                            }
                        } else if (a.length > 1 && ((a[0].toLowerCase().equals("airport")))) {
                            toast = toast + "airport is being ";
                            if (a[1].toLowerCase().equals("close")) {
                                VoiceProcessing.closeAP(this, this);
                                toast = toast + "closed";
                            } else if (a[1].toLowerCase().equals("reopen")) {
                                VoiceProcessing.reopenAP(this, this);
                                toast = toast + "re-opened";
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                        Log.e("Sys-Voice", toast);
                        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
                    }
                    Log.e("SYS", t2);
                    if(t.equals("")){
                        Toast.makeText(this, "Not recognized", Toast.LENGTH_LONG).show();
                        getSpeechInput();
                        return;
                    }
                    if(navigationView.getMenu().findItem(R.id.nav_flights).isChecked()) {
                        XML.readXML(this);
                        XML.doTable(this, this.getCurrentFocus());
                    }
                }
                break;
        }
    }
}