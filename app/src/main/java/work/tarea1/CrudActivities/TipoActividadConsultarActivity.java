package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoActividad;
import work.tarea1.R;

public class TipoActividadConsultarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoActividad;
    EditText edtTipoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_consultar);

        helper = new DataBaseHWork(this);

        edtIdTipoActividad=(EditText)findViewById(R.id.idTipoActividad);
        edtTipoActividad=(EditText)findViewById(R.id.tipoActividad);
    }

    public void consultarTipoActividad(View v) {
        helper.abrir();
        TipoActividad tipoactividad = helper.consultarTipoAct(edtIdTipoActividad.getText().toString());
        helper.cerrar();
        if(tipoactividad == null)
            Toast.makeText(this, "Tipo de Actividad con codigo " + edtIdTipoActividad.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            edtTipoActividad.setText(tipoactividad.getTipoActividad());

        }
    }

    public void limpiarTexto(View v) {
        edtIdTipoActividad.setText("");
        edtTipoActividad.setText("");
    }
}
