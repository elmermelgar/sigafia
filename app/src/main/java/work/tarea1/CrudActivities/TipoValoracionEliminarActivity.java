package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.R;

public class TipoValoracionEliminarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_valoracion_eliminar);

        helper = new DataBaseHWork(this);

        edtIdTipoValoracion=(EditText)findViewById(R.id.idTipoValoracion);
    }

    public void eliminarTipoValoracion(View v){
        String regEliminadas;
        TipoValoracion tipovaloracion=new TipoValoracion();
        tipovaloracion.setIdTipoValoracion(Integer.parseInt(edtIdTipoValoracion.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarTipoVal(tipovaloracion);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
