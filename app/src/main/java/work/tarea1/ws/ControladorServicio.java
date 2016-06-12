package work.tarea1.ws;

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

import java.io.InputStream;

/**
 * Created by David-PC on 9/6/2016.
 */
public class ControladorServicio {

    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        Log.v("URL", url);
        String respuesta = " ";
        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);
        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            Log.v("codigo de estado",String.valueOf(codigoEstado));
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
                Log.v("Respuesta",respuesta);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion",
                    Toast.LENGTH_LONG).show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
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
            Log.v("Peticion", url);
            Log.v("POST", httpPost.toString());
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
            Toast. makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log. v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static void insertarActividadPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);



        try {
            Toast. makeText(ctx, "Registro ingresado",
                    Toast. LENGTH_LONG).show();
            JSONObject resultado = new JSONObject(json);

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
    }

    public static void actualizarActividadPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        Log.v("json",json);
        try {
            Toast. makeText(ctx, "Registro actualizado en Host",
                    Toast. LENGTH_LONG).show();
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1)
                Toast. makeText(ctx, "Registro actualizado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error al actualizar el registro",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarAsignacionPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            Toast. makeText(ctx, "Registro eliminado en Host",
                    Toast. LENGTH_LONG).show();
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1)
                Toast. makeText(ctx, "Registro eliminado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error al eliminar el registro",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void insertarTipoValoracionPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);



        try {
            Toast. makeText(ctx, "Registro ingresado",
                    Toast. LENGTH_LONG).show();
            JSONObject resultado = new JSONObject(json);

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
    }
    public static void insertarDisponibilidadPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);



        try {
            Toast. makeText(ctx, "Registro ingresado",
                    Toast. LENGTH_LONG).show();
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
    }

    public static List<Valoracion> obtenerValoracionesExterno(String json, Context ctx)
    {
        List<Valoracion> listaValoraciones = new ArrayList<Valoracion>();
        try {
            JSONArray valoracionesJSON = new JSONArray(json);
            for (int i = 0; i < valoracionesJSON.length(); i++)
            {
                JSONObject obj = valoracionesJSON.getJSONObject(i);
                Valoracion valoracion = new Valoracion();
                valoracion.setIdAsignaciónLocal(obj.getInt("IDASIGNACIONLOCAL"));
                valoracion.setIdPersona(obj.getString("IDPERSONA"));
                valoracion.setDescripciónValoración("DESCRIPCION_VALORACION");
                listaValoraciones.add(valoracion);
            }
            return listaValoraciones;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static void actualizarDisponibilidadPHP(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);

        try {

            JSONObject resultado = new JSONObject(json.substring(0,json.lastIndexOf("}")+1));

            int respuesta = resultado.getInt("resultado");

            if (respuesta == 1)
                Toast. makeText(ctx, "Registro actualizado",
                        Toast. LENGTH_LONG).show();
            else
                Toast. makeText(ctx, "Error al actualizar el registro",
                        Toast. LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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


}
