package com.example.christian.viennaeast.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.christian.viennaeast.ADT.Crush;
import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.io.XML;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */

public class HistoryFragment extends Fragment {

    private List<String> Text = Arrays.asList("G01", "AP1", "AP2", "AP3", "PAT", "HANG", "/", "-", "?");
    private List<Integer> BgColors = Arrays.asList(Color.parseColor("#00AA00"), Color.parseColor("#DDDD00"), Color.parseColor("#FFAA00"), Color.parseColor("#FF6400"), Color.parseColor("#AA0000"), Color.parseColor("#AA00FF"), Color.parseColor("#8000CC"), Color.parseColor("#000088"), Color.parseColor("#0000AA"));
    private List<Integer> TxtColors = Arrays.asList(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);


    ScrollView scrollViewOne, scrollViewTwo;

    AlertDialog.Builder builder_add;
    AlertDialog.Builder builder_click;
    AlertDialog.Builder builder_months;

    LinearLayout Flights, Months;
    TableLayout Table;

    LinearLayout.LayoutParams LLparams;
    LinearLayout.LayoutParams LLparams2;
    TableRow.LayoutParams tabLparams;

    View.OnClickListener c;
    View.OnClickListener cm;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        scrollViewOne =(ScrollView)root.findViewById(R.id.svOne);
        scrollViewTwo =(ScrollView)root.findViewById(R.id.svTwo);


        scrollViewOne.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollViewTwo.scrollTo(scrollX, scrollY);
            }
        });

        scrollViewTwo.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollViewOne.scrollTo(scrollX,scrollY);
            }
        });

        Flights = (LinearLayout) root.findViewById(R.id.Flight_Header);
        Months = (LinearLayout) root.findViewById(R.id.Month_Header);
        Table = (TableLayout) root.findViewById(R.id.Table_History);

        int Height_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int Width_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int Width2_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
        int Margin_px = 2;

        LLparams = new LinearLayout.LayoutParams(Width_px,Height_px);
        LLparams.setMargins(Margin_px, Margin_px, Margin_px, Margin_px);

        LLparams2 = new LinearLayout.LayoutParams(Width2_px,Height_px);
        LLparams2.setMargins(Margin_px, Margin_px, Margin_px, Margin_px);

        tabLparams = new TableRow.LayoutParams(Width_px,Height_px);
        tabLparams.setMargins(Margin_px, Margin_px, Margin_px, Margin_px);

        TextView b_add = (TextView) root.findViewById(R.id.textView_add);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] Text_add = new String[1];

                builder_add = new AlertDialog.Builder(getContext());
                builder_add.setTitle("New Month");

                final EditText input_add = new EditText(getContext());
                input_add.setText(new SimpleDateFormat("MMM YY", Locale.UK).format(new Date()));
                input_add.setInputType(InputType.TYPE_CLASS_TEXT);
                builder_add.setView(input_add);

                builder_add.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Text_add[0] = input_add.getText().toString();
                        add(Text_add[0]);
                    }
                });
                builder_add.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder_add.show();
            }
        });

        c = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final ViewGroup vg1 = (ViewGroup) view.getParent();
                final ViewGroup vg2 = (ViewGroup) view.getParent().getParent();

                final String[] Text_click = new String[1];

                builder_click = new AlertDialog.Builder(getContext());
                builder_click.setTitle("Input");

                final Spinner input_click = new Spinner(getContext());
                input_click.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Arrays.asList("-", "G01", "AP1", "AP2", "AP3", "PAT", "HANG", "/", "?")));
                input_click.setSelection(Arrays.asList("-", "G01", "AP1", "AP2", "AP3", "PAT", "HANG", "/", "?").indexOf(((TextView)view).getText().toString()));
                builder_click.setView(input_click);


                builder_click.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Text_click[0] = input_click.getSelectedItem().toString();
