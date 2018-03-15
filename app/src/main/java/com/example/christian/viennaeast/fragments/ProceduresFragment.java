package com.example.christian.viennaeast.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.activities.ProceduresActivity;
import com.example.christian.viennaeast.io.XML;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProceduresFragment extends Fragment {

    public ProceduresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_procedures, container, false);

        XML.readProceduresXML(getContext());
        root.findViewById(R.id.button_internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(0);
            }
        });
        root.findViewById(R.id.button_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(1);
            }
        });
        root.findViewById(R.id.button_workflow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(2);
            }
        });
        root.findViewById(R.id.button_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(3);
            }
        });

        return root;
    }

    public void start(int tab){
        Intent i = new Intent(getContext(), ProceduresActivity.class);
        i.putExtra("tab", tab);
        startActivity(i);
    }
}
