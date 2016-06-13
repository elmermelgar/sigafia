package work.tarea1.CrudActivities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import work.tarea1.PrivetClass.Actividad;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;


@SuppressLint("NewApi")
public class ActividadFilterFechaActivity extends AppCompatActivity {

    EditText edtFecha;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_filter_fecha);
        edtFecha=(EditText)findViewById(R.id.fecha);
    }

    public void consultarActividad(View v){

        String url="";
        List<Actividad> actividades;

        String fecha=edtFecha.getText().toString();

        url="http://grupo16pdm16.netne.net/ws_actividad_count_fecha.php?fecha="+fecha;
        actividades=ControladorServicio.getActividadFechaPHP(url, this);



    }


}
