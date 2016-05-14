package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;

public class ActividadActualizarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataBaseHWork helper;

    Spinner spTipoActividad;
    Spinner spPersonaResponsable;
    EditText editDescripcion;
    EditText editIdActividad;

    String idPersonaResponsable;
    Integer idTipoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_actualizar);
        editIdActividad=(EditText)findViewById(R.id.editIdActividad);
        helper=new DataBaseHWork(this);
        spTipoActividad=(Spinner)findViewById(R.id.spinner_tipo_actividad);
        spTipoActividad.setOnItemSelectedListener(this);
        spPersonaResponsable=(Spinner)findViewById(R.id.spinner_id_persona);

        spPersonaResponsable.setOnItemSelectedListener(this);
        loadSpinnerData();
        editDescripcion=(EditText)findViewById(R.id.editDescripcion);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner_tipo_actividad)
        {
            String filter = parent.getItemAtPosition(position).toString();
            idTipoActividad=(Integer)helper.getValueSelectedSpineer("id_tipo_actividad","tipo_actividad","TipoActividad",false,filter);
            // Showing selected spinner item
            /*Toast.makeText(parent.getContext(), "You selected: " + idTipoActividad,
                    Toast.LENGTH_LONG).show();*/
        }
        else if(spinner.getId() == R.id.spinner_id_persona)
        {
            String filter = parent.getItemAtPosition(position).toString();
            idPersonaResponsable=(String)helper.getValueSelectedSpineer("id_persona","nombre","Persona",true,filter);
            // Showing selected spinner item
            /*Toast.makeText(parent.getContext(), "You selected: " +  idPersonaResponsable,
                    Toast.LENGTH_LONG).show();*/
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadSpinnerData(){
        spTipoActividad.setAdapter(helper.prepareSpinner(this,"TipoActividad", "tipo_actividad", "id_tipo_actividad"));
        spPersonaResponsable.setAdapter(helper.prepareSpinner(this,"Persona", "nombre", "id_persona"));
    }

    public void actualizarActividad(View v) {

        String idActividad=editIdActividad.getText().toString();
        System.out.println(idActividad);
        Integer idTipoActividad=this.idTipoActividad;
        String idPersonaResponsable=this.idPersonaResponsable;
        String descripcion=editDescripcion.getText().toString();
        Integer idAct=Integer.getInteger(idActividad);
        String regInsertados;
        Actividad a=new Actividad(idActividad,idTipoActividad,idPersonaResponsable,descripcion);
        helper.abrir();
        String estado=helper.actualizar(a);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();



}
    public void limpiarTexto(View v) {
        editDescripcion.setText("");
        editIdActividad.setText("");

    }
}