//                        Log.e("text", Text_click[0]);
                        change(vg2.indexOfChild(vg1), vg1.indexOfChild(view), Text_click[0]);
                    }
                });
                builder_click.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder_click.show();

            }
        };

        cm = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final ViewGroup vg1 = (ViewGroup) view.getParent();

                builder_months = new AlertDialog.Builder(getContext());
                builder_months.setTitle("Delete?");



                builder_months.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Log.e("text", Text_click[0]);
                        delete(vg1.indexOfChild(view));
                    }
                });
                builder_months.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder_months.show();
            }
        };

        draw();

        return root;
    }

    private void delete(int row){
        XML.readHistoryXML(getContext());

        List<List<String>> ll = XML.getHistory();
        List<String> m = XML.getMonths();

        m.remove(row);
        for (List<String> l:ll){
            l.remove(row);
        }

        XML.saveHistoryXML(getContext(), ll, m);
        draw();
    }

    private void change(int row, int item, String new_s){
        XML.readHistoryXML(getContext());

        List<List<String>> ll = XML.getHistory();
        List<String> m = XML.getMonths();

        List<String> l = ll.get(row);
        l.set(item, new_s);
        ll.set(row,l);

        XML.saveHistoryXML(getContext(), ll, m);
        draw();
    }

    private void add(String Month){
        XML.readHistoryXML(getContext());

        List<List<String>> ll = XML.getHistory();
        List<String> m = XML.getMonths();

        for(int i = 0; i<ll.size(); i++) {
            String s;
            if (getContext().getSharedPreferences("PASS_SET", 0).getBoolean("Closed", false)) {
                if (XML.getAllActiveFlights()[i].getGate().equals("HANGAR")) {
                    s = "HANG";
                } else {
                    s = "/";
                }
                List l = ll.get(i);
                l.add(s);
                ll.set(i, l);
            }else{
                switch (XML.getAllActiveFlights()[i].getGate()) {
                    case "G01":
                        s = "G01";
                        break;
                    case "G11":
                        s = "AP1";
                        break;
                    case "G12":
                        s = "AP1";
                        break;
                    case "G13":
                        s = "AP1";
                        break;
                    case "G14":
                        s = "AP1";
                        break;
                    case "G15":
                        s = "AP1";
                        break;
                    case "G16":
                        s = "AP1";
                        break;
                    case "G17":
                        s = "AP1";
                        break;
                    case "G18":
                        s = "AP1";
                        break;
                    case "G21":
                        s = "AP2";
                        break;
                    case "G22":
                        s = "AP2";
                        break;
                    case "G23":
                        s = "AP2";
                        break;
                    case "G24":
                        s = "AP2";
                        break;
                    case "G25":
                        s = "AP2";
                        break;
                    case "G26":
                        s = "AP2";
                        break;
                    case "G27":
                        s = "AP2";
                        break;
                    case "G28":
                        s = "AP2";
                        break;
                    case "G31":
                        s = "AP3";
                        break;
                    case "G32":
                        s = "AP3";
                        break;
                    case "G33":
                        s = "AP3";
                        break;
                    case "G34":
                        s = "AP3";
                        break;
                    case "G35":
                        s = "AP3";
                        break;
                    case "G36":
                        s = "AP3";
                        break;
                    case "G37":
                        s = "AP3";
                        break;
                    case "G38":
                        s = "AP3";
                        break;
                    case "Pattern":
                        s = "PAT";
                        break;
                    case "GONE":
                        s = "-";
                        break;
                    case "-":
                        s = "-";
                        break;
                    default:
                        s = "?";

                }
                List l = ll.get(i);
                l.add(s);
                ll.set(i, l);
            }
        }
        m.add(Month);

        XML.saveHistoryXML(getContext(), ll, m);
        draw();
    }

    private void draw(){
        Flights.removeAllViews();
        Months.removeAllViews();
        Table.removeAllViews();

        XML.readHistoryXML(getContext());

        List<List<String>> ll = XML.getHistory();
        List<String> m = XML.getMonths();

        for (List<String> l:ll){
            TableRow r = new TableRow(getContext());
            r.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            for (String s : l){
                TextView TableText = new TextView(getContext());
                TableText.setTextColor(TxtColors.get(Text.indexOf(s)));
                TableText.setLayoutParams(tabLparams);
                TableText.setBackgroundColor(BgColors.get(Text.indexOf(s)));
                TableText.setGravity(Gravity.CENTER);
                TableText.setText(s);
                TableText.setOnClickListener(c);
                r.addView(TableText);
            }
            Table.addView(r);
        }
        for(Crush cr:XML.getAllActiveFlights()){
            TextView HeaderTwoText = new TextView(getContext());
            HeaderTwoText.setTextColor(Color.WHITE);
            HeaderTwoText.setLayoutParams(LLparams2);
            HeaderTwoText.setBackgroundColor(Color.parseColor("#000050"));
            HeaderTwoText.setGravity(Gravity.CENTER);
            HeaderTwoText.setText(cr.getFlightNumber());
            Flights.addView(HeaderTwoText);
        }
        for (String ms:m){
            TextView HeaderOneText = new TextView(getContext());
            HeaderOneText.setTextColor(Color.WHITE);
            HeaderOneText.setLayoutParams(LLparams);
            HeaderOneText.setBackgroundColor(Color.parseColor("#000050"));
            HeaderOneText.setGravity(Gravity.CENTER);
            HeaderOneText.setText(ms);
            HeaderOneText.setOnClickListener(cm);
            Months.addView(HeaderOneText);

        }
    }

}
