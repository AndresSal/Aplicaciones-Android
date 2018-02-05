package com.example.user.app13_consumoserviciosw02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CaseConsultActivity extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_consult);
        myTextView=(TextView)findViewById(R.id.labelC);
    }

    public void consultContacts(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 ="https://api.androidhive.info/contacts/";
        final DatabaseHandler DB = new DatabaseHandler(this);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray contacts = jsonObject.getJSONArray("contacts");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject contacto = contacts.getJSONObject(i);
                        String id = contacto.getString("id");
                        String nombre = contacto.getString("name");
                        String email = contacto.getString("email");
                        String direccion = contacto.getString("address");
                        String genero = contacto.getString("gender");

                        DB.addContact(new Contact(id,nombre,email,direccion,genero));
                    }
                } catch (final JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myTextView.setText("That didn't work!!");
            }
        }
        );

        queue.add(jsObjRequest);

        Intent intent = new Intent(this, CaseResultActivity.class);
        startActivity(intent);
    }
}
