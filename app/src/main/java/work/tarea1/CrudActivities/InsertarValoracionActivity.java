package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

public class InsertarValoracionActivity extends Activity implements AdapterView.OnItemSelectedListener{

    DataBaseHWork helper;

    EditText idValoración;
    String   idTipoValoración;
    Spinner spTipoValoración;
    String idAsignacionLocal;
    Spinner spAsignaciónLocal;
    String idPersona;
    Spinner spPersona;
    EditText descripciónValoración;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_valoracion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode. setThreadPolicy(policy);

        helper = new DataBaseHWork(this);

        idValoración= (EditText) findViewById(R.id.idValoración);
        descripciónValoración = (EditText) findViewById(R.id.descripciónValoración);

        spTipoValoración=(Spinner)findViewById(R.id.spinner_tipo_valoracion);
        spAsignaciónLocal=(Spinner)findViewById(R.id.spinner_asignacion_local);
        spPersona=(Spinner)findViewById(R.id.spinner_persona);
        spTipoValoración.setOnItemSelectedListener(this);
        spAsignaciónLocal.setOnItemSelectedListener(this);
        spPersona.setOnItemSelectedListener(this);
        loadSpinnerData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_valoracion_insertar, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;

        if(spinner.getId() == R.id.spinner_tipo_valoracion) {
            String filter = parent.getItemAtPosition(position).toString();
            idTipoValoración = (String) helper.getValueSelectedSpineer("idTipoValoracion", "tipoValoracion", "tipoValoracion", true, filter);
        }
        else if(spinner.getId() == R.id.spinner_asignacion_local) {
            String filter = parent.getItemAtPosition(position).toString();
            idAsignacionLocal = (String) helper.getValueSelectedSpineer("IDASIGNACIONLOCAL", "ID_LOCAL", "ASIGNACION_LOCALES", true, filter);

        }
        else if(spinner.getId() == R.id.spinner_persona) {
            String filter = parent.getItemAtPosition(position).toString();
            idPersona = (String) helper.getValueSelectedSpineer("id_persona", "nombre", "Persona", true, filter);

        }
    }



    private void loadSpinnerData(){
        spTipoValoración.setAdapter(helper.prepareSpinner(this, "tipoValoracion", "tipoValoracion", "idTipoValoracion"));
        spAsignaciónLocal.setAdapter(helper.prepareSpinner(this, "ASIGNACION_LOCALES", "ID_LOCAL", "IDASIGNACIONLOCAL"));
        spPersona.setAdapter(helper.prepareSpinner(this, "Persona", "nombre", "id_persona"));
    }

    public void insertarValoración(View v) {

        String idVal=idValoración.getText().toString();
        String idTipoVal=this.idTipoValoración;
        String idAsigLocal=this.idAsignacionLocal;
        String idPer=this.idPersona;
        String descrip=descripciónValoración.getText().toString();
        String regInsertados;

        Valoracion valoracion = new Valoracion();
        valoracion.setIdValoración(Integer.parseInt(idVal));
        valoracion.setIdTipoValoración(Integer.parseInt(idTipoVal));
        valoracion.setIdAsignaciónLocal(Integer.parseInt(idAsigLocal));
        valoracion.setIdPersona(idPer);
        valoracion.setDescripciónValoración(descrip);

        helper.abrir();
        regInsertados = helper.insertarValor(valoracion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void insertarValoracionHost(View v){

        String url="";
        String idVal=idValoración.getText().toString();
        String idTipoVal=this.idTipoValoración;
        String idAsigLocal=this.idAsignacionLocal;
        String idPer=this.idPersona;
        String descrip=descripciónValoración.getText().toString();

        url="http://grupo16pdm16.netne.net/ws_valoracion_insertar.php?idvaloracion="+idVal+"&idasignacionlocal="+idAsigLocal+"&idpersona="+idPer+"&descripcionvaloracion="+descrip+"&idtipovaloracion="+idTipoVal;
        String urlX=url.replace(" ", "%20");
        ControladorServicio.insertarTipoValoracionPHP(urlX, this);
        limpiarTexto();

    }

    public void limpiarTexto() {
        idValoración.setText("");
//        idPersona.setText("");
        descripciónValoración.setText("");
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
