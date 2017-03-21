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

public class AdvancedSearch extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Button startSearch;
    LinearLayout searchResultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.medicalPlaces_expandedList);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        startSearch = (Button) findViewById(R.id.start_search);
        searchResultLayout = (LinearLayout) findViewById(R.id.search_reasult_Layout);
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResultLayout.setVisibility(View.VISIBLE);
            }
        });


    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Clinic");
        listDataHeader.add("Hospital");
        listDataHeader.add("Laboratory");
        listDataHeader.add("Pharmacy");

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

        listDataChild.put(listDataHeader.get(0), clinics); // Header, Child data
        listDataChild.put(listDataHeader.get(1), hospitals);
        listDataChild.put(listDataHeader.get(2), Laboratory);
        listDataChild.put(listDataHeader.get(3), pharmaces);

    }
}
