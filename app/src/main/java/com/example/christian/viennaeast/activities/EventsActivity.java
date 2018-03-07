package com.example.christian.viennaeast.activities;

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

import com.example.christian.viennaeast.ADT.Crush;
import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.io.XML;

public class EventsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    private static Crush active;
    private Crush.Event[] Events;
    private static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent i = getIntent();
        active = (Crush) i.getSerializableExtra("Crush");
        index = active.getIndex();
        Events = active.getEvents();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab_delete = (FloatingActionButton) findViewById(R.id.fab_delete);
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditEventActivity.class);
                i.putExtra("RequestType", "delete");
                i.putExtra("Crush", active);
                i.putExtra("Event", mViewPager.getCurrentItem());
                startActivityForResult(i, 3);
                finish();
            }
        });
        FloatingActionButton fab_new = (FloatingActionButton) findViewById(R.id.fab_new);
        fab_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditEventActivity.class);
                i.putExtra("RequestType", "new");
                i.putExtra("Crush", active);
                startActivityForResult(i, 1);
                finish();
            }
        });
        FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditEventActivity.class);
                i.putExtra("RequestType", "edit");
                i.putExtra("Crush", active);
                i.putExtra("Event", mViewPager.getCurrentItem());
                startActivityForResult(i, 2);
                finish();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label2);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView Type = (TextView) rootView.findViewById(R.id.textView23);
            TextView Date = (TextView) rootView.findViewById(R.id.textView25);
            TextView Info = (TextView) rootView.findViewById(R.id.textView27);



            active = XML.getAllActiveFlights()[index];
            Crush.Event activeEvent = active.getEvents()[section_number];

            Type.setText(activeEvent.getTypeAsText());
            Date.setText(activeEvent.getDateAsText());
            Info.setText(activeEvent.getInfo());

            return rootView;
        }

//        @Override
//        public void onResume() {
//            super.onResume();
//            View rootView = this.getView();
//            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
//
//            TextView Type = (TextView) rootView.findViewById(R.id.textView23);
//            TextView Date = (TextView) rootView.findViewById(R.id.textView25);
//            TextView Info = (TextView) rootView.findViewById(R.id.textView27);
//
//            active = XML.getAllActiveFlights()[index];
//            Crush.Event activeEvent = active.getEvents()[section_number];
//
//
//            Type.setText(activeEvent.getTypeAsText());
//            Date.setText(activeEvent.getDateAsText());
//            Info.setText(activeEvent.getInfo());
//
//        }
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
            return active.getEvents().length;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
