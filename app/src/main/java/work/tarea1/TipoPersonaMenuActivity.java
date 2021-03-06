package work.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import work.tarea1.R;

public class TipoPersonaMenuActivity extends ListActivity {

    String[] menu={"Insertar Tipo Persona" , "Consultar Tipo Persona" , "Actualizar Tipo Persona" ,
            "Eliminar Tipo Persona"};
    String[]
            activities={"TipoPersonaInsertarActivity" , "TipoPersonaConsultarActivity", "TipoPersonaActualizarActivity" ,
            "TipoPersonaEliminarActivity" };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter( new ArrayAdapter<String>( this, android.R.layout. simple_list_item_1,
                menu));
        ListView listView = getListView();
        //listView.setBackgroundColor(Color.CYAN);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout. simple_list_item_1, menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l,View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        //l.getChildAt(position).setBackgroundColor(Color.CYAN);
        try{
            Class<?> clase=Class. forName("work.tarea1.CrudActivities."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
