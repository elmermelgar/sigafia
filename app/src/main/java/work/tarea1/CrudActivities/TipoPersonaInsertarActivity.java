package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoPersona;
import work.tarea1.R;

public class TipoPersonaInsertarActivity extends AppCompatActivity {
    DataBaseHWork helper;
    EditText editCodigo;
    EditText editTipoPersona;
    EditText editDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_persona_insertar);
        helper=new DataBaseHWork(this);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
        editTipoPersona=(EditText)findViewById(R.id.editTipoPersona);
        editDescripcion=(EditText)findViewById(R.id.editDescripcion);

    }
    public void insertarTipoPersona(View v) {
        String codigo=editCodigo.getText().toString();
        String tipoPersona=editTipoPersona.getText().toString();
        String descripcion=editDescripcion.getText().toString();
        String regInsertados;

        TipoPersona tp=new TipoPersona(codigo,tipoPersona,descripcion);
        helper.abrir();
        regInsertados=helper.insertar(tp);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editTipoPersona.setText("");
        editDescripcion.setText( "");

    }
}
