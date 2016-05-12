package work.tarea1.CrudActivities;

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
        String label = parent.getItemAtPosition(position).toString();
        String o=(String)helper.getValueSelectedSpineer("tipo_persona","TipoPersona",true,label);
        // Showing selected spinner item
       /* Toast.makeText(parent.getContext(), "You selected: " +o,
                Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void loadSpinnerData(){
        spTipoPersona.setAdapter(helper.prepareSpinner(this,"TipoPersona", "tipo_persona", "codigo"));
    }
}
