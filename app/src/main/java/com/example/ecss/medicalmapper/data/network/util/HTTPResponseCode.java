package com.example.ecss.medicalmapper.data.network.util;

/**
 * An interface that enumerates the names of the mostly triggered HTTP response codes retrieved
 * after the HTTP request is done.
 */
public interface HTTPResponseCode {
    int UNKNOWN = -1;
    int OK = 200;
    int BAD_REQUEST = 400;
    int UNAUTHORIZED = 401;
}
