package com.example.user.app10_datepicker_spinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    private Spinner spinner1;
    private Spinner spinner2;

    public void setList(View view)
    {
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);

        Toast.makeText(getApplicationContext(),"OnClickListener:"+
        "\nSpinner1: "+String.valueOf(spinner1.getSelectedItem()) +
        "\nSpinner2: "+String.valueOf(spinner2.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }

    public void addItemsOnSpinner2()
    {
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        List<String> lista = new ArrayList<String>();
        lista.add("item 1");
        lista.add("item 2");
        lista.add("item 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this,
                android.R.layout.simple_spinner_item,lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView)findViewById(R.id.txtFecha);
        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month+1, day);

        addItemsOnSpinner2();
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(),
                "Ejemplo de calendario", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Dialog onCreateDialog(int Id)
    {
        if(Id==999)
        {
            return new DatePickerDialog(this,myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            showDate(i, i1+1, i2);
        }
    };

    private void showDate(int year, int month, int day)
    {
        dateView.setText(new StringBuilder().
                append(day).
                append("/").
                append(month).
                append("/").
                append(year));
    }
}
