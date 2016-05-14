package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;

public class ActividadEliminarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText editIdActividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_eliminar);
        helper=new DataBaseHWork(this);
        editIdActividad=(EditText)findViewById(R.id.editIdActividad);
    }

    public void eliminarActividad(View v){
        String regEliminadas;
        Actividad actividad=new Actividad();

        actividad.setIdActividadString(editIdActividad.getText().toString());
        helper.abrir();
        regEliminadas=helper.eliminar(actividad);
        helper.cerrar();
        Toast. makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
