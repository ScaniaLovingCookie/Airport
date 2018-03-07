package com.example.christian.viennaeast.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.christian.viennaeast.R;
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

        return root;
    }
}
