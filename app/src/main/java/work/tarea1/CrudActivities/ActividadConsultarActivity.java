package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;

public class ActividadConsultarActivity extends AppCompatActivity {

    DataBaseHWork helper;

    EditText valueIdActividad;
    EditText editIdActividad;
    EditText editIdTipoActividad;
    EditText editIdPersona;
    EditText editDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_consultar);
        helper=new DataBaseHWork(this);
        valueIdActividad=(EditText)findViewById(R.id.valueIdActividad);
        editIdActividad=(EditText)findViewById(R.id.editIdActividad);
        editIdTipoActividad=(EditText)findViewById(R.id.editIdTipoActividad);
        editIdPersona=(EditText)findViewById(R.id.editIdPersona);
        editDescripcion=(EditText)findViewById(R.id.editDescripcion);
    }

    public void consultarActividad(View v){
        helper.abrir();
        Actividad a =helper.consultarActividad(valueIdActividad.getText().toString());
        helper.cerrar();
        if(a == null)
            Toast. makeText(this, "Actividad con id. " + valueIdActividad.getText().toString() + " no encontrada", Toast.LENGTH_LONG).show();
        else{

            editIdActividad.setText(a.getIdActividad().toString());
            editIdTipoActividad.setText(a.getIdTipoActividad().toString());
            editIdPersona.setText(a.getIdPersona().toString());
            editDescripcion.setText(a.getDescripcion());


        }

    }
    public void limpiarTexto(View v) {

        editIdPersona.setText("");
       editIdTipoActividad.setText("");
        editDescripcion.setText("");
        editIdActividad.setText("");

    }
}
