package com.example.user.laywerbusiness;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class EnterCaseActivity extends AppCompatActivity {

    public int cont=1;

    //declaro algunas variables para el DatePicker
    private Calendar calendar;
    private TextView dateViewInit, dateViewEnd;
    private int year, month, day;
    private Spinner mySpinner;

    public void setDateInit(View view)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(),
                "calendario - fecha inicial", Toast.LENGTH_SHORT).show();
    }

    public void setDateEnd (View view)
    {
        showDialog(990);
        Toast.makeText(getApplicationContext(),
                "calendario - fecha final", Toast.LENGTH_SHORT).show();
    }

//llamada automática a onCreateDialog - presentar un dialogo de tipo DatePicker//   @Override
    protected Dialog onCreateDialog (int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this, DateListener_Init,
                    year, month, day);
        }

        if(id==990)
        {
            return new DatePickerDialog(this,DateListener_End,
                    year, month, day);
        }
        return null;
    }

     private DatePickerDialog.OnDateSetListener DateListener_Init = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate("fecha_inicial",arg1, arg2+1,arg3);
        }
    };

    private DatePickerDialog.OnDateSetListener DateListener_End = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate("fecha_final",arg1, arg2+1,arg3);
        }
    };

    //actualizar la fecha del día seleccionado en el cuadro de texto.
    private void showDate(String fecha, int year, int month, int day)
    {
        if(fecha.equals("fecha_inicial"))
        {
            dateViewInit.setText(new StringBuilder().
                    append(day).append("/").append(month).append("/").append(year));
        }
        if(fecha.equals("fecha_final"))
        {
            dateViewEnd.setText(new StringBuilder().
                    append(day).append("/").append(month).append("/").append(year));
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_case);

        dateViewInit = (TextView) findViewById(R.id.txtDateViewInit);
        dateViewEnd = (TextView) findViewById(R.id.txtDateViewEnd);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate("fecha_inicial",year, month+1, day);
        showDate("fecha_final",year,month+1, day);
    }

    public void newRegister(View view)
    {
        DataBaseSQLite DBH = new DataBaseSQLite(this);
        String name,
               start_date,
               end_date,
               state;

        ArrayList <Client> client_list = new ArrayList<Client>();
        ArrayList<Integer> ids = new ArrayList<Integer>();

        EditText txtname = (EditText)findViewById(R.id.txtName);
        //EditText txtstate = (EditText)findViewById(R.id.txtState);
        TextView txtstartdate = (TextView)findViewById(R.id.txtDateViewInit);
        TextView txtenddate = (TextView) findViewById(R.id.txtDateViewEnd);
        mySpinner = (Spinner)findViewById(R.id.spnState);

        name=txtname.getText().toString();
        //state=txtstate.getText().toString();
        state=String.valueOf(mySpinner.getSelectedItem());
        start_date=txtstartdate.getText().toString();
        end_date=txtenddate.getText().toString();

        client_list = DBH.getAllClients();

        for(Client cl:client_list)
        {
            ids.add(cl.get_id());
        }

        Client client=null;

        for(int i=0;i<=ids.size();i++)
        {
            if(!ids.contains(cont))
            {
                client = new Client(cont,name, start_date, end_date, state);
            }
        }
        DBH.addClient(client);
        cont++;
    }

    public void setList(View view)
    {
        mySpinner = (Spinner)findViewById(R.id.spnState);
        Toast.makeText(getApplicationContext(),"OnClickListener: "+
                        "\nEstado: "+String.valueOf(mySpinner.getSelectedItem()),
                            Toast.LENGTH_SHORT).show();
    }

}
