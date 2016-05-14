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

public class AsignacionInsertarActivity extends AppCompatActivity {

    private DataBaseHWork helper;
    private EditText idAsignacion;
    private EditText idActividad;
    private EditText idLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_insertar);

        helper = new DataBaseHWork(this);
        idAsignacion = (EditText) findViewById(R.id.idAsignacion);
        idActividad = (EditText) findViewById(R.id.idActividad);
        idLocal = (EditText) findViewById(R.id.idLocal);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignacion_insertar, menu);
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

    public void insertarAsignacion(View view) {
        String regInsertados;

        asignacion asignacion = new asignacion( idAsignacion.getText().toString(), idActividad.getText().toString(), idLocal.getText().toString());
        helper.abrir();
        regInsertados = helper.insertar(asignacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}
