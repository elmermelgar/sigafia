package work.tarea1.CrudActivities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;
@SuppressLint("NewApi")
public class ActividadEliminarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText editIdActividad;
    @SuppressLint("NewApi")
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

    public void eliminarActividadHost(View v){
        String idActividad=editIdActividad.getText().toString();
        String url;
        url="http://grupo16pdm16.netne.net/ws_actividad_delete.php?idactividad="+idActividad;
        System.out.println("URL:"+url);
        ControladorServicio.deleteActividadPHP(url, this);
    }
}
