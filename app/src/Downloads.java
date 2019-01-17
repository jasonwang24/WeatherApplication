package com.example.jwang.weatherapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloads extends AsyncTask<String,Void,String> {



    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                char current = (char) data;

                result += current;

                data = reader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        try{
            JSONObject jsonObject = new JSONObject(result);
            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));
            double temperature = double.parsedouble(weatherData.getString("temp"));
            String weatherInfo = jsonObject.getString("weather"); //might need to fix


            double tempInt = double.parsedouble(weatherData.getString("temp"));
            int tempIn = (int) (tempInt*1.8-459.67);

            MainActivity.temperatureTextView.setText(String.valueOf(tempIn)+"F");

            MainActivity.placeTextView.setText(jsonObject.getString("name"));

            JSONArray jsonArray = new JSONArray(weatherInfo);

            for (int i =0; i<jsonArray.length();i++){
                JSONObject jsonPart = jsonArray.getJSONObject(i);
                JSONObject weatherData = new JSONObject(jsonObject.getString("main"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }
