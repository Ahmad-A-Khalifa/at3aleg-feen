package com.example.ecss.medicalmapper.chatbot;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmed on 27/06/17.
 */

public class connection extends AsyncTask<String,Void,String > {


    public static String result = "";
    final String LOG_TAG = connection.class.getSimpleName();

    protected String fun(String s)
    {
        return s;
    }



    @Override
    protected String doInBackground(String... params) {

        String tag_url = "my_tag_url";



        String url = params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;






        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast


            Uri builtUri =  Uri.parse(url).buildUpon().build();

            URL ur = new URL(builtUri.toString());
            // Log.v(LOG_TAG,"Built Url" + builtUri.toString());
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) ur.openConnection();

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Basic YzNmM2U5YTQtNTkwOC00MGJkLWE1YjQtYjVjYjQyODMzZGNhOlZjZVJmdDJpWWk4eg==");

            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            if (params[1] != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(params[1]);
                writer.flush();
            }

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            //Log.v(LOG_TAG,"forecast Json String"+forecastJsonStr);
        } catch (IOException e) {
            //Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }


        return forecastJsonStr;
    }
}
