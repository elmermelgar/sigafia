package work.tarea1.CrudActivities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoPersona;
import work.tarea1.R;

public class TipoPersonaEliminarActivity extends Activity {
    DataBaseHWork helper;
    EditText editCodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_persona_eliminar);
        helper=new DataBaseHWork(this);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
    }

    public void eliminarTipoPersona(View v){
        String regEliminadas;
        TipoPersona tipoPersona=new TipoPersona();

        tipoPersona.setCodigo(editCodigo.getText().toString());
        helper.abrir();
        regEliminadas=helper.eliminar(tipoPersona);
        helper.cerrar();
        Toast. makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
