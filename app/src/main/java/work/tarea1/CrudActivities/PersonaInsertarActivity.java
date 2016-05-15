package work.tarea1.CrudActivities;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Persona;
import work.tarea1.R;

public class PersonaInsertarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DataBaseHWork helper;
    EditText editIdPersona;
    Spinner spTipoPersona;
    EditText editNombre;
    EditText editApellido;
    EditText editDui;
    EditText editGradoAcademico;
    EditText editGenero;
    EditText editEmail;
    String idTipoPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_insertar);
        helper=new DataBaseHWork(this);
        editIdPersona=(EditText)findViewById(R.id.editIdPersona);
        spTipoPersona=(Spinner)findViewById(R.id.spinner_tipo_persona);
        spTipoPersona.setOnItemSelectedListener(this);
        loadSpinnerData();
        editNombre=(EditText)findViewById(R.id.editNombre);
        editApellido=(EditText)findViewById(R.id.editApellido);
        editDui=(EditText)findViewById(R.id.editDui);
        editGradoAcademico=(EditText)findViewById(R.id.editGradoAcademico);
        editGenero=(EditText)findViewById(R.id.editGenero);
        editEmail=(EditText)findViewById(R.id.editEmail);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String filter = parent.getItemAtPosition(position).toString();
        idTipoPersona=(String)helper.getValueSelectedSpineer("codigo","tipo_persona","TipoPersona",true,filter);
        // Showing selected spinner item
        /*Toast.makeText(parent.getContext(), "You selected: " +idTipoPersona,
                Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
    public void insertarPersona(View v) {

        String idPersona=editIdPersona.getText().toString();

        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String dui=editDui.getText().toString();
        String idTipoPersona=this.idTipoPersona;
        String gradoAcademico=editGradoAcademico.getText().toString();
        String genero=editGenero.getText().toString();
        String email=editEmail.getText().toString();
        String regInsertados;



        Persona p=new Persona(idPersona,idTipoPersona,nombre,apellido,dui,gradoAcademico,genero,email);
        helper.abrir();
        regInsertados=helper.insertar(p);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editNombre.setText("");
        editApellido.setText("");
        editIdPersona.setText("");
        editGradoAcademico.setText("");
        editGenero.setText("");
        editEmail.setText("");
        editDui.setText("");

    }


    private void loadSpinnerData(){
        spTipoPersona.setAdapter(helper.prepareSpinner(this,"TipoPersona", "tipo_persona", "codigo"));
    }
}
