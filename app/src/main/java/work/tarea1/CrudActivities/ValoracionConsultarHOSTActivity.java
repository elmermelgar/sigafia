package work.tarea1.CrudActivities;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

@SuppressLint("NewApi")
public class ValoracionConsultarHOSTActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText idPersona;
    ListView listaValoracionesView;
    static List<Valoracion> listaValoraciones;
    static List<String> descripcionValoraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion_consultar_host);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listaValoraciones = new ArrayList<Valoracion>();

        helper = new DataBaseHWork(this);
        idPersona = (EditText) findViewById(R.id.idPersona);
        listaValoracionesView = (ListView) findViewById(R.id.listViewVal);
    }

    public void servicioPHP(View v) {
        String url="";
        JSONObject persona;
        url ="http://grupo16pdm16.netne.net/ws_valoraciones_consutarporpersona.php" + "?idpersona=" + idPersona;

        String valoracionesExternas ="";
        valoracionesExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);
        try {
            listaValoraciones.addAll(ControladorServicio.obtenerValoracionesExterno(valoracionesExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        for (int i = 0; i < listaValoraciones.size(); i++) {
            dato = listaValoraciones.get(i).getIdAsignaciónLocal() + " " + listaValoraciones.get(i).getDescripciónValoración();
            descripcionValoraciones.add(dato);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripcionValoraciones);
        listaValoracionesView.setAdapter(adaptador);
    }

}
