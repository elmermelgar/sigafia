package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.R;

public class TipoValoracionConsultarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoValoracion;
    EditText edtTipoValoracion;
    EditText edtDescripcionTipoValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_valoracion_consultar);

        helper = new DataBaseHWork(this);

        edtIdTipoValoracion=(EditText)findViewById(R.id.idTipoValoracion);
        edtTipoValoracion=(EditText)findViewById(R.id.tipoValoracion);
        edtDescripcionTipoValoracion=(EditText)findViewById(R.id.descripcionTipoValoracion);
    }

    public void consultarTipoValoracion(View v) {
        helper.abrir();
        TipoValoracion tipovaloracion = helper.consultarTipoVal(edtIdTipoValoracion.getText().toString());
        helper.cerrar();
        if(tipovaloracion == null)
            Toast.makeText(this, "Tipo de Valoración con codigo " + edtIdTipoValoracion.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            edtTipoValoracion.setText(tipovaloracion.getTipoValoracion());
            edtDescripcionTipoValoracion.setText(tipovaloracion.getDescripciónTipoValoracion());

        }
    }

    public void limpiarTexto(View v) {
        edtIdTipoValoracion.setText("");
        edtTipoValoracion.setText("");
        edtDescripcionTipoValoracion.setText("");
    }
}
