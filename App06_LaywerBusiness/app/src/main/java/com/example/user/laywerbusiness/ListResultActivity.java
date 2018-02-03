package com.example.user.laywerbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        Intent intent = getIntent();
        DataBaseSQLite DBH = new DataBaseSQLite(this);

        String name=intent.getStringExtra(EXTRA_MESSAGE);
        Client cliente = DBH.getClientByName(name);

        TextView txtname = (TextView)findViewById(R.id.resultName);
        txtname.setText(cliente.get_name());

        TextView txtinit = (TextView)findViewById(R.id.resultInitDate);
        txtinit.setText(cliente.get_start_date());

        TextView txtend = (TextView)findViewById(R.id.resultFinalDate);
        txtend.setText(cliente.get_end_date());

        TextView txtstatus = (TextView)findViewById(R.id.resultStatus);
        txtstatus.setText(cliente.get_state());
    }
}
