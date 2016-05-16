package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.asignacion;
import work.tarea1.R;

public class AsignacionInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DataBaseHWork helper;
    private EditText idAsignacion;
    private int idActividad;
    private String idLocal;

    Spinner spAsignacionInsert;
    Spinner spinnerAsigancionLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_insertar);

        helper = new DataBaseHWork(this);
        idAsignacion = (EditText) findViewById(R.id.idAsignacionLabel);
//        idActividad = (EditText) findViewById(R.id.idActividad);
//        idLocal = (EditText) findViewById(R.id.idLocal);


        spAsignacionInsert=(Spinner)findViewById(R.id.spinnerAsignacionInsert);
        spinnerAsigancionLocal=(Spinner)findViewById(R.id.spinnerAsigancionLocal);
        spAsignacionInsert.setOnItemSelectedListener(this);
        spinnerAsigancionLocal.setOnItemSelectedListener(this);

        loadSpinnerData();



    }

    private void loadSpinnerData() {
        spAsignacionInsert.setAdapter(helper.prepareSpinner(this,"Actividad", "descripcion", "id_actividad"));
        spinnerAsigancionLocal.setAdapter(helper.prepareSpinner(this, "LOCAL", "ID_LOCAL", "ID_LOCAL"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignacion_insertar, menu);
        return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinnerAsignacionInsert)
        {
            String filter = parent.getItemAtPosition(position).toString();
            idActividad= (Integer) helper.getValueSelectedSpineer("id_actividad", "descripcion", "Actividad",false,filter);
            // Showing selected spinner item
//            Toast.makeText(parent.getContext(), "You selected: " + idActividad,
//                    Toast.LENGTH_LONG).show();
        }
        else if(spinner.getId() == R.id.spinnerAsigancionLocal)
        {
            String filter = parent.getItemAtPosition(position).toString();
            idLocal= (String) helper.getValueSelectedSpineer("ID_LOCAL", "ID_LOCAL", "LOCAL",true,filter);
           //Showing selected spinner item
//            Toast.makeText(parent.getContext(), "You selected: " +  idLocal,
//                    Toast.LENGTH_LONG).show();
        }
    }

    public void insertarAsignacion(View view) {
        String regInsertados;

        asignacion asignacion = new asignacion( idAsignacion.getText().toString(), Integer.toString(idActividad) , idLocal.toString());
        helper.abrir();
        regInsertados = helper.insertarAsinacion(asignacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        idAsignacion.setText("");
//        idLocal.setText("");
//        idActividad.setText("");

    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
