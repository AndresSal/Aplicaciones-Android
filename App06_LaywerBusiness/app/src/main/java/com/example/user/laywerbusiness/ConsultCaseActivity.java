package com.example.user.laywerbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ConsultCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_case);
    }

    public void consultResult(View view)
    {
        Intent intent = new Intent(this, ConsultResultsActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText5);
        String query = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,query);
        startActivity(intent);
    }
}
