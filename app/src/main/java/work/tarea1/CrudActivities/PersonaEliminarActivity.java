package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Persona;
import work.tarea1.R;

public class PersonaEliminarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText editIdPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_eliminar);
        helper=new DataBaseHWork(this);
        editIdPersona=(EditText)findViewById(R.id.editIdPersona);
    }

    public void eliminarPersona(View v){
        String regEliminadas;
        Persona persona=new Persona();

        persona.setIdPersona(editIdPersona.getText().toString());
        helper.abrir();
        regEliminadas=helper.eliminar(persona);
        helper.cerrar();
        Toast. makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
