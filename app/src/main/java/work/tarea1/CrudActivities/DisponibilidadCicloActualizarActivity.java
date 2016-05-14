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

public class DisponibilidadCicloActualizarActivity extends Activity {
    DataBaseHWork helper;
    EditText edtiddisp;
    EditText edtidlocal;
    EditText edtidhorario;
    EditText edtidciclo;
    EditText edtdisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilidad_ciclo_actualizar);
        helper=new DataBaseHWork(this);
        edtiddisp=(EditText)findViewById(R.id.editiddisponibilidad);
        edtidlocal=(EditText)findViewById(R.id.editidlocal);
        edtidhorario=(EditText)findViewById(R.id.editidhorario);
        edtidciclo=(EditText)findViewById(R.id.editidciclo);
        edtdisp=(EditText)findViewById(R.id.editdisponibilidad);
    }
public void actualizarDisponibilidadCiclo(View v){
    DisponibilidadCiclo dc = new DisponibilidadCiclo();
    dc.setIdDisponibilidad(Integer.valueOf(edtiddisp.getText().toString()));
    dc.setIdLocal(Integer.valueOf(edtidlocal.getText().toString()));
    dc.setIdHorario(Integer.valueOf(edtidhorario.getText().toString()));
    dc.setIdciclo(Integer.valueOf(edtidciclo.getText().toString()));
    dc.setDisponibilidad(edtdisp.getText().toString());
    helper.abrir();
    String estado = helper.actualizar(dc);
    helper.cerrar();
    Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
}

public void limpiarTexto(View v){
    edtiddisp.setText("");
    edtidlocal.setText("");
    edtidhorario.setText("");
    edtidciclo.setText("");
    edtdisp.setText("");
}


}
