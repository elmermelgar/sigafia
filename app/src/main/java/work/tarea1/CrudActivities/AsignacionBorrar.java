package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.R;
import work.tarea1.PrivetClass.asignacion;
import work.tarea1.DataBaseHWork;

public class AsignacionBorrar extends AppCompatActivity {

    private EditText idAsignacion;
    private DataBaseHWork helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_borrar);

        helper = new DataBaseHWork(this);
        idAsignacion = (EditText) findViewById(R.id.idAsignacionInput);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignacion_borrar, menu);
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

    public void borrarAsignacion(View view) {
        String cont;
        helper.abrir();
        cont = helper.borrarAsignacion(idAsignacion.getText().toString());
        helper.cerrar();
        Toast.makeText(this, cont, Toast.LENGTH_SHORT).show();

    }
    public void asignacionEliminarHost(View view) {


    }
}
