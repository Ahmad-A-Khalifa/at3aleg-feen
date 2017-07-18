package com.example.ecss.medicalmapper.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.PlacesResponse;
import com.example.ecss.medicalmapper.model.place.Laboratory;
import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.adapters.SavedLabsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;

public class SavedLabsFragment extends Fragment {
    private View mRootView;
    private static final String ARGUMENT_EXTRA_SAVED_LABS = "labs";
    List<Laboratory> labs;
    @BindView(R.id.saved_labs_list)
    RecyclerView mList;
    private SavedLabsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SavedLabsFragment() {
        // Required empty public constructor
    }

    public static SavedLabsFragment newInstance(ArrayList<Laboratory> laboratoryList) {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(ARGUMENT_EXTRA_SAVED_LABS, laboratoryList);
        SavedLabsFragment fragment = new SavedLabsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_saved_labs, container, false);
        ButterKnife.bind(this, mRootView);
      /*  if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_SAVED_LABS) && getArguments().getParcelableArrayList(ARGUMENT_EXTRA_SAVED_LABS) != null) {

            labs = getArguments().getParcelableArrayList(ARGUMENT_EXTRA_SAVED_LABS);
            if (labs != null) {
                mAdapter = new SavedLabsAdapter();
                mLayoutManager = new LinearLayoutManager(getActivity());
                mList.setLayoutManager(mLayoutManager);
                mList.setItemAnimator(new DefaultItemAnimator());
                mList.setAdapter(mAdapter);
                mAdapter.updateAdapter(labs);
            }*/

        callSavedPlacesFromApi();

        return mRootView;
    }

    private void callSavedPlacesFromApi() {
        if (checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            if (getUserFromSharedPreferences(getContext()).getUserId() != -1) {
                Call<PlacesResponse> call = apiService.GetSavedPlaces(getUserFromSharedPreferences(getContext()).getUserId());
                call.enqueue(new Callback<PlacesResponse>() {
                    @Override
                    public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {

                        MedicalPlaces places = null;
                        ApiCallStatus apiCallStatus = null;

                        if (response.body() != null) {
                            places = response.body().getMedicalPlaces();
                            apiCallStatus = response.body().getApiCallStatus();
                        }

                        if (apiCallStatus != null && places != null && apiCallStatus.getIsSuccessful()) {
                            mAdapter = new SavedLabsAdapter();
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mList.setLayoutManager(mLayoutManager);
                            mList.setItemAnimator(new DefaultItemAnimator());
                            mList.setAdapter(mAdapter);
                            if (places.getLaboratories() != null)
                                mAdapter.updateAdapter(places.getLaboratories());

                        } else {
                            if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                                Toast.makeText(getContext(), getString(R.string.Nothingtoshow), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlacesResponse> call, Throwable t) {
                    }
                });
            } else {
                Toast.makeText(getActivity(), getString(R.string.sign_in_problem_message), Toast.LENGTH_LONG).show();
            }
        }
    }
}
