package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.R;

public class LocalEliminar extends AppCompatActivity {

    private EditText idLocal;
    private DataBaseHWork helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);

        helper = new DataBaseHWork(this);
        idLocal = (EditText) findViewById(R.id.idLocal);
    }


    public void borrarLocal(View view) {
        String cont;
        helper.abrir();
        cont = helper.borrarLocal(idLocal.getText().toString());
        helper.cerrar();
        Toast.makeText(this, cont, Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_eliminar, menu);
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


}
