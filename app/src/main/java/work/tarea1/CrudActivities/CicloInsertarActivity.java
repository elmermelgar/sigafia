package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.PrivetClass.Ciclo;
import work.tarea1.R;

public class CicloInsertarActivity extends Activity {
    DataBaseHWork helper;
    EditText edtidciclo;
    EditText edtciclo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        edtidciclo=(EditText)findViewById(R.id.editIdCiclo);
        edtciclo=(EditText)findViewById(R.id.editCiclo);


    }
    public void insertarCiclo(View v) {
        String regInsertados;
        Integer idciclo=Integer.valueOf(edtciclo.getText().toString());
        Integer ciclo=Integer.valueOf(edtciclo.getText().toString());
        Ciclo c=new Ciclo();
        c.setIdciclo(idciclo);
        c.setCiclo(ciclo);
        helper.abrir();
        regInsertados=helper.insertar(c);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        edtidciclo.setText("");
        edtciclo.setText("");


    }
}
