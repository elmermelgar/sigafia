package work.tarea1.CrudActivities;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.simple.parser.ParseException;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

@SuppressLint("NewApi")
public class ActividadInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DataBaseHWork helper;

    Spinner spTipoActividad;
    Spinner spPersonaResponsable;
    EditText editDescripcion;
    EditText editIdActividad;
    EditText editFecha;

    String idPersonaResponsable;
    Integer idTipoActividad;



    @SuppressLint("NewApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_insertar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode. setThreadPolicy(policy);

        editIdActividad=(EditText)findViewById(R.id.editIdActividad);
        editFecha=(EditText)findViewById(R.id.editFecha);
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
            idTipoActividad=(Integer)helper.getValueSelectedSpineer("idTipoActividad","tipoActividad","tipoActividad",false,filter);
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
        spTipoActividad.setAdapter(helper.prepareSpinner(this,"tipoActividad", "tipoActividad", "idTipoActividad"));
        spPersonaResponsable.setAdapter(helper.prepareSpinner(this,"Persona", "nombre", "id_persona"));
    }


    public void insertarActividad(View v) {



        String idActividad=editIdActividad.getText().toString();
        Integer idTipoActividad=this.idTipoActividad;
        String idPersonaResponsable=this.idPersonaResponsable;
        String descripcion=editDescripcion.getText().toString();
        String fecha=editFecha.getText().toString();
        String regInsertados;



        Actividad a=new Actividad(idActividad,idTipoActividad,idPersonaResponsable,descripcion,fecha);
        helper.abrir();
        regInsertados=helper.insertar(a);

        helper.cerrar();
        //String id=helper.getIdTabla("Actividad","id_actividad").toString();
        editIdActividad.setText(regInsertados);
        Toast.makeText(this, "Exito al guardar", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Id. Actividad:"+, Toast.LENGTH_SHORT).show();
    }

    public void insertarActividadHost(View v)  throws ParseException {

        String url="";
        String idActividad=this.editIdActividad.getText().toString();
        Integer idTipoActividad=this.idTipoActividad;
        String idPersonaResponsable=this.idPersonaResponsable;
        String descripcion=editDescripcion.getText().toString();
        String fecha=editFecha.getText().toString();

        url="http://grupo16pdm16.netne.net/ws_actividad_insertar.php?idactividad="+idActividad+"&idtipoactividad="+idTipoActividad+"&idpersona="+idPersonaResponsable+"&descripcion="+descripcion+"&fecha="+fecha;
        ControladorServicio.insertarActividadPHP(url,this);
        limpiarTexto();

    }
    public void limpiarTexto() {
        editDescripcion.setText("");
        editIdActividad.setText("");
        editFecha.setText("");

    }
}
