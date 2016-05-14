package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;

public class InsertarValoracionActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText idValoración;
    EditText idTipoValoración;
    EditText idAsignaciónLocal;
    EditText idPersona;
    EditText descripciónValoración;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_valoracion);

        helper = new DataBaseHWork(this);
        idValoración= (EditText) findViewById(R.id.idValoración);
        idTipoValoración = (EditText) findViewById(R.id.idTipoValoración);
        idAsignaciónLocal = (EditText) findViewById(R.id.idAsignacionLocal);
        idPersona = (EditText) findViewById(R.id.idPersona);
        descripciónValoración = (EditText) findViewById(R.id.descripciónValoración);
    }

    public void insertarValoración(View v) {

        String idVal=idValoración.getText().toString();
        String idTipoVal=idTipoValoración.getText().toString();
        String idAsigLocal=idAsignaciónLocal.getText().toString();
        String idPer=idPersona.getText().toString();
        String descrip=descripciónValoración.getText().toString();
        String regInsertados;

        Valoracion valoracion = new Valoracion();
        valoracion.setIdValoración(Integer.parseInt(idVal));
        valoracion.setIdTipoValoración(Integer.parseInt(idTipoVal));
        valoracion.setIdAsignaciónLocal(Integer.parseInt(idAsigLocal));
        valoracion.setIdPersona(Integer.parseInt(idPer));
        valoracion.setDescripciónValoración(descrip);

        helper.abrir();
        regInsertados = helper.insertarValor(valoracion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        idValoración.setText("");
        idTipoValoración.setText("");
        idAsignaciónLocal.setText("");
        idPersona.setText("");
        descripciónValoración.setText("");
    }
}
