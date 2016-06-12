package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.DisponibilidadCiclo;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

public class DisponibilidadCicloInsertarActivity extends Activity {
    EditText edtidisponibilidad;
    EditText edtidlocal;
    EditText edtidhorario;
    EditText edtidciclo;
    EditText edtdisponibilidad;
    DataBaseHWork helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilidad_ciclo_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode. setThreadPolicy(policy);
        edtidisponibilidad=(EditText)findViewById(R.id.editiddisp);
        edtidlocal=(EditText)findViewById(R.id.idLocal);
        edtidhorario=(EditText )findViewById(R.id.idhorario);
        edtidciclo=(EditText )findViewById(R.id.idciclo);
        edtdisponibilidad=(EditText )findViewById(R.id.disponibilidad);
        helper=new DataBaseHWork(this);
    }

    public void insertarDisponibilidad(View v) {
        String regInsertados;
        int iddisponibilidad=Integer.valueOf(edtidisponibilidad.getText().toString());
        int idhorario=Integer.valueOf(edtidhorario.getText().toString());
        String idlocal=edtidlocal.getText().toString();
        int idciclo=Integer.valueOf(edtidciclo.getText().toString());
        String disponibilidad=edtdisponibilidad.getText().toString();
        DisponibilidadCiclo disp=new DisponibilidadCiclo();
        disp.setIdDisponibilidad(iddisponibilidad);
        disp.setIdHorario(idhorario);
        disp.setIdLocal(idlocal);
        disp.setIdciclo(idciclo);
        disp.setDisponibilidad(disponibilidad);
        helper.abrir();
        regInsertados=helper.insertar(disp);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void insertarDisponibilidadHost(View v){
        String url="";
        int iddisponibilidad=Integer.valueOf(edtidisponibilidad.getText().toString());
        int idhorario=Integer.valueOf(edtidhorario.getText().toString());
        String idlocal=edtidlocal.getText().toString();
        int idciclo=Integer.valueOf(edtidciclo.getText().toString());
        String disponibilidad=edtdisponibilidad.getText().toString();

        url="http://grupo16pdm16.netne.net/ws_disponibilidad_ciclo_insertar.php?iddisponibilidad="+iddisponibilidad+"&idlocal="+ idlocal +"&idciclo="+idciclo+"&idhorario="+idhorario+"&disponibilidad="+disponibilidad+".";
        ControladorServicio.insertarDisponibilidadPHP(url, this);
    }
    public void limpiarTexto(View v) {
        edtidisponibilidad.setText("");
        edtidlocal.setText("");
        edtidhorario.setText("");
        edtidciclo.setText("");
        edtdisponibilidad.setText("");
    }
}
