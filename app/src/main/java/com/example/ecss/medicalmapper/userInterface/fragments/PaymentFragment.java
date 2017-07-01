package com.example.ecss.medicalmapper.userInterface.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ecss.medicalmapper.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.id.expiry_date;
import static com.example.ecss.medicalmapper.R.id.finish_btn;

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentFragment extends Fragment {
    private static final int MAX_NUMBER_CARD_NUMBER = 16;
    private static final int NUMBER_CVV = 3;

    @BindView(finish_btn)
    Button mFinishButton;

    @BindView(R.id.card_number)
    EditText mCardNumberEditText;

    @BindView(expiry_date)
    EditText mExpiryDateEditText;

    @BindView(R.id.cvv)
    EditText mCvvEditText;
    private View mRootView;

    public PaymentFragment() {
    }

    public static PaymentFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        PaymentFragment fragment = new PaymentFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_payment, container, false);

        ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @OnClick({R.id.finish_btn, expiry_date})
    public void onClick(View v) {

        switch (v.getId()) {
            case finish_btn:
                //HomeScreenActivity.startActivity(getContext());
                break;

            case expiry_date:

                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();

                break;
        }
    }

    public boolean validate() {

        boolean valid = true;

        String CardNumber = "";
        if (mCardNumberEditText.getText() != null) {
            CardNumber = mCardNumberEditText.getText().toString();
        }

        if (CardNumber.isEmpty() || CardNumber.length() > MAX_NUMBER_CARD_NUMBER) { //need to check the number of digits in a Card
            mCardNumberEditText.setError(getString(R.string.error_validate_card_number));
            valid = false;
        } else {
            mCardNumberEditText.setError(null);
        }

        String ExpiryDate = "";
        if (mExpiryDateEditText.getText() != null) {
            ExpiryDate = mExpiryDateEditText.getText().toString();
        }
        if (ExpiryDate.isEmpty()) {//need to detect if the input is a date
            mExpiryDateEditText.setError(getString(R.string.error_validate_expiry_date));
            valid = false;
        } else {
            mExpiryDateEditText.setError(null);
        }

        String Cvv = "";
        if (mCvvEditText.getText() != null) {
            Cvv = mCvvEditText.getText().toString();
        }
        if (Cvv.isEmpty() || Cvv.length() != NUMBER_CVV) {
            mCvvEditText.setError(getString(R.string.error_validate_cvv));
            valid = false;
        } else {
            mCvvEditText.setError(null);
        }

        return valid;
    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            mExpiryDateEditText.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            System.out.println(mExpiryDateEditText.getText().toString());
        }
    }
}