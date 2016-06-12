package work.tarea1.webService;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import work.tarea1.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class webAsignacionAcitivdad extends Activity {

    private String Resultado;
    private EditText resultadoWeb;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_asignacion_acitivdad);

        resultadoWeb=(EditText)findViewById(R.id.resultadoWeb);
        editText2   =(EditText)findViewById(R.id.editText2);

    }


    public void WebConsultaAct(View view) {

        HttpAsyncTask hola= new HttpAsyncTask();
                hola.execute("http://grupo16pdm16.netne.net/ws_resumenAsignacion.php");
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
String todos = "";

            try {

                String[] Filas = result.split("/-");
                for (String Fila : Filas) {

                    String[] campo = Fila.split("/;");

                         todos+="Id Asignacion :" + campo[0] +
                            "\nTipo Actividad :" + campo[1] +
                            "\nNombre :" + campo[2] + campo[3] +
                            "\nLocal :" + campo[4] +
                            "\nNum. Valoraciones:" + campo[5]+
                            "\n----------------------------------------------\n";
                }
                resultadoWeb.setText(todos);
            } catch (Exception e) {
                e.printStackTrace();
                resultadoWeb.setText("Error");
            }


        }

    }


}
