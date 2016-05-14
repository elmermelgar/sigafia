package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoActividad;
import work.tarea1.R;

public class TipoActividadActualizarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoActividad;
    EditText edtTipoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_actividad_actualizar);

        helper = new DataBaseHWork(this);

        edtIdTipoActividad=(EditText)findViewById(R.id.idTipoActividad);
        edtTipoActividad=(EditText)findViewById(R.id.tipoActividad);
    }

    public void actualizarTipoActividad(View v) {

        TipoActividad tipoactividad = new TipoActividad();
        tipoactividad.setIdTipoActividad(Integer.parseInt(edtIdTipoActividad.getText().toString()));
        tipoactividad.setTipoActividad(edtTipoActividad.getText().toString());

        helper.abrir();
        String estado = helper.actualizarTipoAct(tipoactividad);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        edtIdTipoActividad.setText("");
        edtTipoActividad.setText("");
    }
}
