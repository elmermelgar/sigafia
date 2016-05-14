
package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;

public class EliminarValoracionActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText idValoración;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_valoracion);

        helper = new DataBaseHWork(this);
        idValoración= (EditText) findViewById(R.id.idValoración);
    }

    public void eliminarValoracion(View v){
        String regEliminadas;
        Valoracion valoracion=new Valoracion();
        valoracion.setIdValoración(Integer.parseInt(idValoración.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarValoración(valoracion);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
