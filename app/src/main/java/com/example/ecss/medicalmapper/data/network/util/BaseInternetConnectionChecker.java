package com.example.ecss.medicalmapper.data.network.util;

public interface BaseInternetConnectionChecker {

    /**
     * This method checks the internet connection and returns true if the device is connected
     * to the internet, false otherwise.
     * @return true if device is connected to the internet, false otherwise.
     */
    boolean isNetworkAvailable();
}
