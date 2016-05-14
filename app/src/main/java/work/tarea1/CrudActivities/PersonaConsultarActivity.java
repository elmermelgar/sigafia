package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Persona;
import work.tarea1.R;

public class PersonaConsultarActivity extends AppCompatActivity {

    DataBaseHWork helper;
    EditText editIdPersona;
    EditText valueIdPersona;
    EditText editIdTipoPersona;
    EditText editNombre;
    EditText editApellido;
    EditText editDui;
    EditText editGradoAcademico;
    EditText editGenero;
    EditText editEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_consultar);
        helper=new DataBaseHWork(this);
        valueIdPersona=(EditText)findViewById(R.id.valueIdPersona);
        editIdPersona=(EditText)findViewById(R.id.editIdPersona);
        editIdTipoPersona=(EditText)findViewById(R.id.editIdTipoPersona);
        editNombre=(EditText)findViewById(R.id.editNombre);
        editApellido=(EditText)findViewById(R.id.editApellido);
        editDui=(EditText)findViewById(R.id.editDui);
        editGradoAcademico=(EditText)findViewById(R.id.editGradoAcademico);
        editGenero=(EditText)findViewById(R.id.editGenero);
        editEmail=(EditText)findViewById(R.id.editEmail);
    }

    public void consultarPersona(View v){
        helper.abrir();
        Persona p =helper.consultarPersona(valueIdPersona.getText().toString());
        helper.cerrar();
        if(p == null)
            Toast. makeText(this, "Persona con codigo " + valueIdPersona.getText().toString() + " no encontrada", Toast.LENGTH_LONG).show();
        else{

            editIdPersona.setText(p.getIdPersona());
            editIdTipoPersona.setText(p.getIdTipoPersona());
            editNombre.setText(p.getNombre());
            editApellido.setText(p.getApellido());
            editDui.setText(p.getDui());
            editGradoAcademico.setText(p.getGradoAcademico());
            editGenero.setText(p.getGenero());
            editEmail.setText(p.getEmail());

        }

    }
    public void limpiarTexto(View v) {
        editNombre.setText("");
        editApellido.setText("");
        editIdPersona.setText("");
        editIdTipoPersona.setText("");
        editGradoAcademico.setText("");
        editGenero.setText("");
        editEmail.setText("");
        editDui.setText("");

    }
}
