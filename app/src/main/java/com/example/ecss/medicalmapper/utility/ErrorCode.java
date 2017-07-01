package com.example.ecss.medicalmapper.utility;

import com.example.ecss.medicalmapper.R;

/**
 * An interface that enumerates the names of the all errors that are raised while running
 * the application.
 */
public class ErrorCode {

    public static final int ERROR_GENERAL_ERROR = 0;
    public static final int ERROR_NO_NETWORK_CONNECTION = 1;
    public static final int ERROR_REPORTING_MEDICAL_PLACE = 2;

    /**
     * This method maps the error code to its corresponding message string resource id
     * @param errorCode code of the error raised
     * @return error message string resource id
     */
    public static int getErrorMessageResource(int errorCode) {
        int[] errorMessagesResources = new int[] {
                R.string.error_general_error,
                R.string.error_no_network_connection,
                R.string.error_reporting_medical_place
        };
        if (errorCode > errorMessagesResources.length) {
            errorCode = ERROR_GENERAL_ERROR;
        }
        return errorMessagesResources[errorCode];
    }
}
