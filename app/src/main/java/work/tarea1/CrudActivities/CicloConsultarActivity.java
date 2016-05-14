package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.PrivetClass.Ciclo;
import work.tarea1.DataBaseHWork;
import work.tarea1.R;

public class CicloConsultarActivity extends Activity {
    EditText edtidciclo;
    EditText edtciclo;
    DataBaseHWork helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new DataBaseHWork(this);
        setContentView(R.layout.activity_ciclo_consultar);
        edtidciclo = (EditText) findViewById(R.id.edtidciclo);
        edtciclo = (EditText) findViewById(R.id.edtciclo);
    }

    public void consultarCiclo(View v) {
        helper.abrir();
        Ciclo cl = helper.consultarCiclo(edtidciclo.getText().toString());
        helper.cerrar();
        if (cl == null) {
            Toast.makeText(this, "Ciclo con id_ciclo " +
                    edtidciclo.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        } else {
            edtciclo.setText(String.valueOf(cl.getCiclo()));
        }
    }
    public void limpiarTexto(View v){
        edtidciclo.setText("");
        edtciclo.setText("");

    }
}
