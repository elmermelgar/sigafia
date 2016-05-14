package work.tarea1.CrudActivities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.DisponibilidadCiclo;
import work.tarea1.R;

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
        int idlocal=Integer.valueOf(edtidlocal.getText().toString());
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
    public void limpiarTexto(View v) {
        edtidisponibilidad.setText("");
         edtidlocal.setText("");
        edtidhorario.setText("");
        edtidciclo.setText("");
        edtdisponibilidad.setText("");
    }
}
