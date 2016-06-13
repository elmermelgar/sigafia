package work.tarea1.CrudActivities;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import work.tarea1.DataBaseHWork;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.R;
import work.tarea1.ws.ControladorServicio;

@SuppressLint("NewApi")
public class ValoracionConsultarHOSTActivity extends AppCompatActivity {

    DataBaseHWork helper;
    private EditText idPersona;
    ListView listaValoracionesView;
    private EditText resultadoWeb;
    static List<Valoracion> listaValoraciones;
    static List<String> descripcionValoraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion_consultar_host);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listaValoraciones = new ArrayList<Valoracion>();

        helper = new DataBaseHWork(this);
        idPersona = (EditText) findViewById(R.id.idPersona);
        resultadoWeb = (EditText) findViewById(R.id.resultadoWeb);
    }

    public void WebConsultaVal(View view) {

        HttpAsyncTask consulta= new HttpAsyncTask();
        consulta.execute("http://grupo16pdm16.netne.net/ws_valoraciones_consutarporpersona.php?idpersona=" + idPersona.getText().toString());
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Error ";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Datos recividos correctamente", Toast.LENGTH_LONG).show();
            // aqui ira la logica de la implementacion
            //     resultadoWeb.setText(result);
            if(result.indexOf("<")!=-1)
                result=result.substring(0,result.indexOf("<"));

            String todos = " ";

            try {

                String[] Filas = result.split("/-");
                for (String Fila : Filas) {

                    String[] campo = Fila.split("/;");

                    todos+= "Id Persona :" + campo[0] +
                            "\nLocal :" + campo[1] +
                            "\nTipo :" + campo[3] +
                            "\nDescripción :" + campo[2] +
                            "\n-----------------------------------------------------------\n";
                }
                resultadoWeb.setText(todos);
            } catch (Exception e) {
                e.printStackTrace();
                resultadoWeb.setText("Sin Coincidencias");
            }


        }

    }

    public void limpiarTexto(View v){
        idPersona.setText("");
        resultadoWeb.setText("");
    }


}
