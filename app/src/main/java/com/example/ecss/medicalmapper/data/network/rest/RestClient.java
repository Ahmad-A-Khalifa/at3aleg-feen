package com.example.ecss.medicalmapper.data.network.rest;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * This is a helper class for making the HTTP requests with ease.
 */
public class RestClient {

    public static final String TAG = "RestClient";

    private static final int CONNECTION_TIMEOUT = 60 * 1000;
    private static final int SOCKET_TIMEOUT = 30 * 1000;

    private String mUrl;
    private List<DataPair> mDataPairs;
    private String mResponse;
    private String mMessage;
    private int mResponseCode;

    /**
     * Parametrized constructor, it has no other parameters as the request may not require
     * any addition parameters.
     * @param url the request url
     */
    public RestClient(String url) {
        this(url, null);
    }

    /**
     * Parametrized constructor, is meant to set both the request URL and the key-value pairs
     * being sent as parameters in the request.
     * @param url the request url
     * @param dataPairs a list of key-value pairs used for building composite requests with
     *                  parameters.
     */
    public RestClient(String url, List<DataPair> dataPairs) {
        mUrl = url;
        mDataPairs = dataPairs;
    }

    /**
     * This method returns the response body of the response of the HTTP request
     * @return the response body resulted from the HTTP request
     */
    public String getResponse() {
        return mResponse;
    }

    /**
     * This method returns the response code of the response of the HTTP request
     * @return the response code resulted from the HTTP request
     */
    public int getResponseCode() {
        return mResponseCode;
    }

    /**
     * This method returns the error message resulted from the HTTP request, if any.
     * @return the error message resulted from the HTTP request
     */
    public String getErrorMessage() {
        return mMessage;
    }

    /**
     * This method is responsible for calling the executing method for
     * the HTTP request based on the passed.
     * method type.
     * @param methodType the methods type of the HTTP request
     * @throws Exception
     */
    public void execute(String methodType) throws  Exception {
        Log.i("Method called: ", "execute");
        Log.d("Method parameters: ", methodType);
        switch (methodType) {
            case MethodType.GET:
                executeGetRequest();
                break;
            case MethodType.POST:
                executeRequest("POST");
                break;
            case MethodType.PUT:
                executeRequest("PUT");
                break;
            case MethodType.DELETE:
                executeRequest("DELETE");
                break;
        }
    }

    /**
     * This method is responsible for executing all the non-GET method types for
     * the HTTP request.
     * @param method the type of method being executed
     * @throws Exception any type of exception could be triggered while executing
     * this method
     */
    private void executeRequest(String method) throws Exception {
        Log.i("Method called: ", "executeRequest");
        Log.d("Method parameters: ", method);
        URL url;
        url = new URL(mUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setReadTimeout(SOCKET_TIMEOUT);
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        JSONObject entity = new JSONObject();
        if (mDataPairs != null) {
            for (int i = 0; i < mDataPairs.size(); i++) {
                DataPair dataPair = mDataPairs.get(i);
                entity.put(dataPair.getKey(), dataPair.getValue());
            }
        }
        byte[] outputBytes = entity.toString().getBytes("UTF-8");
        OutputStream os = conn.getOutputStream();
        os.write(outputBytes);
        mResponseCode = conn.getResponseCode();
        mMessage = conn.getResponseMessage();
        if (mResponseCode == HttpsURLConnection.HTTP_OK) {
            InputStream inputStream = conn.getInputStream();
            mResponse = convertStreamToString(inputStream);
            inputStream.close();
        }
        else {
            mResponse = "";
        }
    }

    /**
     * This method is responsible for executing the GET method types for
     * the HTTP request.
     * @throws Exception any type of exception could be triggered while executing
     * this method
     */
    private void executeGetRequest() throws Exception {
        Log.i("Method called: ", "executeGetRequest");
        URL url;
        if (mDataPairs != null && mDataPairs.size() > 0) {
            StringBuilder builder = new StringBuilder(mUrl);
            builder.append("?");
            for (DataPair pair : mDataPairs) {
                builder.append(pair.getKey());
                builder.append('=');
                builder.append(URLEncoder.encode(pair.getValue().toString(), "UTF-8"));
                builder.append('&');
            }
            builder.deleteCharAt(builder.length() - 1);
            mUrl = builder.toString();
        }
        System.out.println(mUrl);
        url = new URL(mUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(MethodType.GET);
        conn.setReadTimeout(SOCKET_TIMEOUT);
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setDoInput(true);
        mResponseCode = conn.getResponseCode();
        mMessage = conn.getResponseMessage();
        if (mResponseCode == HttpsURLConnection.HTTP_OK) {
            InputStream inputStream = conn.getInputStream();
            mResponse = convertStreamToString(inputStream);

            // Closing the input stream will trigger connection release
            inputStream.close();
        }
        else {
            mResponse = "";
        }
    }

    /**
     * This method is responsible for converting the InputStream resulted from the
     * response to a string to be parsed later.
     * @param inputStream the input stream resulted from the HTTP request
     * @return a string that can be parsed later to objects
     * @throws IOException An exception that is triggered while converting the input
     * stream to a String object
     */
    private String convertStreamToString(InputStream inputStream) throws IOException {
        Log.i("Method called: ", "convertStreamToString");
        Log.d("Method parameters: ", inputStream.toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        catch (Exception exception) {
            Log.e("Method error", exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception exception) {
                Log.e("Method error", exception.getMessage());
                exception.printStackTrace();
            }
        }

        String result;
        try {
            result = stringBuilder.toString();
        }
        catch (Exception exception) {
            Log.e("Method error", exception.getMessage());
            exception.printStackTrace();
            result = "";
        }
        return result;
    }

    /**
     * An interface that contains the names of the mostly used methods types in
     * the HTTP requests.
     */
    public interface MethodType {
        String GET = "GET";
        String POST = "POST";
        String PUT = "PUT";
        String DELETE = "DELETE";
    }

    /**
     * This class is used for creating pairs of key-value objects that are needed to
     * parsed and sent as parameters with the request
     */
    public static class DataPair {
        private String mKey;
        private Object mValue;

        /**
         * Parametrized constructor
         * @param key the key of the parameter being sent
         * @param value the value of the parameter being sent
         */
        public DataPair(String key, Object value) {
            mKey = key;
            mValue = value;
        }

        /**
         * This method returns the key of the parameter
         * @return the key of the parameter
         */
        public String getKey() {
            return mKey;
        }

        /**
         * This method returns the value of the parameter
         * @return the value of the parameter
         */
        public Object getValue() {
            return mValue;
        }
    }
}
