package work.tarea1.CrudActivities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

public class TipoValoracionInsertarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText edtIdTipoValoracion;
    EditText edtTipoValoracion;
    EditText edtDescripcionTipoValoracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_valoracion_insertar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode. setThreadPolicy(policy);

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
        limpiarTexto();
    }

    public void insertarTipoValoracionHost(View v){

        String url="";
        String idTipoValoracion=this.edtIdTipoValoracion.getText().toString();
        String tipoValoracion=this.edtTipoValoracion.getText().toString();
        String descripcionTipoValoracion=this.edtDescripcionTipoValoracion.getText().toString();


        url="http://grupo16pdm16.netne.net/ws_tipovaloracion_insertar.php?idtipovaloracion="+idTipoValoracion+"&tipovaloracion="+tipoValoracion+"&descripciontipovaloracion="+descripcionTipoValoracion+".";
        String urlX=url.replace(" ", "%20");
        ControladorServicio.insertarTipoValoracionPHP(urlX, this);
        limpiarTexto();

    }

    public void limpiarTexto() {
        edtIdTipoValoracion.setText("");
        edtTipoValoracion.setText("");
        edtDescripcionTipoValoracion.setText("");
    }

}
