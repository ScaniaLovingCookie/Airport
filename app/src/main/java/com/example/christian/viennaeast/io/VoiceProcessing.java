package com.example.christian.viennaeast.io;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.christian.viennaeast.ADT.Crush;
import com.example.christian.viennaeast.activities.ClosedActivity;
import com.example.christian.viennaeast.activities.MainActivity;

public abstract class VoiceProcessing {

    public static void move(Context context, String iata, String destination){
        Crush active = XML.CrushByIATA(iata);
        Crush[] Flights = XML.getAllActiveFlights();
        active.setGate(destination);
        Flights[active.getIndex()] = active;
        XML.saveXML(context, Flights);
    }

    public static void closeAP(Context context, Activity activity) {
        SharedPreferences prefs = context.getSharedPreferences("PASS_SET", 0);
        SharedPreferences.Editor edit = context.getSharedPreferences("PASS_SET", 0).edit();

        if(XML.getGroundMap().getG01().equals("") && !prefs.getBoolean("Closed", false)){
            Toast.makeText(context, "No Plane in G01!", Toast.LENGTH_SHORT).show();
            edit.putBoolean("Closed", false);
            edit.apply();
            return;
        }

        Intent i = new Intent(context, ClosedActivity.class);
        edit = context.getSharedPreferences("PASS_SET", 0).edit();
        edit.putBoolean("Closed", true);
        if (context.getSharedPreferences("PASS_SET", 0).getString("Hangar", "").equals("")){

            edit.putString("Hangar", XML.getGroundMap().getG01());

        }
        edit.apply();
        context.startActivity(i);
        activity.finish();
    }

    public static void reopen(){

    }
}