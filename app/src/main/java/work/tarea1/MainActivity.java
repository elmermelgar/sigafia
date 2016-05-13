package work.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] menu = {"Tabla Asignacion", "Tabla horario", "Tabla Local", "crear Asignacion y horario"};
    String[] activities = {"asignacionMenu", "horarioMenu", "localMenu"};
    DataBaseHWork BDhelper ;

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
        if (position != 3) {
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