package work.tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class logIng extends AppCompatActivity {
    DataBaseHWork helper;
    private EditText contrasenia;
    private EditText usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_ing);
        helper=new DataBaseHWork(this);

        contrasenia=(EditText)findViewById(R.id.contrasenia);
        usuario=    (EditText)findViewById(R.id.usuario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_ing, menu);
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

    public void ingresar(View view) {
        Boolean existe= false;

        helper.abrir();
        existe =helper.existeUser(usuario.getText().toString(),contrasenia.getText().toString());
        helper.cerrar();
        if(existe)
        {
            Toast.makeText(this, "Bienbenido : " + usuario.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MainActivity.class );
            startActivity(i);


        }
        else
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
    }




    public void limpiarTexto(View v) {
        usuario.setText("");
        contrasenia.setText("");

    }
}
