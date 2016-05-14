package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;

public class ConsultarValoracionActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText idValoración;
    EditText idTipoValoración;
    EditText idAsignaciónLocal;
    EditText idPersona;
    EditText descripciónValoración;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_valoracion);

        helper = new DataBaseHWork(this);
        idValoración= (EditText) findViewById(R.id.idValoración);
        idTipoValoración = (EditText) findViewById(R.id.idTipoValoración);
        idAsignaciónLocal = (EditText) findViewById(R.id.idAsignacionLocal);
        idPersona = (EditText) findViewById(R.id.idPersona);
        descripciónValoración = (EditText) findViewById(R.id.descripciónValoración);
    }

    public void consultarValoracion(View v) {
        helper.abrir();
        Valoracion valoracion = helper.consultarValoración(idValoración.getText().toString());
        helper.cerrar();
        if(valoracion == null)
            Toast.makeText(this, "Valoración con codigo " + idValoración.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            idTipoValoración.setText(String.valueOf(valoracion.getIdTipoValoración()));
            idAsignaciónLocal.setText(String.valueOf(valoracion.getIdAsignaciónLocal()));
            idPersona.setText(String.valueOf(valoracion.getIdPersona()));
            descripciónValoración.setText(valoracion.getDescripciónValoración());
        }
    }

    public void limpiarTexto(View v){
        idValoración.setText("");
        idTipoValoración.setText("");
        idAsignaciónLocal.setText("");
        idPersona.setText("");
        descripciónValoración.setText("");
    }

}
