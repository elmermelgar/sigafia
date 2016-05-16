package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.horario;
import work.tarea1.R;

public class HorarioInsertar extends AppCompatActivity {

    private DataBaseHWork helper;
    private EditText idHorario;
    private EditText horarioInicio;
    private EditText horarioFinal;
    protected ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);


        helper = new DataBaseHWork(this);
        idHorario = (EditText) findViewById(R.id.idHorario);
        horarioInicio = (EditText) findViewById(R.id.horarioInicio);
        horarioFinal = (EditText) findViewById(R.id.horarioFinal);

        //codigo de spiner quemado con un string
       /* Spinner spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Games, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

    }

    public void insertarHorario(View view) {
        String regInsertados;

        horario horario = new horario(idHorario.getText().toString(), horarioInicio.getText().toString(), horarioFinal.getText().toString());
        helper.abrir();
        regInsertados = helper.insertarHorario(horario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }





    public void limpiarTexto(View v) {
        idHorario.setText("");
        horarioInicio.setText("");
        horarioFinal.setText("");
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horario_insertar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
