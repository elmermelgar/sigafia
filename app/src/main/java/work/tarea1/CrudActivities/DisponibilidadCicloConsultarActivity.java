package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.DisponibilidadCiclo;
import work.tarea1.R;

public class DisponibilidadCicloConsultarActivity extends Activity {
DataBaseHWork helper;
EditText edtiddisponibilidad;
EditText edtidhorario;
EditText edtidlocal;
EditText edtidciclo;
EditText edtdisponibilidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilidad_ciclo_consultar);
        helper=new DataBaseHWork(this);
        edtiddisponibilidad=(EditText)findViewById(R.id.edtiddisponibilidad);
        edtidhorario=(EditText)findViewById(R.id.edtidhorario);
        edtidlocal=(EditText)findViewById(R.id.edtidlocal);
        edtidciclo=(EditText)findViewById(R.id.edtidciclo);
        edtdisponibilidad=(EditText)findViewById(R.id.edtdisponibilidad);

    }
 public void consultarDisponibilidad(View v){
        helper.abrir();
        DisponibilidadCiclo disp=helper.consultarDisponibilidad(edtiddisponibilidad.getText().toString(),edtidhorario.getText().toString(),edtidlocal.getText().toString(),edtidciclo.getText().toString());
        helper.cerrar();
        if(disp == null){
            Toast.makeText(this, "Disponibilidad no registrada",
                    Toast.LENGTH_LONG).show();}
        else{
            edtdisponibilidad.setText(disp.getDisponibilidad());
        }
    }
public void limpiarTexto(View v){
    edtiddisponibilidad.setText("");
    edtidhorario.setText("");
    edtidlocal.setText("");
    edtidciclo.setText("");
    edtdisponibilidad.setText("");
}

}
