package com.example.christian.viennaeast.io;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.christian.viennaeast.ADT.Crush;
import com.example.christian.viennaeast.activities.ClosedActivity;
import com.example.christian.viennaeast.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public abstract class VoiceProcessing {

    public static void move(Context context, String iata, String destination){

        if (Arrays.asList("AP1", "AP2", "AP3").contains(destination)){
            String d = "G" + destination.charAt(2) + (XML.getGroundMap().getApronLength(Integer.valueOf(destination.charAt(2) + ""))+ 1);
            destination = d;
        }
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
            edit.putString("Start", new SimpleDateFormat("dd. MMMM YYYY", Locale.UK).format(new Date()));

        }
        edit.apply();
        context.startActivity(i);
        activity.finish();
    }

    public static void reopenAP(Context context, Activity activity){
        SharedPreferences prefs = context.getSharedPreferences("PASS_SET", 0);
        SharedPreferences.Editor edit = prefs.edit();

        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
        edit.putBoolean("Closed", false);
        edit.putString("Hangar", "");
        edit.remove("Start");
        edit.apply();
        context.startActivity(i);
        activity.finish();

    }
}