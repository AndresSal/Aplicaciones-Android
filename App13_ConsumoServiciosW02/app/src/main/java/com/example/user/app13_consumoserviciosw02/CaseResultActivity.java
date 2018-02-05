package com.example.user.app13_consumoserviciosw02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CaseResultActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Contact> listViewContacts = new ArrayList<Contact>();
    private ArrayAdapter<Contact> adapter;

    final DatabaseHandler DB = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_result);
        listView = (ListView)findViewById(R.id.listaContactos);
        List<Contact>listaDB=DB.getAllContacts();

        adapter = new ArrayAdapter<Contact>(this,
                android.R.layout.simple_list_item_1, listViewContacts);
        listView.setAdapter(adapter);

        for (Contact cont:listaDB) {
            adapter.add(cont);
        }
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact pos = listViewContacts.get(position);
                Toast.makeText(getApplicationContext(),
                               "nombre: "+pos.getNombre()+
                               "\nemail: "+pos.getEmail()+
                               "\ndireccion: "+pos.getGenero()+
                                "\ngenero: "+pos.getGenero(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
