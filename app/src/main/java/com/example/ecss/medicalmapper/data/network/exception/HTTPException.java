package com.example.ecss.medicalmapper.data.network.exception;

/**
 * This Exception class is used for defining and throwing the exceptions triggered
 * when making an HTTP request.
 */
public class HTTPException extends Exception {
    private int mResponseCode;

    /**
     * Parametrized Constructor
     * @param responseCode Error response code for that exception
     * @param message The exception message
     */
    public HTTPException(int responseCode, String message) {
        super(message);
        mResponseCode = responseCode;
    }

    /**
     * It returns response code for that exception for being filtered and handled based on
     * the the code.
     * @return response code for that exception
     */
    public int getResponseCode() {
        return mResponseCode;
    }
}
