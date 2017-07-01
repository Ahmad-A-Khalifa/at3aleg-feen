package com.example.ecss.medicalmapper.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecss.medicalmapper.ClassAdapter;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.SearchResultsWrapper;
import com.example.ecss.medicalmapper.userInterface.activities.general.DetailsActivity;
import com.example.ecss.medicalmapper.userInterface.adapters.SearchPlacesAdapter;
import com.example.ecss.medicalmapper.utility.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplaySearchPlacesFragment extends Fragment {
    public static final String ARGUMENT_EXTRA_SEARCH_PLACE = "SearchPlace";

    @BindView(R.id.search_places_list)
    RecyclerView mPlacesList;

    SearchPlacesAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public static DisplaySearchPlacesFragment newInstance(SearchResultsWrapper wrapper) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_EXTRA_SEARCH_PLACE, wrapper);
        DisplaySearchPlacesFragment fragment = new DisplaySearchPlacesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DisplaySearchPlacesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display_search_places, container, false);

        ButterKnife.bind(this, rootView);

        if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_SEARCH_PLACE) && getArguments().getSerializable(ARGUMENT_EXTRA_SEARCH_PLACE) != null) {

            SearchResultsWrapper wrapper = (SearchResultsWrapper) getArguments().getSerializable(ARGUMENT_EXTRA_SEARCH_PLACE);

            if (wrapper != null && wrapper.getBranches() != null) {
                final SearchResultsWrapper copyPlaces = wrapper;
                mAdapter = new SearchPlacesAdapter();
                mLayoutManager = new LinearLayoutManager(getActivity());
                mPlacesList.setLayoutManager(mLayoutManager);
                mPlacesList.setItemAnimator(new DefaultItemAnimator());
                mPlacesList.setAdapter(mAdapter);
                mAdapter.updateAdapter(wrapper.getBranches());
                mAdapter.setListner(new SearchPlacesAdapter.Listner() {
                    @Override
                    public void onClick(int pos) {
                        MedicalPlace copyPlace = ClassAdapter.convertSearchPlaceBranchToMedicalPlace(copyPlaces.getBranches().get(pos), Utility.getLanguageFromSettings(getActivity()));
                        DetailsActivity.startActivity(getActivity(), copyPlace);
                    }
                });
            }
        }
        return rootView;
    }
}
