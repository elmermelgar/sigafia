package work.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import work.tarea1.PrivetClass.TipoActividad;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.PrivetClass.asignacion;

/**
 * Created by fhmen on 27/04/2016.
 */
public class DataBaseHWork {


    //se crean los campos que seran las columnas de nuestra base,solo sirven para hacacer referencia
    private static final String[] camposAsignacion = new String[]
            {"cod_asignacion",
                    "actividadId",
                    "idLocal"};
    private static final String[] camposHorario = new String[]
            {"idHorario",
                    "horario"};
    private static final String[] camposValoración = new String[]
            {"idValoracion",
                    "idTipoValoracion",
                    "idAsignacionLocal",
                    "idPersona",
                    "descripcionValoracion"};
    private static final String[] camposTipoValoración = new String[]
            {"idTipoValoracion",
                    "tipoValoracion",
                    "descripcionTipoValoracion"};
    private static final String[] camposTipoActividad = new String[]
            {"idTipoActividad",
                    "tipoActividad"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


      public  DataBaseHWork (Context ctx) {
        this. context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public String insertar(asignacion asignacion) {
        long contador=0;
        String regInsertados="Registro Insertado Nº= " ;
        contador=db.insert("asignacion" , null, asignacion.toContentValues());
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción" ;
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    public asignacion consultarasignacion(String IdA) {
        String[] id = {IdA};
        Cursor cursor = db.query("asignacion" , camposAsignacion, "cod_asignacion = ?" , id,null, null, null);
        if(cursor.moveToFirst()){
            asignacion asignacion = new asignacion(cursor.getString(0),cursor.getString(1),cursor.getString(2));

        return asignacion;
    }else{
        return null;
    }

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea1.s3db" ;
        private static final int VERSION = 1;


                    public DatabaseHelper(Context context) {
                        super(context, BASE_DATOS, null, VERSION);
                    }



                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        try{
                            db.execSQL("CREATE TABLE 'asignacion' ('cod_asignacion' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'actividadId' INTEGER NOT NULL, 'idLocal' INTEGER);" );
                            db.execSQL("CREATE TABLE 'horario' ('idHorario' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'horaInicial' DATETIME NOT NULL, 'horaFinal' DATETIME NOT NULL);" );
                            db.execSQL("CREATE TABLE 'valoracion' ('idValoracion' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'idTipoValoracion' INTEGER NOT NULL, 'idAsignacionLocal' INTEGER NOT NULL, 'idPersona' INTERGER NOT NULL, 'descripcionValoracion' VARCHAR(50));" );
                            db.execSQL("CREATE TABLE 'tipoValoracion' ('idTipoValoracion' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'tipoValoracion' VARCHAR(20), 'descripcionTipoValoracion' VARCHAR(50));" );
                            db.execSQL("CREATE TABLE 'tipoActividad' ('idTipoActividad' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'tipoActividad' VARCHAR(50));" );
                        }catch(SQLException e){
                            e.printStackTrace();
                        }
                    }


                        @Override
                        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub
                        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }


    public String insertarValor(Valoracion valoración){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues valor = new ContentValues();
        valor.put("idValoracion", valoración.getIdValoración());
        valor.put("idTipoValoracion", valoración.getIdTipoValoración());
        valor.put("idPersona", valoración.getIdPersona());
        valor.put("idAsignacionLocal", valoración.getIdAsignaciónLocal());
        valor.put("descripcionValoracion", valoración.getDescripciónValoración());

        contador=db.insert("valoracion", null, valor);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertarTipoVal(TipoValoracion tipovaloración){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues tipovalor = new ContentValues();

        tipovalor.put("idTipoValoracion", tipovaloración.getIdTipoValoracion());
        tipovalor.put("tipoValoracion", tipovaloración.getTipoValoracion());
        tipovalor.put("descripcionTipoValoracion", tipovaloración.getDescripciónTipoValoracion());

        contador=db.insert("tipoValoracion", null, tipovalor);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertarTipoAct(TipoActividad tipoactividad){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues tipoact = new ContentValues();

        tipoact.put("idTipoActividad", tipoactividad.getIdTipoActividad());
        tipoact.put("tipoActividad", tipoactividad.getTipoActividad());

        contador=db.insert("tipoActividad", null, tipoact);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Valoracion consultarValoración(String idValoracion){
        String[] id = {idValoracion};
        Cursor cursor = db.query("valoracion", camposValoración, "idValoracion = ?", id, null, null, null, null);
        if(cursor.moveToFirst()){
            Valoracion valoracion = new Valoracion();
            valoracion.setIdValoración(cursor.getInt(0));
            valoracion.setIdTipoValoración(cursor.getInt(1));
            valoracion.setIdPersona(cursor.getInt(2));
            valoracion.setIdAsignaciónLocal(cursor.getInt(3));
            valoracion.setDescripciónValoración(cursor.getString(4));
            return valoracion;
        }else{ return null;
        }
    }

    public TipoValoracion consultarTipoVal(String idTipoValoracion){
        String[] id = {idTipoValoracion};
        Cursor cursor = db.query("tipoValoracion", camposTipoValoración, "idTipoValoracion = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoValoracion tipovaloracion = new TipoValoracion();
            tipovaloracion.setIdTipoValoracion(cursor.getInt(0));
            tipovaloracion.setTipoValoracion(cursor.getString(1));
            tipovaloracion.setDescripciónTipoValoracion(cursor.getString(2));

            return tipovaloracion;
        }else{ return null;
        }
    }

    public TipoActividad consultarTipoAct(String idTipoActividad){
        String[] id = {idTipoActividad};
        Cursor cursor = db.query("tipoActividad", camposTipoActividad, "idTipoActividad = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoActividad tipoactividad = new TipoActividad();
            tipoactividad.setIdTipoActividad(cursor.getInt(0));
            tipoactividad.setTipoActividad(cursor.getString(1));

            return tipoactividad;
        }else{ return null;
        }
    }

    private boolean verificarIntegridad(Object dato) throws SQLException{
        //verificar que exista la Valoracion
        Valoracion valoracion2 = (Valoracion)dato;
        String[] id = {String.valueOf(valoracion2.getIdValoración())};
        abrir();
        Cursor c2 = db.query("valoracion", null, "idValoracion = ?", id, null, null, null, null);
        if(c2.moveToFirst()){
            //Se encontro la Valoracion
            return true;
        }
        return false;
    }

    public String actualizarValoración(Valoracion valoracion){

        if(verificarIntegridad(valoracion)){
            String[] id = {String.valueOf(valoracion.getIdValoración())};
            ContentValues cv = new ContentValues();
            cv.put("idTipoValoracion", valoracion.getIdTipoValoración());
            cv.put("idAsignacionLocal", valoracion.getIdAsignaciónLocal());
            cv.put("idPersona", valoracion.getIdPersona());
            cv.put("descripcionValoracion", valoracion.getDescripciónValoración());
            db.update("valoracion", cv, "idValoracion = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con idValoración " + valoracion.getIdValoración() + " no existe";
        }
    }

    private boolean verificarIntegridadTV(Object dato) throws SQLException{
        //verificar que exista el Tipo de Valoracion
        TipoValoracion tipovaloracion2 = (TipoValoracion)dato;
        String[] id = {String.valueOf(tipovaloracion2.getIdTipoValoracion())};
        abrir();
        Cursor c2 = db.query("tipoValoracion", null, "idTipoValoracion = ?", id, null, null, null, null);
        if(c2.moveToFirst()){
            //Se encontro el tipo de Valoracion
            return true;
        }
        return false;
    }

    public String actualizarTipoVal(TipoValoracion tipovaloracion){

        if(verificarIntegridadTV(tipovaloracion)){
            String[] id = {String.valueOf(tipovaloracion.getIdTipoValoracion())};

            ContentValues cv = new ContentValues();

            cv.put("tipoValoracion", tipovaloracion.getTipoValoracion());
            cv.put("descripcionTipoValoracion", tipovaloracion.getDescripciónTipoValoracion());

            db.update("tipoValoracion", cv, "idTipoValoracion = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con idTipoValoración " + tipovaloracion.getIdTipoValoracion() + " no existe";
        }
    }

    private boolean verificarIntegridadTA(Object dato) throws SQLException{
        //verificar que exista el Tipo de Actividad
        TipoActividad tipoactividad2 = (TipoActividad)dato;
        String[] id = {String.valueOf(tipoactividad2.getIdTipoActividad())};
        abrir();
        Cursor c2 = db.query("tipoActividad", null, "idTipoActividad = ?", id, null, null, null);
        if(c2.moveToFirst()){
            //Se encontro el Tipo de Actividad
            return true;
        }
        return false;
    }

    public String actualizarTipoAct(TipoActividad tipoactividad){

        if(verificarIntegridadTA(tipoactividad)){
            String[] id = {String.valueOf(tipoactividad.getIdTipoActividad())};

            ContentValues cv = new ContentValues();

            cv.put("tipoActividad", tipoactividad.getTipoActividad());

            db.update("tipoActividad", cv, "idTipoActividad = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con idTipoActividad " + tipoactividad.getIdTipoActividad() + " no existe";
        }
    }

    public String eliminarValoración(Valoracion valoracion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(valoracion)) {
            contador+=db.delete("valoracion", "idValoracion='"+valoracion.getIdValoración()+"'", null);
        }
        contador+=db.delete("valoracion", "idValoracion='"+valoracion.getIdValoración()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminarTipoVal(TipoValoracion tipovaloracion){
        String regAfectados="Registros Afectados= ";
        int contador=0;
        if (verificarIntegridadTV(tipovaloracion)) {
            contador+=db.delete("tipoValoracion", "idTipoValoracion='"+tipovaloracion.getIdTipoValoracion()+"'", null);
        }
        contador+=db.delete("tipoValoracion", "idTipoValoracion='"+tipovaloracion.getIdTipoValoracion()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminarTipoAct(TipoActividad tipoactividad){
        String regAfectados="Registros Afectados= ";
        int contador=0;
        if (verificarIntegridadTA(tipoactividad)) {
            contador+=db.delete("tipoActividad", "idTipoActividad='"+tipoactividad.getIdTipoActividad()+"'", null);
        }
        contador+=db.delete("tipoActividad", "idTipoActividad='"+tipoactividad.getIdTipoActividad()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String llenarBDCarnet(){
        abrir();
        try {
            db.execSQL("DELETE FROM asignacion");
            db.execSQL("insert into asignacion (actividadId,idLocal) values (2,3)" );

        }catch (Exception e){
            Log.d("my",e.toString());
            return "Guardo Error";
        }


        cerrar();
        return "Guardo Correctamente";

    }

}
