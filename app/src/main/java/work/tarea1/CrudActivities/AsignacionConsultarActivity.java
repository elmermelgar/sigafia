package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.asignacion;
import work.tarea1.R;

public class AsignacionConsultarActivity extends AppCompatActivity {

    private DataBaseHWork helper;
    private EditText idAsignacion;
    private EditText idLocal;
//    private TextView Actividad;
    private EditText idActividad;
//    private TextView Local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_consultar);

        helper = new DataBaseHWork(this);
        idAsignacion = (EditText) findViewById(R.id.idAsignacion);
        idLocal = (EditText) findViewById(R.id.idLocal);
        idActividad = (EditText) findViewById(R.id.idActividad);
//        Actividad = (TextView) findViewById(R.id.Actividad);
//        Local = (TextView) findViewById(R.id.Local);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignacion_consultar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void consultarAsignacion(View view) {
        helper.abrir();
        asignacion asignacion =
                helper.consultarasignacion(idAsignacion.getText().toString());
        helper.cerrar();
        if(asignacion == null)
            Toast. makeText(this, "Alumno con carnet " +
                    idAsignacion.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            idAsignacion.setText(asignacion.getCod_asignacion());
            idLocal.setText(asignacion.getIdLocal());
           // Actividad.setText(asignacion.getSexo());
            idActividad.setText(asignacion.getActividadId());
         //   Local.setText(asignacion. valueOf(alumno.getMatganadas()));
            }
    }
}
