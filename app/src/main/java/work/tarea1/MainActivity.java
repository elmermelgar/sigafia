package work.tarea1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private ListView listViewTabla;
    DataBaseHWork BDhelper ;

    String[] menu = {"Tabla Tipo Persona","Tabla Persona","Tabla Actividad",
            "Tabla Tipo Actividad","Tabla Ciclo","Tabla Disponibilidad Ciclo","Tabla Valoracion",
            "Tabla Tipo Valoracion","Tabla Asignacion","Tabla Local","Tabla Horario","Llenar Base de Datos"};
    String[] activities = {null,"TipoPersonaMenuActivity","PersonaMenuActivity",
            "ActividadMenuActivity","tipoActividadMenu","CicloMenuActivity","DisponibilidadMenuActivity",
            "valoracionMenu","tipoValoracionMenu","asignacionMenu","localMenu","horarioMenu" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDhelper=new DataBaseHWork(this);
     /*  setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));


        BDhelper=new DataBaseHWork(this);

       ListView listView = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
       setListAdapter(adapter);*/
        Tabla tabla_data[] = new Tabla[]
                {

                        new Tabla(R.drawable.tp, "Tipo persona"),
                        new Tabla(R.drawable.personas, "Persona"),
                        new Tabla(R.drawable.actividad, "Actividad"),
                        new Tabla(R.drawable.ta, "Tipo actividad"),
                        new Tabla(R.drawable.ciclo, "Ciclo"),
                        new Tabla(R.drawable.disponibilidad, "Disponibilidad ciclo"),
                        new Tabla(R.drawable.valoracion, "Valoración"),
                        new Tabla(R.drawable.tv, "Tipo valoración"),
                        new Tabla(R.drawable.asignacion, "Asignación"),
                        new Tabla(R.drawable.local, "Local"),
                        new Tabla(R.drawable.horario, "Horario"),
                        new Tabla(R.drawable.parametros, "Llenar base de datos"),
                };

        TablaAdapter adapter = new TablaAdapter(this,
                R.layout.listview_item_row, tabla_data);


        listViewTabla = (ListView)findViewById(R.id.listViewTablas);

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listViewTabla.addHeaderView(header);

        listViewTabla.setAdapter(adapter);

        listViewTabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreValue=null;
                if (position != 12) {
                    if(activities[position]!=null) {
                        nombreValue = activities[position];
                        try {
                            Class<?> clase = Class.forName("work.tarea1." + nombreValue);
                            Intent inte = new Intent();
                            inte.setClass(view.getContext(), clase);
                            startActivity(inte);


                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                } else {          //CODIGO PARA LLENAR BASE DE DATOS

                    BDhelper.abrir();
                    String tost = BDhelper.llenarBD();
                    BDhelper.cerrar();
                    Toast.makeText(view.getContext(),tost, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


   /* @Override
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
    }*/
}
