package com.example.user.laywerbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consultCase(View view)
    {
        Intent intent = new Intent(this,ConsultCaseActivity.class);
        startActivity(intent);
    }

    public void enterCase(View view)
    {
        Intent intent = new Intent(this, EnterCaseActivity.class);
        startActivity(intent);
    }

    public void seeClientsList(View view)
    {
        Intent intent = new Intent(this, ClientListActivity.class);
        startActivity(intent);
    }
}
