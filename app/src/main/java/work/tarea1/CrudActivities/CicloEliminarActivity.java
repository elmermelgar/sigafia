package work.tarea1.CrudActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.PrivetClass.Ciclo;
import work.tarea1.DataBaseHWork;
import work.tarea1.R;

public class CicloEliminarActivity extends Activity {
    EditText  edtidciclo;
    EditText edtciclo;
    DataBaseHWork helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar);
        helper=new DataBaseHWork(this);
        edtidciclo=(EditText)findViewById(R.id.editidciclo);
    }
public void limpiar(){
    edtidciclo.setText("");
    edtciclo.setText("");

}
public void eliminarCiclo(View v){
    String regEliminadas;
    Ciclo c=new Ciclo();
    c.setIdciclo(Integer.valueOf(edtidciclo.getText().toString()));
    helper.abrir();
    regEliminadas=helper.eliminar(c);
    helper.cerrar();
    Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
}
}


