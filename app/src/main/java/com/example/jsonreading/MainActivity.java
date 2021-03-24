package com.example.jsonreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get_bboundry();
        //get_bboundry_array();
    }

    public void get_bboundry() {
        String json;
        try {
            InputStream is = getAssets().open("bboundry.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String basin = jsonObject.getString("basin");
                String lon = jsonObject.getString("long");
                String lat = jsonObject.getString("lat");

                Log.v("JSON Reading , ", basin + " : " + lon + " : " + lat);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    public void get_bboundry_array() {
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAssets("bboundry_array.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("BAG");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                String lon = userData.getString("long");
                String lat = userData.getString("lat");

                Log.v("JSON Reading ", lon + " : " + lat);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String JsonDataFromAssets(String flname) {
        String json = null;
        try {
            InputStream inputStream = getAssets().open(flname);
            int sizeOffFile = inputStream.available();
            byte[] bufferData = new byte[sizeOffFile];
            inputStream.read(bufferData);
            inputStream.close();

            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}