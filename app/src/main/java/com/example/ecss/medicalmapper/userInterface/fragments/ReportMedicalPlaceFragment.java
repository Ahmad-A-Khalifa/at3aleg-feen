package com.example.ecss.medicalmapper.userInterface.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.data.network.repository.RemoteReportMedicalPlaceRepository;
import com.example.ecss.medicalmapper.data.network.service.ReportMedicalPlaceService;
import com.example.ecss.medicalmapper.data.network.util.InternetConnectionChecker;
import com.example.ecss.medicalmapper.presenter.ReportMedicalPlacePresenter;
import com.example.ecss.medicalmapper.presenter.view.ReportMedicalPlaceView;
import com.example.ecss.medicalmapper.utility.ErrorCode;
import com.example.ecss.medicalmapper.utility.UserDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class ReportMedicalPlaceFragment extends Fragment implements ReportMedicalPlaceView {

    public static final String TAG = "REPORT_MEDICAL_PLACE_FRAGMENT";

    @BindView(R.id.fragment_report_medical_place)
    LinearLayout mRootLayout;

    @BindView(R.id.fragment_report_medical_place_radio_group)
    RadioGroup mPlaceStateRadioGroup;

    @BindView(R.id.fragment_report_medical_place_comment_edit_text)
    EditText mCommentEditText;

    @BindString(R.string.base_api_url)
    String mBaseUrl;

    @BindString(R.string.state_closed)
    String mStateClosed;

    @BindString(R.string.state_outdated)
    String mStateOutdated;

    @BindString(R.string.done)
    String mDoneMessage;

    @BindString(R.string.error_general)
    String mGeneralErrorMessage;

    private static final int REPORT_REQUEST_ID = 1;

    private static final String ARGUMENT_USER_ID = "argument_user_id";
    private static final String ARGUMENT_PLACE_ID = "argument_place_id";
    private static final String ARGUMENT_BRANCH_ID = "argument_branch_id";

    private static final String STATE_USER_ID = "state_user_id";
    private static final String STATE_PLACE_ID = "state_place_id";
    private static final String STATE_BRANCH_ID = "state_branch_id";
    private static final String STATE_PLACE_STATE = "state_place_state";
    private static final String STATE_USER_COMMENT = "state_user_comment";

    private int mUserId;
    private int mPlaceId;
    private int mBranchId;
    private OnFragmentInteractionListener mFragmentInteractionListener;
    private ReportMedicalPlacePresenter mPresenter;
    private Map<Integer, String> mRadioButtonIdToStateMap;
    private ProgressDialog mProgressDialog;

    public static ReportMedicalPlaceFragment newInstance(int userId, int placeId, int branchId) {
        ReportMedicalPlaceFragment fragment = new ReportMedicalPlaceFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_USER_ID, userId);
        arguments.putInt(ARGUMENT_PLACE_ID, placeId);
        arguments.putInt(ARGUMENT_BRANCH_ID, branchId);
        fragment.setArguments(arguments);
        return fragment;
    }

    public ReportMedicalPlaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_medical_place, container, false);
        initialize(view, savedInstanceState);
        return view;
    }

    @SuppressLint("UseSparseArrays")
    private void initialize(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mRadioButtonIdToStateMap = new HashMap<>();
        mRadioButtonIdToStateMap.put(
                R.id.fragment_report_medical_place_not_available_radio_button,
                mStateClosed
        );
        mRadioButtonIdToStateMap.put(
                R.id.fragment_report_medical_place_info_outdated_radio_button,
                mStateOutdated
        );

        Map<String, Integer> stateToRadioButtonIdMap = new HashMap<>();
        stateToRadioButtonIdMap.put(
                mStateClosed,
                R.id.fragment_report_medical_place_not_available_radio_button
        );
        stateToRadioButtonIdMap.put(
                mStateOutdated,
                R.id.fragment_report_medical_place_info_outdated_radio_button
        );
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(STATE_USER_ID) &&
                savedInstanceState.containsKey(STATE_PLACE_ID) &&
                savedInstanceState.containsKey(STATE_BRANCH_ID) &&
                savedInstanceState.containsKey(STATE_PLACE_STATE) &&
                savedInstanceState.containsKey(STATE_USER_COMMENT)) {
            mUserId = savedInstanceState.getInt(STATE_USER_ID);
            mPlaceId = savedInstanceState.getInt(STATE_PLACE_ID);
            mBranchId = savedInstanceState.getInt(STATE_BRANCH_ID);
            String placeState = savedInstanceState.getString(STATE_PLACE_STATE);
            String userComment = savedInstanceState.getString(STATE_USER_COMMENT);
            mPlaceStateRadioGroup.check(stateToRadioButtonIdMap.get(placeState));
            mCommentEditText.setText(userComment);
        } else if (getArguments() != null &&
                getArguments().containsKey(ARGUMENT_USER_ID) &&
                getArguments().containsKey(ARGUMENT_PLACE_ID) &&
                getArguments().containsKey(ARGUMENT_BRANCH_ID)) {
            mUserId = getArguments().getInt(ARGUMENT_USER_ID);
            mPlaceId = getArguments().getInt(ARGUMENT_PLACE_ID);
            mBranchId = getArguments().getInt(ARGUMENT_BRANCH_ID);
        } else {
            showMessage(mGeneralErrorMessage);
            return;
        }
        mPresenter = new ReportMedicalPlacePresenter(
                this,
                new ReportMedicalPlaceService(),
                new RemoteReportMedicalPlaceRepository(mBaseUrl),
                new InternetConnectionChecker(getContext())
        );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_USER_ID, mUserId);
        outState.putInt(STATE_PLACE_ID, mPlaceId);
        outState.putInt(STATE_BRANCH_ID, mBranchId);
        outState.putString(
                STATE_PLACE_STATE,
                mRadioButtonIdToStateMap.get(mPlaceStateRadioGroup.getCheckedRadioButtonId())
        );
        outState.putString(STATE_USER_COMMENT, mCommentEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            String exceptionMessage = context.toString()
                    + " must implement OnFragmentInteractionListener";
            throw new RuntimeException(exceptionMessage);
        }
    }

    @Override
    public void onDetach() {
        mFragmentInteractionListener = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onSuccess(int requestId) {
        dismissProgressDialog();
        switch (requestId) {
            case REPORT_REQUEST_ID:
                showMessage(mDoneMessage);
                if (mFragmentInteractionListener != null) {
                    mFragmentInteractionListener.onReportFinished();
                }
                break;
        }
    }

    @Override
    public void onFailure(int requestId, int errorCode, String errorMessage) {
        dismissProgressDialog();
        switch (requestId) {
            case REPORT_REQUEST_ID:
                showMessage(getString(ErrorCode.getErrorMessageResource(errorCode)));
                break;
        }
    }

    @OnClick({
            R.id.fragment_report_medical_place_report_button,
            R.id.fragment_report_medical_place_cancel_button
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_report_medical_place_report_button:
                if (mPresenter != null) {
                    showProgressDialog();
                    mPresenter.reportMedicalPlace(
                            REPORT_REQUEST_ID,
                            mUserId,
                            mPlaceId,
                            mBranchId,
                            mRadioButtonIdToStateMap.get(
                                    mPlaceStateRadioGroup.getCheckedRadioButtonId()
                            ),
                            mCommentEditText.getText().toString()
                    );
                }
                break;
            case R.id.fragment_report_medical_place_cancel_button:
                if (mFragmentInteractionListener != null) {
                    mFragmentInteractionListener.onReportFinished();
                }
                break;
        }
    }

    private void showMessage(String message) {
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = UserDialog.showProgressDialog(getContext());
        } else {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public interface OnFragmentInteractionListener {
        void onReportFinished();
    }
}
