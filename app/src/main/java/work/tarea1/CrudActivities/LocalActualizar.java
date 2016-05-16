package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.local;
import work.tarea1.R;

public class LocalActualizar extends AppCompatActivity {


    private DataBaseHWork helper;
    private EditText idLocal;
    private EditText direccion;
    private EditText capacidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);

        helper = new DataBaseHWork(this);
        idLocal = (EditText) findViewById(R.id.idLocal);
        direccion = (EditText) findViewById(R.id.direccion);
        capacidad = (EditText) findViewById(R.id.capacidad);


    }

    public void consultarLocal(View view) {
        helper.abrir();
        local local = helper.consultarLocal(idLocal.getText().toString());
        helper.cerrar();
        if (local == null)
            Toast.makeText(this, "Horario : " +
                    idLocal.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else {
            idLocal.setText(local.getID_local());
            direccion.setText(local.getDireccion());
            capacidad.setText(local.getCapacidad());
        }
    }

    public void modificarLocal(View view) {


        String regInsertados;

        local local = new local(idLocal.getText().toString(), direccion.getText().toString(), capacidad.getText().toString());
        helper.abrir();
        regInsertados = helper.actualizarLocal(local);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_actualizar, menu);
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




    public void limpiarTexto(View v) {
        idLocal.setText("");
        direccion.setText("");
        capacidad.setText("");
    }


}
