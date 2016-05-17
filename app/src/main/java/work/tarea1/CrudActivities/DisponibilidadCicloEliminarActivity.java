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

public class DisponibilidadCicloEliminarActivity extends Activity {
DataBaseHWork helper;
    EditText edtiddisponibilidad;
    EditText edtidlocal;
    EditText edtidhorario;
    EditText edtidciclo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilidad_ciclo_eliminar);
        helper=new DataBaseHWork(this);
        edtiddisponibilidad=(EditText)findViewById(R.id.editiddisponibilidad);
       /* edtidlocal=(EditText)findViewById(R.id.editidlocal);
        edtidhorario=(EditText)findViewById(R.id.editidhorario);
        edtidciclo=(EditText)findViewById(R.id.editidciclo);*/

    }
    public void eliminarDisponibilidadCiclo(View v){
        String regEliminadas;
        DisponibilidadCiclo dc=new DisponibilidadCiclo();
        dc.setIdDisponibilidad(Integer.valueOf(edtiddisponibilidad.getText().toString()));
        /*dc.setIdLocal(edtidlocal.getText().toString());
        dc.setIdHorario(Integer.valueOf(edtidhorario.getText().toString()));
        dc.setIdciclo(Integer.valueOf(edtidciclo.getText().toString()));*/
        helper.abrir();
        regEliminadas=helper.eliminar(dc);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v){
        edtiddisponibilidad.setText("");
      /*  edtidlocal.setText("");
        edtidhorario.setText("");
        edtidciclo.setText("");*/
    }

}
