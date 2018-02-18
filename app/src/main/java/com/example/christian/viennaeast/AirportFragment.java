package com.example.christian.viennaeast;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AirportFragment extends Fragment {

    public AirportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_airport, container, false);

        Button b1 = (Button) root.findViewById(R.id.button_G01);
        Button b2 = (Button) root.findViewById(R.id.button_AP1);
        Button b3 = (Button) root.findViewById(R.id.button_AP2);
        Button b4 = (Button) root.findViewById(R.id.button_AP3);
//        Button b5 = (Button) root.findViewById(R.id.button_GONE);
//        Button b6 = (Button) root.findViewById(R.id.button_PAT);

        if(!XML.getGroundMap().getG01().equals("")){
            b1.setBackground(getResources().getDrawable(R.drawable.button_background42, null));
        }
        switch (XML.getGroundMap().getAP1Length()){
            case 0: b2.setBackground(getResources().getDrawable(R.drawable.button_background0, null)); break;
            case 1: b2.setBackground(getResources().getDrawable(R.drawable.button_background1, null)); break;
            case 2: b2.setBackground(getResources().getDrawable(R.drawable.button_background2, null)); break;
            case 3: b2.setBackground(getResources().getDrawable(R.drawable.button_background3, null)); break;
            case 4: b2.setBackground(getResources().getDrawable(R.drawable.button_background4, null)); break;
            case 5: b2.setBackground(getResources().getDrawable(R.drawable.button_background5, null)); break;
            case 6: b2.setBackground(getResources().getDrawable(R.drawable.button_background6, null)); break;
            case 7: b2.setBackground(getResources().getDrawable(R.drawable.button_background7, null)); break;
            case 8: b2.setBackground(getResources().getDrawable(R.drawable.button_background8, null)); break;
        }
        switch (XML.getGroundMap().getAP2Length()){
            case 0: b3.setBackground(getResources().getDrawable(R.drawable.button_background0, null)); break;
            case 1: b3.setBackground(getResources().getDrawable(R.drawable.button_background1, null)); break;
            case 2: b3.setBackground(getResources().getDrawable(R.drawable.button_background2, null)); break;
            case 3: b3.setBackground(getResources().getDrawable(R.drawable.button_background3, null)); break;
            case 4: b3.setBackground(getResources().getDrawable(R.drawable.button_background4, null)); break;
            case 5: b3.setBackground(getResources().getDrawable(R.drawable.button_background5, null)); break;
            case 6: b3.setBackground(getResources().getDrawable(R.drawable.button_background6, null)); break;
            case 7: b3.setBackground(getResources().getDrawable(R.drawable.button_background7, null)); break;
            case 8: b3.setBackground(getResources().getDrawable(R.drawable.button_background8, null)); break;
        }
        switch (XML.getGroundMap().getAP3Length()){
            case 0: b4.setBackground(getResources().getDrawable(R.drawable.button_background0, null)); break;
            case 1: b4.setBackground(getResources().getDrawable(R.drawable.button_background1, null)); break;
            case 2: b4.setBackground(getResources().getDrawable(R.drawable.button_background2, null)); break;
            case 3: b4.setBackground(getResources().getDrawable(R.drawable.button_background3, null)); break;
            case 4: b4.setBackground(getResources().getDrawable(R.drawable.button_background4, null)); break;
            case 5: b4.setBackground(getResources().getDrawable(R.drawable.button_background5, null)); break;
            case 6: b4.setBackground(getResources().getDrawable(R.drawable.button_background6, null)); break;
            case 7: b4.setBackground(getResources().getDrawable(R.drawable.button_background7, null)); break;
            case 8: b4.setBackground(getResources().getDrawable(R.drawable.button_background8, null)); break;
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
