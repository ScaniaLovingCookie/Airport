package com.example.christian.viennaeast;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class CrushActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crush);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_delete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewActivity.class);
                Crush tmp = XML.CrushByFlightIndex(mViewPager.getCurrentItem());
                i.putExtra("RequestType", "delete");
                i.putExtra("Crush", tmp);
                startActivityForResult(i, 3);
                finish();
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewActivity.class);
                Crush tmp = XML.CrushByFlightIndex(mViewPager.getCurrentItem());
                i.putExtra("RequestType", "edit");
                i.putExtra("Crush", tmp);
                startActivityForResult(i, 2);
            }
        });
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab_events);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EventsActivity.class);
                Crush tmp = XML.CrushByFlightIndex(mViewPager.getCurrentItem());
                i.putExtra("Crush", tmp);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        Crush c1 = (Crush) i.getSerializableExtra("Crush");

        if(c1 != null) {
            int index = c1.getIndex();
            ViewPager pager = findViewById(R.id.container);
            pager.setCurrentItem(index);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crush, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_crush, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView FlightNr = (TextView) rootView.findViewById(R.id.textView11);
            TextView Pilot = (TextView) rootView.findViewById(R.id.textView13);
            TextView Airline = (TextView) rootView.findViewById(R.id.textView15);
            TextView Airline2 = (TextView) rootView.findViewById(R.id.textView17);
            TextView Gate = (TextView) rootView.findViewById(R.id.textView19);
            TextView Aircraft = (TextView) rootView.findViewById(R.id.textView21);

            Crush active = XML.CrushByFlightIndex(section_number);

            FlightNr.setText(active.getFlightNumber());
            Pilot.setText(active.getFirstName() + " " + active.getLastName());
            Airline.setText(active.getAirline());
            Airline2.setText(active.getCallsign() + " (" + active.getIACO() + "/" + active.getIATA() + ")");
            Gate.setText(active.getGate());
            Aircraft.setText(active.getAircraft());

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            View rootView = this.getView();
            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView FlightNr = (TextView) rootView.findViewById(R.id.textView11);
            TextView Pilot = (TextView) rootView.findViewById(R.id.textView13);
            TextView Airline = (TextView) rootView.findViewById(R.id.textView15);
            TextView Airline2 = (TextView) rootView.findViewById(R.id.textView17);
            TextView Gate = (TextView) rootView.findViewById(R.id.textView19);
            TextView Aircraft = (TextView) rootView.findViewById(R.id.textView21);

            Crush active = XML.CrushByFlightIndex(section_number);

            FlightNr.setText(active.getFlightNumber());
            Pilot.setText(active.getFirstName() + " " + active.getLastName());
            Airline.setText(active.getAirline());
            Airline2.setText(active.getCallsign() + " (" + active.getIACO() + "/" + active.getIATA() + ")");
            Gate.setText(active.getGate());
            Aircraft.setText(active.getAircraft());
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return XML.getAllActiveFlights().length;
        }
    }
}
