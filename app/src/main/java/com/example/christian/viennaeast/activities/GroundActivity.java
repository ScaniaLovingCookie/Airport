package com.example.christian.viennaeast.activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

import com.example.christian.viennaeast.R;
import com.example.christian.viennaeast.io.XML;

public class GroundActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_ground);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        int i = getIntent().getIntExtra("tab", 0);
        mViewPager.setCurrentItem(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ground, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_ground, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(getResources().getColor(R.color.colorAccent));
            textView.setTextSize(20);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.GroundLayout);

            TextView label1 = (TextView) rootView.findViewById(R.id.textView31);
            TextView text1 = (TextView) rootView.findViewById(R.id.textView32);
            TextView label2 = (TextView) rootView.findViewById(R.id.textView33);
            TextView text2 = (TextView) rootView.findViewById(R.id.textView34);
            TextView label3 = (TextView) rootView.findViewById(R.id.textView35);
            TextView text3 = (TextView) rootView.findViewById(R.id.textView36);
            TextView label4 = (TextView) rootView.findViewById(R.id.textView37);
            TextView text4 = (TextView) rootView.findViewById(R.id.textView38);
            TextView label5 = (TextView) rootView.findViewById(R.id.textView39);
            TextView text5 = (TextView) rootView.findViewById(R.id.textView40);
            TextView label6 = (TextView) rootView.findViewById(R.id.textView41);
            TextView text6 = (TextView) rootView.findViewById(R.id.textView42);
            TextView label7 = (TextView) rootView.findViewById(R.id.textView43);
            TextView text7 = (TextView) rootView.findViewById(R.id.textView44);
            TextView label8 = (TextView) rootView.findViewById(R.id.textView45);
            TextView text8 = (TextView) rootView.findViewById(R.id.textView46);

            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 0:
                    textView.setText("Gate 01:");
                    label1.setText("G01:");
                    text1.setText(XML.getGroundMap().getG01());
                    label2.setVisibility(View.INVISIBLE);
                    text2.setVisibility(View.INVISIBLE);
                    label3.setVisibility(View.INVISIBLE);
                    text3.setVisibility(View.INVISIBLE);
                    label4.setVisibility(View.INVISIBLE);
                    text4.setVisibility(View.INVISIBLE);
                    label5.setVisibility(View.INVISIBLE);
                    text5.setVisibility(View.INVISIBLE);
                    label6.setVisibility(View.INVISIBLE);
                    text6.setVisibility(View.INVISIBLE);
                    label7.setVisibility(View.INVISIBLE);
                    text7.setVisibility(View.INVISIBLE);
                    label8.setVisibility(View.INVISIBLE);
                    text8.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    textView.setText("Apron 1:");
                    label1.setText("G11:");
                    text1.setText(XML.getGroundMap().getAP1()[0]);
                    label2.setText("G12:");
                    text2.setText(XML.getGroundMap().getAP1()[1]);
                    label3.setText("G13:");
                    text3.setText(XML.getGroundMap().getAP1()[2]);
                    label4.setText("G14:");
                    text4.setText(XML.getGroundMap().getAP1()[3]);
                    label5.setText("G15:");
                    text5.setText(XML.getGroundMap().getAP1()[4]);
                    label6.setText("G16:");
                    text6.setText(XML.getGroundMap().getAP1()[5]);
                    label7.setText("G17:");
                    text7.setText(XML.getGroundMap().getAP1()[6]);
                    label8.setText("G18:");
                    text8.setText(XML.getGroundMap().getAP1()[7]);
                    break;
                case 2:
                    textView.setText("Apron 2:");
                    label1.setText("G21:");
                    text1.setText(XML.getGroundMap().getAP2()[0]);
                    label2.setText("G22:");
                    text2.setText(XML.getGroundMap().getAP2()[1]);
                    label3.setText("G23:");
                    text3.setText(XML.getGroundMap().getAP2()[2]);
                    label4.setText("G24:");
                    text4.setText(XML.getGroundMap().getAP2()[3]);
                    label5.setText("G25:");
                    text5.setText(XML.getGroundMap().getAP2()[4]);
                    label6.setText("G26:");
                    text6.setText(XML.getGroundMap().getAP2()[5]);
                    label7.setText("G27:");
                    text7.setText(XML.getGroundMap().getAP2()[6]);
                    label8.setText("G28:");
                    text8.setText(XML.getGroundMap().getAP2()[7]);
                    break;
                case 3:
                    textView.setText("Apron 3:");
                    label1.setText("G31:");
                    text1.setText(XML.getGroundMap().getAP3()[0]);
                    label2.setText("G32:");
                    text2.setText(XML.getGroundMap().getAP3()[1]);
                    label3.setText("G33:");
                    text3.setText(XML.getGroundMap().getAP3()[2]);
                    label4.setText("G34:");
                    text4.setText(XML.getGroundMap().getAP3()[3]);
                    label5.setText("G35:");
                    text5.setText(XML.getGroundMap().getAP3()[4]);
                    label6.setText("G36:");
                    text6.setText(XML.getGroundMap().getAP3()[5]);
                    label7.setText("G37:");
                    text7.setText(XML.getGroundMap().getAP3()[6]);
                    label8.setText("G38:");
                    text8.setText(XML.getGroundMap().getAP3()[7]);
                    break;
                case 4:
                    ll.removeAllViews();
                    LayoutParams lparams = new LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    lparams.setMargins(px,px,px,px);

                    TextView tv0 =new TextView(getContext());
                    tv0.setLayoutParams(lparams);
                    tv0.setText("Pattern:");
                    tv0.setTextSize(20);
                    tv0.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv0.setGravity(Gravity.CENTER);
                    ll.addView(tv0);

                    lparams.setMargins(20,20,20,20);

                    for(String s:XML.getGroundMap().getPAT()){
                        TextView tv=new TextView(getContext());
                        tv.setLayoutParams(lparams);
                        tv.setText(s);
                        tv.setTextSize(16);
                        tv.setTextColor(Color.WHITE);
                        tv.setGravity(Gravity.CENTER);
                        ll.addView(tv);
                    }
                    break;
                case 5:
                    ll.removeAllViews();

                    LayoutParams lparams2 = new LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    lparams2.setMargins(px,px,px,px);

                    TextView tv=new TextView(getContext());
                    tv.setLayoutParams(lparams2);
                    tv.setText("GONE:");
                    tv.setTextSize(20);
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv.setGravity(Gravity.CENTER);
                    ll.addView(tv);

                    lparams2.setMargins(20,20,20,20);

                    for(String s:XML.getGroundMap().getGONE()){
                        tv=new TextView(getContext());
                        tv.setLayoutParams(lparams2);
                        tv.setText(s);
                        tv.setTextSize(16);
                        tv.setTextColor(Color.WHITE);
                        tv.setGravity(Gravity.CENTER);
                        ll.addView(tv);
                    }
                    break;
            }
            return rootView;
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
            return 6;
        }
    }
}
