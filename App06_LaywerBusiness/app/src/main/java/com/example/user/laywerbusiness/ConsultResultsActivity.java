package com.example.user.laywerbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ConsultResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_results);

        DataBaseSQLite DBH = new DataBaseSQLite(this);

        Intent intent = getIntent();
        String query = intent.getStringExtra(EXTRA_MESSAGE);

        Client client = DBH.getClientByName(query);

        EditText nametext= (EditText)findViewById(R.id.editText6);
        nametext.setText(client.get_name());

        EditText startdatetext = (EditText) findViewById(R.id.editText7);
        startdatetext.setText(client.get_start_date());

        EditText enddatetext = (EditText) findViewById(R.id.editText8);
        enddatetext.setText(client.get_end_date());

        EditText statetext = (EditText) findViewById(R.id.editText9);
        startdatetext.setText(client.get_state());
    }

}
