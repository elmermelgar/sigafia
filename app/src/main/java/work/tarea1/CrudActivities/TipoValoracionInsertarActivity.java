package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.R;

public class TipoValoracionInsertarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoValoracion;
    EditText edtTipoValoracion;
    EditText edtDescripcionTipoValoracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_valoracion_insertar);

        helper = new DataBaseHWork(this);

        edtIdTipoValoracion=(EditText)findViewById(R.id.idTipoValoracion);
        edtTipoValoracion=(EditText)findViewById(R.id.tipoValoracion);
        edtDescripcionTipoValoracion=(EditText)findViewById(R.id.descripcionTipoValoracion);
    }

    public void insertarTipoValoración(View v) {

        String regInsertados;

        TipoValoracion tipovaloracion = new TipoValoracion();
        tipovaloracion.setIdTipoValoracion(Integer.parseInt(edtIdTipoValoracion.getText().toString()));
        tipovaloracion.setTipoValoracion(edtTipoValoracion.getText().toString());
        tipovaloracion.setDescripciónTipoValoracion(edtDescripcionTipoValoracion.getText().toString());

        helper.abrir();
        regInsertados = helper.insertarTipoVal(tipovaloracion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        edtIdTipoValoracion.setText("");
        edtTipoValoracion.setText("");
        edtDescripcionTipoValoracion.setText("");
    }

}
