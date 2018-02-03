package com.example.user.laywerbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ClientListActivity extends AppCompatActivity {

    private ListView lstView;
    private ArrayList<Client>listViewClients = new ArrayList<Client>();
    private ArrayAdapter<String>adapter;
    private ArrayList<String>listClientsNames = new ArrayList<String>();
    //public final Intent intent = new Intent(this, ListResultActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        DataBaseSQLite DBH = new DataBaseSQLite(this);
        listViewClients = DBH.getAllClients();
        for(Client c:listViewClients)
        {
            //if(!listClientsNames.contains(c.get_name()))
            //{
                listClientsNames.add(c.get_name());
            //}
        }
        lstView=(ListView)findViewById(R.id.mainListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listClientsNames);
        lstView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        lstView.setOnItemClickListener
        (new AdapterView.OnItemClickListener()
            {
                String var;
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    var= listClientsNames.get(position);
                    generateActivity(var);

                }

            }
        );
    }

    public void generateActivity(String name)
    {
        Intent intent = new Intent(this, ListResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE,name);
        startActivity(intent);
    }
}
