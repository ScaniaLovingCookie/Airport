package com.example.christian.viennaeast;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class XML {

    private static Crush[] allActiveFlights = new Crush[0];
    private static Ground GroundMap = new Ground();
    private static List<List<String>> History = new ArrayList<>();
    private static List<String> Months = new ArrayList<>();

    private static List<String> IATAs = new ArrayList<>();

    public static void readXML(Context context){

        File f = new File(context.getExternalFilesDir("").getAbsolutePath(), "Flight.xml");

        FileInputStream fin = null;

        XmlPullParserFactory factory;

        String[] AP1 = new String[8], AP2 = new String[8], AP3 = new String[8], PAT = new String[10], GONE = new String[10];
        String G01 = "";
        int ip = 0;
        int ig = 0;
        IATAs = new ArrayList<>();

        try {

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            fin = new FileInputStream(f);
            Crush tmp = new Crush();
            Crush.Event tmpE = tmp.new Event();
            List<Crush.Event> Events = new ArrayList<Crush.Event>();
            String lastTag = "";

            xpp.setInput(fin, null);

            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                }else if (eventType == XmlPullParser.START_TAG){
                    switch (xpp.getName()){
                        case "Flights":
                            allActiveFlights = new Crush[Integer.valueOf(xpp.getAttributeValue(0))]; break;
                        case "Flight":
                            tmp = new Crush();
                            tmp.setFlightNumber(xpp.getAttributeValue(0));
                            tmp.setIndex(Integer.valueOf(xpp.getAttributeValue(1)));
                            break;
                        case "Events":
                            Events = new ArrayList<Crush.Event>();
                            break;
                        case "Event":
                            tmpE = tmp.new Event();
                            break;
                        default:
                            lastTag = xpp.getName(); break;
                    }

                }else if (eventType == XmlPullParser.END_TAG){
//                    if(xpp.getName().equals("Flight")){
//                        allActiveFlights[tmp.getIndex()] = tmp;
//                        tmp = new Crush();
//                    }
                    switch (xpp.getName()){
                        case "Flight":
                            allActiveFlights[tmp.getIndex()] = tmp;
                            tmp = new Crush();
                            break;
                        case "Event":
                            Events.add(tmpE);
                            tmpE = tmp.new Event();
                            break;
                        case "Events":
                            Crush.Event[] eA = new Crush.Event[Events.size()];
                            for(Crush.Event e:Events){
                                eA[Events.indexOf(e)] = e;
                            }
                            tmp.setEvents(eA);
                            Events = new ArrayList<Crush.Event>();
                            break;
                    }
                    lastTag = "";
                }else if (eventType == XmlPullParser.TEXT){
                    switch (lastTag){
                        case "FirstName":
                            tmp.setFirstName(xpp.getText());break;
                        case "LastName":
                            tmp.setLastName(xpp.getText());break;
                        case "IATA":
                            IATAs.add(xpp.getText());
                            tmp.setIATA(xpp.getText());break;
                        case "IACO":
                            tmp.setIACO(xpp.getText());break;
                        case "Airline":
                            tmp.setAirline(xpp.getText());break;
                        case "Callsign":
                            tmp.setCallsign(xpp.getText());break;
                        case "Gate":
                            tmp.setGate(xpp.getText());
                            String g = xpp.getText();
                            String fn = tmp.getFlightNumber();

                            if(g.equals("Pattern")){
                                PAT[ip] = fn;
                                ip++;
                            }else if(g.equals("GONE")){
                                GONE[ig] = fn;
                                ig++;
                            }else if(g.charAt(0) == 'G'){
                                if(g.charAt(1) == '0'){
                                    G01 = fn;
                                }else if(g.charAt(1) == '1'){
                                    AP1[Integer.valueOf(g.charAt(2) + "")-1] = fn;
                                }else if(g.charAt(1) == '2'){
                                    AP2[Integer.valueOf(g.charAt(2) + "")-1] = fn;
                                }else if(g.charAt(1) == '3'){
                                    AP3[Integer.valueOf(g.charAt(2) + "")-1] = fn;
                                }
                            }



                            break;
                        case "Aircraft":
                            tmp.setAircraft(xpp.getText());break;
                        case "Date":
                            tmpE.setDate(xpp.getText());break;
                        case "Type":
                            tmpE.setType(Integer.valueOf(xpp.getText()));break;
                        case "Info":
                            tmpE.setInfo(xpp.getText());break;
                    }
                }

                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        GroundMap = new Ground(AP1, AP2, AP3, PAT, GONE, G01);
    }

    public static void doTable(Context context, View view){
        TableLayout ll = (TableLayout) view.findViewById(R.id.TableLayout);
        ll.removeAllViews();

        for(Crush c:allActiveFlights){
            doRow(context, ll, c);
        }
    }

    public static void doRow(Context context, TableLayout ll, Crush c){

        TableRow row=(TableRow) View.inflate(context, R.layout.table_row, null);

        TextView Callsign = row.findViewById(R.id.Callsign_view);
        TextView FlightNumber = row.findViewById(R.id.Flight_view);
        TextView Gate = row.findViewById(R.id.Gate_view);
        TextView AC = row.findViewById(R.id.AC_view);

        Callsign.setText(c.getCallsign());
        FlightNumber.setText(c.getFlightNumber());
        Gate.setText(c.getGate());
        AC.setText(c.getAircraft());

        ll.addView(row);
    }

    public static void saveXML(Context context, Crush[] Crushs) {

        allActiveFlights = Crushs;

        XmlSerializer serializer = Xml.newSerializer();
        try {

            File f = new File(context.getExternalFilesDir("").getAbsolutePath(), "Flight.xml");

            if(!f.exists()) {
                f.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(f);

            serializer.setOutput(fos, "UTF-8");
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output",true);
            serializer.startDocument("UTF-8", true);

            serializer.startTag("", "Flights");
            serializer.attribute("", "Size", Crushs.length + "");

            int i = 0;

            for(Crush aF:Crushs) {

                serializer.startTag("", "Flight");
                serializer.attribute("", "FlightNumber", aF.getFlightNumber());
                serializer.attribute("", "Index", i + "");

                serializer.startTag("", "FirstName");
                serializer.text(aF.getFirstName());
                serializer.endTag("", "FirstName");

                serializer.startTag("", "LastName");
                serializer.text(aF.getLastName());
                serializer.endTag("", "LastName");

                serializer.startTag("", "IATA");
                serializer.text(aF.getIATA());
                serializer.endTag("", "IATA");

                serializer.startTag("", "IACO");
                serializer.text(aF.getIACO());
                serializer.endTag("", "IACO");

                serializer.startTag("", "Airline");
                serializer.text(aF.getAirline());
                serializer.endTag("", "Airline");

                serializer.startTag("", "Callsign");
                serializer.text(aF.getCallsign());
                serializer.endTag("", "Callsign");

                serializer.startTag("", "Gate");
                serializer.text(aF.getGate());
                serializer.endTag("", "Gate");

                serializer.startTag("", "Aircraft");
                serializer.text(aF.getAircraft());
                serializer.endTag("", "Aircraft");

                serializer.startTag("", "Events");

                for(Crush.Event e:aF.getEvents()){
                    serializer.startTag("", "Event");

                    serializer.startTag("", "Date");
                    serializer.text(e.getDate());
                    serializer.endTag("", "Date");

                    serializer.startTag("", "Type");
                    serializer.text(e.getType() + "");
                    serializer.endTag("", "Type");

                    serializer.startTag("", "Info");
                    serializer.text(e.getInfo());
                    serializer.endTag("", "Info");

                    serializer.endTag("", "Event");
                }
                serializer.text("");
                serializer.endTag("", "Events");

                serializer.endTag("", "Flight");
                i++;

            }
            serializer.endTag("", "Flights");

            serializer.endDocument();
            serializer.flush();
            fos.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Crush[] getAllActiveFlights() {
        return allActiveFlights;
    }

    public static Crush CrushByFlightNumber(String fn){

        for(Crush c:allActiveFlights){
            if(c.getFlightNumber().equals(fn)){
                return c;
            }
        }

        return null;
    }

    public static Crush CrushByFlightIndex(int i){

        for(Crush c:allActiveFlights){
            if(c.getIndex() == i){
                return c;
            }
        }

        return null;
    }

    public static Ground getGroundMap() {
        return GroundMap;
    }

    public static List<List<String>> getHistory() { return History; }

    public static List<String> getMonths() { return Months; }

    public static void readHistoryXML(Context context){

        History = new ArrayList<>();
        Months  = new ArrayList<>();

        File f = new File(context.getExternalFilesDir("").getAbsolutePath(), "History.xml");

        FileInputStream fin = null;

        XmlPullParserFactory factory;

        try {

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            fin = new FileInputStream(f);

            xpp.setInput(fin, null);

            List<String> l = new ArrayList<>();
            String lastTag = "";

            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                }else if (eventType == XmlPullParser.START_TAG){
                    switch (xpp.getName()){
                        case "Flight":
                            l = new ArrayList<>();
                            break;
                        default:
                            lastTag = xpp.getName();
                    }

                }else if (eventType == XmlPullParser.END_TAG){
                    switch (xpp.getName()){
                        case "Flight":
                            History.add(l);
                            l = new ArrayList<>();
                            break;
                    }
                    lastTag = "";
                }else if (eventType == XmlPullParser.TEXT){
                    switch (lastTag){
                        case "Item":
                            l.add(xpp.getText());
                            break;
                        case "M":
                            Months.add(xpp.getText());
                            break;
                    }
                }

                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveHistoryXML(Context context, List<List<String>> History_new, List<String> Months_new) {

        History = History_new;
        Months = Months_new;

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output",true);
            serializer.startDocument("UTF-8", true);

            serializer.startTag("", "History");

            for (List<String> l : History){

                serializer.startTag("", "Flight");

                for (String s:l){
                    serializer.startTag("", "Item");
                    serializer.text(s);
                    serializer.endTag("", "Item");
                }

                serializer.endTag("", "Flight");
            }

            serializer.startTag("", "Months");
            for (String m:Months){
                serializer.startTag("", "M");
                serializer.text(m);
                serializer.endTag("","M");
            }
            serializer.endTag("","Months");
            serializer.endTag("", "History");

            serializer.endDocument();
            String result = writer.toString();

            try {
                File f = new File(context.getExternalFilesDir("").getAbsolutePath(), "History.xml");

                if(!f.exists()) {
                    f.createNewFile();
                }

                FileWriter fw = new FileWriter(f, false);
                fw.write(result);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> getIATAs() {
        return IATAs;
    }
}
