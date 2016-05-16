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

public class AsignacionMoficar extends AppCompatActivity {

    private DataBaseHWork helper;
    private EditText idAsignacion;
    private EditText idLocal;
    //    private TextView Actividad;
    private EditText idActividad;

    //    private TextView Local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_moficar);
        helper = new DataBaseHWork(this);
        idAsignacion = (EditText) findViewById(R.id.idAsignacionLabel);
        idLocal = (EditText) findViewById(R.id.idLocal);
        idActividad = (EditText) findViewById(R.id.idActividad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignacion_moficar, menu);
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

    public void modificarAsignacion(View view) {
        String regInsertados;

        asignacion asignacion = new asignacion(idAsignacion.getText().toString(), idActividad.getText().toString(), idLocal.getText().toString());
        helper.abrir();
        regInsertados = helper.actualizarAsignacion(asignacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


    }

    public void consultarAsignacion(View view) {


        helper.abrir();
        asignacion asignacion =
                helper.consultarasignacion(idAsignacion.getText().toString());
        helper.cerrar();
        if (asignacion == null)
            Toast.makeText(this, "Alumno con carnet " +
                    idAsignacion.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else {
            idAsignacion.setText(asignacion.getIdAsignacionLocal());
            idLocal.setText(asignacion.getID_local());
            // Actividad.setText(asignacion.getSexo());
            idActividad.setText(asignacion.getIdActividad());
            //   Local.setText(asignacion. valueOf(alumno.getMatganadas()));


        }
    }

    public void limpiarTexto(View v) {
        idAsignacion.setText("");
        idLocal.setText("");
        idActividad.setText("");

    }

}
