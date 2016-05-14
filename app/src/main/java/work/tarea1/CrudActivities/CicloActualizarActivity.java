package work.tarea1.CrudActivities;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.PrivetClass.Ciclo;
import work.tarea1.DataBaseHWork;
import work.tarea1.R;

public class CicloActualizarActivity extends Activity {
DataBaseHWork helper;

    EditText edtciclo;
    EditText edtidciclo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new DataBaseHWork(this);
        edtidciclo=(EditText)findViewById(R.id.editIdCiclo);
        edtciclo=(EditText)findViewById(R.id.editCiclo);

    }

    public void actualizarCiclo(View v) {
        Ciclo c=new Ciclo();
        c.setIdciclo(Integer.valueOf(edtidciclo.getText().toString()));
        c.setCiclo(Integer.valueOf(edtciclo.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(c);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        edtidciclo.setText("");
        edtciclo.setText("");

    }
}
