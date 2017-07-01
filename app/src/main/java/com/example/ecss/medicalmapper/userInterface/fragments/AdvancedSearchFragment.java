package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ecss.medicalmapper.ClassAdapter;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchBranch;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchPlaces;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchRequest;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchResponse;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.activities.general.DetailsActivity;
import com.example.ecss.medicalmapper.userInterface.adapters.AdvancedSearchAdapter;
import com.example.ecss.medicalmapper.utility.Utility;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;

public class AdvancedSearchFragment extends Fragment {




    @BindView(R.id.advanced_search_results_list)
    RecyclerView mSearchResultsRecyclerView;

    private AdvancedSearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.advanced_search_progress_bar)
    ProgressBar mProgressBar;

    private RadioGroup  placeType;
    private RadioGroup  placeOrder;
    private LinearLayout sortLinearLayout;
    private LinearLayout spatializationLinearLayout;
    private  String[] items ;
    private Spinner spatializationSpinner;

    private String selectedPlace ;
    private String selectedSpatialization ="";
    private int  selectedOrder =0;
    private  List<AdvancedSearchBranch> mBranchesEmpty = new ArrayList<AdvancedSearchBranch>();
    private int first=0;


    // private List<String> mListDataHeader;
    //private HashMap<String, List<String>> mListDataChild;

    public AdvancedSearchFragment() {
    }

    public static AdvancedSearchFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        AdvancedSearchFragment fragment = new AdvancedSearchFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_advanced_search, container, false);

        ButterKnife.bind(this, rootView);

        placeType = (RadioGroup) rootView.findViewById(R.id.placeTypeRadioGroup);
        placeOrder = (RadioGroup) rootView.findViewById(R.id.searchOrderRadioGroup);
        sortLinearLayout = (LinearLayout) rootView.findViewById(R.id.sortLinearLayout);
        spatializationSpinner= (Spinner) rootView.findViewById(R.id.spitializationSpinner);
        spatializationLinearLayout = (LinearLayout)rootView.findViewById(R.id.spatializationLinearLayout);

        placeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.clinicRadio) {
                    //Toast.makeText(getContext(), "clinc", Toast.LENGTH_SHORT).show();
                    spatializationLinearLayout.setVisibility(View.VISIBLE);
                    sortLinearLayout.setVisibility(View.INVISIBLE);
                    selectedPlace="clinic";
                    items = getResources().getStringArray(R.array.clinic_typs);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
                    spatializationSpinner.setAdapter(adapter);
                } else if (checkedId ==R.id.hospitakRadio1) {
                    //Toast.makeText(getContext(), "hospitakRadio1", Toast.LENGTH_SHORT).show();
                    spatializationLinearLayout.setVisibility(View.INVISIBLE);
                    sortLinearLayout.setVisibility(View.VISIBLE);
                    selectedPlace="hospital";


                }
                else if (checkedId == R.id.labRadio) {
                    // Toast.makeText(getContext(), "labRadio", Toast.LENGTH_SHORT).show();
                    spatializationLinearLayout.setVisibility(View.VISIBLE);
                    sortLinearLayout.setVisibility(View.INVISIBLE);
                    selectedPlace="lab";
                    items = getResources().getStringArray(R.array.lab_typs);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
                    spatializationSpinner.setAdapter(adapter);
                }
                else if (checkedId == R.id.pharmacyRadio) {
                    //Toast.makeText(getContext(), "pharmacyRadio", Toast.LENGTH_SHORT).show();
                    spatializationLinearLayout.setVisibility(View.INVISIBLE);
                    sortLinearLayout.setVisibility(View.VISIBLE);
                    selectedPlace="pharmacy";

                }

            }

        });

        spatializationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedSpatialization = items[position] ;
                sortLinearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedSpatialization = items[0] ;
                sortLinearLayout.setVisibility(View.VISIBLE);
            }

        });


        placeOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.nearstRadio) {
                    selectedOrder =0 ;
                    if (selectedPlace.equals("pharmacy") ||selectedPlace.equals("hospital") )
                    {
                        selectedSpatialization ="";
                    }
                    first=1;
                    getData (selectedPlace , selectedSpatialization , selectedOrder);
                 //   Toast.makeText(getContext(), selectedPlace +"  " + selectedSpatialization+"  near" , Toast.LENGTH_SHORT).show();

                } else if (checkedId ==R.id.topRadio) {
                    selectedOrder= 1;
                    if (selectedPlace.equals("pharmacy") ||selectedPlace.equals("hospital") )
                    {
                        selectedSpatialization ="";
                    }
                    getData (selectedPlace , selectedSpatialization , selectedOrder);


                }


            }

        });

        return rootView;
    }

    public void getData (String placeType , String placeSpitilaization , int order  )
    {

        final Context context = getActivity().getApplicationContext();
        if (checkInternetConnection()) {
            mProgressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<AdvancedSearchResponse> call = apiService.AdvancedSearch(new AdvancedSearchRequest(placeType, placeSpitilaization, order, "30.0445343", "31.2047149"));
            call.enqueue(new Callback<AdvancedSearchResponse>() {
                @Override
                public void onResponse(Call<AdvancedSearchResponse> call, Response<AdvancedSearchResponse> response) {

                    AdvancedSearchPlaces places = null;
                    ApiCallStatus status = null;

                    if (response.body() != null) {
                        places = response.body().getPlaces();
                        status = response.body().getApiCallStatus();
                    }

                    final AdvancedSearchPlaces copyPlaces = places;

                    if (status != null && places != null && status.getIsSuccessful()) {
                        //Display Places
                        if (places.getBranches() != null) {

                            mAdapter = new AdvancedSearchAdapter();
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mSearchResultsRecyclerView.setLayoutManager(mLayoutManager);
                            mSearchResultsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mSearchResultsRecyclerView.setAdapter(mAdapter);
                            for (int i=0 ;i<places.getBranches().size()-1 ;i++ )
                            {
                                if ( places.getBranches().get(i).getPlaceNameEN().equals(places.getBranches().get(i+1).getPlaceNameEN()))
                                {
                                    places.getBranches().remove(i+1);
                                }
                            }
                            mAdapter.updateAdapter(places.getBranches());

                            mAdapter.setListner(new AdvancedSearchAdapter.Listner() {
                                @Override
                                public void onClick(int pos) {
                                    MedicalPlace copyPlace = ClassAdapter.convertAdvancedSearchBranchToMedicalPlace(copyPlaces.getBranches().get(pos), Utility.getLanguageFromSettings(getActivity()));
                                    DetailsActivity.startActivity(context, copyPlace);
                                }
                            });
                        }

                    } else {
                        if (status != null && status.getErrorStatus() != null)

                            if(first==1)
                                mAdapter.updateAdapter(mBranchesEmpty);
                        Toast.makeText(getActivity(), getString(R.string.no_search), Toast.LENGTH_LONG).show();
                    }
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<AdvancedSearchResponse> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    if(first==1)
                         mAdapter.updateAdapter(mBranchesEmpty);
                    Toast.makeText(getActivity(), getString(R.string.no_search), Toast.LENGTH_LONG).show();
                }
            });
        }
    }



}
