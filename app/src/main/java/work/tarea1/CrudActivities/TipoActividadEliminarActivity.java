package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoActividad;
import work.tarea1.R;

public class TipoActividadEliminarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_eliminar);

        helper = new DataBaseHWork(this);

        edtIdTipoActividad=(EditText)findViewById(R.id.idTipoActividad);
    }

    public void eliminarTipoActividad(View v){
        String regEliminadas;
        TipoActividad tipoactividad=new TipoActividad();
        tipoactividad.setIdTipoActividad(Integer.parseInt(edtIdTipoActividad.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarTipoAct(tipoactividad);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}
