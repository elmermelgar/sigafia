package work.tarea1.CrudActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.horario;
import work.tarea1.R;

public class HorarioConsultar extends AppCompatActivity {

    private DataBaseHWork helper;
    private EditText idHorario;
    private EditText horarioInicio;
    private EditText horarioFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_consultar);


        helper = new DataBaseHWork(this);
        idHorario = (EditText) findViewById(R.id.idHorario);
        horarioInicio = (EditText) findViewById(R.id.horarioInicio);
        horarioFinal = (EditText) findViewById(R.id.horarioFinal);
//        Actividad = (TextView) findViewById(R.id.Actividad);
//        Local = (TextView) findViewById(R.id.Local);
    }


    public void consultarHorario(View view) {
        helper.abrir();
        horario horario =
                helper.consultarHorario(idHorario.getText().toString());
        helper.cerrar();
        if (horario == null)
            Toast.makeText(this, "Horario : " +
                    idHorario.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else {
            idHorario.setText(horario.getIdHorario());
            horarioInicio.setText(horario.getHoraInicio());
            horarioFinal.setText(horario.getHoraFinal());
        }
    }














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horario_consultar, menu);
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
