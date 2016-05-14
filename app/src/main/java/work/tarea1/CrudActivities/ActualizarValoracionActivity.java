package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;

public class ActualizarValoracionActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText idValoración;
    EditText idTipoValoración;
    EditText idAsignaciónLocal;
    EditText idPersona;
    EditText descripciónValoración;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_valoracion);

        helper = new DataBaseHWork(this);
        idValoración= (EditText) findViewById(R.id.idValoración);
        idTipoValoración = (EditText) findViewById(R.id.idTipoValoración);
        idAsignaciónLocal = (EditText) findViewById(R.id.idAsignacionLocal);
        idPersona = (EditText) findViewById(R.id.idPersona);
        descripciónValoración = (EditText) findViewById(R.id.descripciónValoración);
    }

    public void actualizarValoracion(View v) {
        Valoracion valoracion = new Valoracion();

        valoracion.setIdValoración(Integer.parseInt(idValoración.getText().toString()));
        valoracion.setIdTipoValoración(Integer.parseInt(idTipoValoración.getText().toString()));
        valoracion.setIdAsignaciónLocal(Integer.parseInt(idAsignaciónLocal.getText().toString()));
        valoracion.setIdPersona(Integer.parseInt(idPersona.getText().toString()));
        valoracion.setDescripciónValoración(descripciónValoración.getText().toString());

        helper.abrir();
        String estado = helper.actualizarValoración(valoracion);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        idValoración.setText("");
        idTipoValoración.setText("");
        idAsignaciónLocal.setText("");
        idPersona.setText("");
        descripciónValoración.setText("");
    }
}
