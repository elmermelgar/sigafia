package work.tarea1.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import work.tarea1.PrivetClass.Actividad;

/**
 * Created by David-PC on 9/6/2016.
 */
public class ControladorServicio {

    private static String peticion;
    private static Context ctx;

    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = "";
        // Estableciendo tiempo de espera del servicio

        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams. setConnectionTimeout(parametros, 3000);
        HttpConnectionParams. setSoTimeout(parametros, 5000);
        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);

            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();

                respuesta = EntityUtils. toString(entidad, HTTP.UTF_8);
               // System.out.println("Respuesta:"+respuesta);
            }
        } catch (Exception e) {
            Toast. makeText(ctx, "Error en la conexion", Toast. LENGTH_LONG).show();
            // Desplegando el error en el LogCat
            Log. v("Error de Conexion", e.toString());
        }
        return respuesta;
    }
    public static String obtenerRespuestaPost(String url, JSONObject obj,
                                              Context ctx) {
        String respuesta = " ";
        try {
            HttpParams parametros = new BasicHttpParams();
            HttpConnectionParams. setConnectionTimeout(parametros, 10000);
            HttpConnectionParams. setSoTimeout(parametros, 50000);
            HttpClient cliente = new DefaultHttpClient(parametros);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type", "application/json");
            StringEntity nuevaEntidad = new StringEntity(obj.toString());
            httpPost.setEntity(nuevaEntidad);
            Log. v("Peticion",url);
            Log. v("POST", httpPost.toString());
            HttpResponse httpRespuesta = cliente.execute(httpPost);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                respuesta = Integer. toString(codigoEstado);
                Log. v("respuesta",respuesta);
            }
            else{
                Log. v("respuesta",Integer. toString(codigoEstado));
            }
        } catch (Exception e) {
            Toast. makeText(ctx, "Error en la conexion", Toast. LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log. v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static void insertarActividadPHP(String peticion, Context ctx)  {
        String json = obtenerRespuestaPeticion(peticion, ctx);

        //JSONParser parser_obj = new JSONParser();



        //resultado = (JSONObject)parser_obj.parse(json.length()>0?json.substring(json.indexOf("{"), json.lastIndexOf("}")+1):"Error");
        //resultado = (JSONObject)parser_obj.parse(json.substring(1,14));



        try {
            JSONObject resultado = new JSONObject(json.substring(0,json.lastIndexOf("}")+1));
            int respuesta = resultado.getInt("resultado");

            if (respuesta == 1)
                Toast. makeText(ctx, "Registro ingresado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error registro duplicado",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


     /*   try {
            //Toast. makeText(ctx, "Registro ingresado", Toast. LENGTH_LONG).show();

            JSONObject resultado;

            //resultado = (JSONObject)parser_obj.parse(json.length()>0?json.substring(json.indexOf("{"), json.lastIndexOf("}")+1):"Error");
            //resultado = (JSONObject)parser_obj.parse(json.substring(1,14));

            resultado =new JSONObject(json.substring(1,14));
            int respuesta = resultado.getInt("resultado");

            if (respuesta == 1)
                Toast. makeText(ctx, "Registro ingresado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error registro duplicado",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    public static void deleteActividadPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);


        try {
            Toast. makeText(ctx, "Registro eliminado",
                    Toast. LENGTH_LONG).show();
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1)
                Toast. makeText(ctx, "Registro eliminado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error al eliminar registro",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static List<Actividad> getActividadFechaPHP(String peticion,Context ctx){


        //JSONParser parser_obj = new JSONParser();
        String json=obtenerRespuestaPeticion(peticion,ctx);
        //System.out.println("JSON:"+json);
        Toast. makeText(ctx,json.substring(0,json.lastIndexOf("]")+1) , Toast. LENGTH_LONG).show();
        List<Actividad> listaActividades = new ArrayList<Actividad>();
        try {

            JSONObject jsonObject = new JSONObject(json.substring(0,json.lastIndexOf("]")+1));

            JSONArray actividadJSON =jsonObject.toJSONArray(new JSONArray(jsonObject));
            //JSONObject actividadJObject =new JSONObject(json.substring(0,json.lastIndexOf("]")+1));



            for (int i = 0; i < actividadJSON.length(); i++) {
                JSONObject obj = actividadJSON.getJSONObject(i);
                Actividad actividad=new Actividad();
                actividad.setIdActividad(obj.getInt("idactividad"));
                actividad.setIdTipoActividad(obj.getInt("idtipoactividad"));
                actividad.setIdPersona(obj.getString("idpersona"));
                actividad.setDescripcion(obj.getString("descripcion"));
                actividad.setFecha(obj.getString("fecha"));

                listaActividades.add(actividad);
            }
            return listaActividades;
        } catch (Exception e) {
            Toast. makeText(ctx, "Error en parseo de JSON", Toast. LENGTH_LONG).show();
            return null;
        }
    }


}
