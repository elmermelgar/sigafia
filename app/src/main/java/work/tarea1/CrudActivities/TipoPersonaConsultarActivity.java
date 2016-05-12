package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoPersona;
import work.tarea1.R;

public class TipoPersonaConsultarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText editCod;
    EditText editCodigo;
    EditText editTipoPersona;
    EditText editDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_persona_consultar);
        helper=new DataBaseHWork(this);
        editCod=(EditText)findViewById(R.id.editCod);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
        editTipoPersona=(EditText)findViewById(R.id.editTipoPersona);
        editDescripcion=(EditText)findViewById(R.id.editDescripcion);
    }
    public void consultarTipoPersona(View v){
        helper.abrir();
        TipoPersona tp =helper.consultarTipoPersona(editCod.getText().toString());
        helper.cerrar();
        if(tp == null)
            Toast. makeText(this, "Tipo persona con codigo " + editCod.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCodigo.setText(tp.getCodigo());
            editTipoPersona.setText(tp.getTipoPersona());
            editDescripcion.setText(tp.getDescripcion());

        }

    }
    public void limpiarTexto(View v) {
        editCod.setText("");
        editCodigo.setText("");
        editTipoPersona.setText("");
        editDescripcion.setText("");

    }


}
