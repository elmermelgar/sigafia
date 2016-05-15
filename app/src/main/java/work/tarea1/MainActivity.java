package work.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {


    DataBaseHWork BDhelper ;

    String[] menu = {"Tabla Tipo Persona","Tabla Persona","Tabla Actividad"
            +",Tabla Tipo Actividad","Tabla Ciclo","Tabla Disponibilidad Ciclo","Tabla Valoracion"
            +",Tabla Tipo Valoracion","Tabla Asignacion","Tabla Local","Tabla Horario","Llenar Base de Datos"};
    String[] activities = {"TipoPersonaMenuActivity","PersonaMenuActivity"
            +",ActividadMenuActivity","tipoActividadMenu","CicloMenuActivity","DisponibilidadMenuActivity"
            +",valoracionMenu","tipoValoracionMenu","asignacionMenu","localMenu","horarioMenu" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));


        BDhelper=new DataBaseHWork(this);

        ListView listView = getListView();
//        listView.setBackgroundColor(Color.GREEN);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);



    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i("22", "PRUEBA LOFG");
        if (position != 11) {
            String nombreValue = activities[position];
            try {
                Class<?> clase = Class.forName("work.tarea1." + nombreValue);
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {          //CODIGO PARA LLENAR BASE DE DATOS

            BDhelper.abrir();
            String tost = BDhelper.llenarBD();
            BDhelper.cerrar();
            Toast.makeText(this,tost, Toast.LENGTH_SHORT).show();

        }
    }
}
