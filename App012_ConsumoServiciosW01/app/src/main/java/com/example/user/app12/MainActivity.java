package com.example.user.app12;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.label);
    }

    public void makeRequest(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            public void onResponse(String response){
                mTextView.setText("Response is: "+response.substring(0,500));
            }
        }, new Response.ErrorListener(){
            public void onErrorResponse (VolleyError error){
                mTextView.setText("That's kind of weird! It didn't work!!");
            }
        });
        queue.add(stringRequest);
    }

    public void makeRequest2(View view){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url2 =
                "http://maps.googleapis.com/maps/api/geocode/json?latlng=39.476245,-0.349448&sensor=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response){
                mTextView.setText("Response: "+response.toString());
            }
        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){
                mTextView.setText("That's kind of weird! It didn't work!!");
            }
        });
        queue.add(jsonObjectRequest);
    }


}
