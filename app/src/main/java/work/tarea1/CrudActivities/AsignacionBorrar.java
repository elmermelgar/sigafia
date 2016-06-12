package work.tarea1.CrudActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

public class AsignacionBorrar extends AppCompatActivity {

    private EditText editIdAsignacion;
    private DataBaseHWork helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_borrar);

        helper = new DataBaseHWork(this);
        editIdAsignacion = (EditText) findViewById(R.id.idAsignacionInput);
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
        cont = helper.borrarAsignacion(editIdAsignacion.getText().toString());
        helper.cerrar();
        Toast.makeText(this, cont, Toast.LENGTH_SHORT).show();

    }
    public void asignacionEliminarHost(View view) {
        String idAsignacion=this.editIdAsignacion.getText().toString();
        String url="http://grupo16pdm16.netne.net/ws_asignacion_eliminar.php?idasignacionlocal="+idAsignacion;
        Log.v("URL:  ", url);
        ControladorServicio.eliminarAsignacionPHP(url, this);
    }
}
