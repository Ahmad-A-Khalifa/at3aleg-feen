package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.adapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.id.start_search;

public class AdvancedSearchActivity extends AppCompatActivity {

    @BindView(R.id.medicalPlaces_expandedList)
    ExpandableListAdapter mListAdapter;
    @BindView(start_search)
    Button mStartSearchButton;
    @BindView(R.id.search_reasult_Layout)
    LinearLayout mSearchResultLayout;
    private ExpandableListView mExpandableListView;
    private List<String> mListDataHeader;
    private HashMap<String, List<String>> mListDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

        }
        setContentView(R.layout.activity_advanced_search);

        ButterKnife.bind(this);

        // preparing list data
        prepareListData();
        mListAdapter = new ExpandableListAdapter(this, mListDataHeader, mListDataChild);
        // setting list adapter
        mExpandableListView.setAdapter(mListAdapter);

        /*mStartSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchResultLayout.setVisibility(View.VISIBLE);
            }
        });*/
    }

    @OnClick({R.id.start_search})
    public void onClick(View v) {

        switch (v.getId()) {
            case start_search:
                mSearchResultLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void prepareListData() {
        mListDataHeader = new ArrayList<String>();
        mListDataChild = new HashMap<String, List<String>>();

        // Adding child data
        mListDataHeader.add("Clinic");
        mListDataHeader.add("Hospital");
        mListDataHeader.add("Laboratory");
        mListDataHeader.add("Pharmacy");

        // Adding child data
        List<String> clinics = new ArrayList<String>();
        clinics.add("Allergy and Immunology");
        clinics.add("Elders");
        clinics.add("Rheumatology");

        List<String> hospitals = new ArrayList<String>();
        hospitals.add("General");
        hospitals.add("Specialized");

        List<String> Laboratory = new ArrayList<String>();
        Laboratory.add("Biology");
        Laboratory.add("Physiology");

        List<String> pharmaces = new ArrayList<String>();
        pharmaces.add("General");

        mListDataChild.put(mListDataHeader.get(0), clinics); // Header, Child data
        mListDataChild.put(mListDataHeader.get(1), hospitals);
        mListDataChild.put(mListDataHeader.get(2), Laboratory);
        mListDataChild.put(mListDataHeader.get(3), pharmaces);
    }
}
