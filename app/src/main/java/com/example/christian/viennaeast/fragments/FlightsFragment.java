package com.example.christian.viennaeast.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.io.XML;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlightsFragment extends Fragment {


    public FlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flights, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        XML.readXML(getContext());
        XML.doTable(getContext(), getView());
    }
}
