<<<<<<< HEAD
package work.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

<<<<<<< HEAD
import work.tarea1.PrivetClass.TipoActividad;
import work.tarea1.PrivetClass.TipoValoracion;
import work.tarea1.PrivetClass.Valoracion;
import work.tarea1.PrivetClass.asignacion;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.tarea1.PrivetClass.*;
>>>>>>> master

/**
 * Created by fhmen on 27/04/2016.
 */
public class DataBaseHWork {


    //se crean los campos que seran las columnas de nuestra base,solo sirven para hacacer referencia
    private static final String[] camposAsignacion = new String[]
            {"cod_asignacion",
                    "actividadId",
                    "idLocal"};
   
                    
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

    private static final String[] camposPersona = new String[]
            {"id_persona", "id_tipo_persona", "nombre", "apellido", "dui", "grado_academico", "genero", "email"};
    private static final String[] camposTipoPersona = new String[]
            {"codigo", "tipo_persona", "descripcion"};

    private static final String[] camposActividad = new String[]
            {"id_actividad", "id_tipo_actividad", "id_persona", "descripcion"};
    //private HashMap<String, String> spinnerTipoPersonaMap;
 private static final String[] camposHorario = new String[]
            {"IDHORARIO", "HORA_INICIO", "HORA_FIN"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DataBaseHWork(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public String insertarAsinacion(asignacion asignacion) {
        long contador = 0;
        String regInsertados = "Registro Insertado Nº= ";
        contador = db.insert("ASIGNACION_LOCALES", null, asignacion.toContentValues());
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    public asignacion consultarasignacion(String IdA) {
        String[] id = {IdA};
        Cursor cursor = db.query("ASIGNACION_LOCALES", camposAsignacion, "idAsignacionLocal = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            asignacion asignacion = new asignacion(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            return asignacion;
        } else {
            return null;
        }

    }

    public void setSpinnerTipoPersonaMap(HashMap<String, String> spinnerTipoPersonaMap) {
        this.spinnerTipoPersonaMap = spinnerTipoPersonaMap;
    }
*/
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //private static final String BASE_DATOS = "tarea1.s3db" ;
        private static final String BASE_DATOS = "tarea1.s3db";
        private static final int VERSION = 1;


        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE 'asignacion' ('cod_asignacion' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'actividadId' INTEGER NOT NULL, 'idLocal' INTEGER);");
                db.execSQL("CREATE TABLE 'horario' ('idHorario' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'horaInicial' DATETIME NOT NULL, 'horaFinal' DATETIME NOT NULL);");

<<<<<<< HEAD
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
=======
                //Tablas Persona y TipoPersona
                db.execSQL("CREATE TABLE 'Persona'('id_persona' VARCHAR(8) NOT NULL PRIMARY KEY,"
                        + "'id_tipo_persona' VARCHAR(10),'nombre' VARCHAR(80),'apellido' VARCHAR(80),'dui' VARCHAR(10),'grado_academico' VARCHAR(50)," +
                        "'genero' VARCHAR(1),'email' VARCHAR(50));");
                db.execSQL("CREATE TABLE 'TipoPersona'('codigo' VARCHAR(10) NOT NULL"
                        + " PRIMARY KEY,'tipo_persona' VARCHAR(30),'descripcion' VARCHAR(150));");
>>>>>>> master

                db.execSQL("CREATE TABLE 'Actividad'('id_actividad'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'id_tipo_actividad' INTEGER NOT NULL,'id_persona' VARCHAR(8) NOT NULL,'descripcion' VARCHAR(500));") ;

                db.execSQL("CREATE TABLE 'TipoActividad' ('id_tipo_actividad' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'tipo_actividad' VARCHAR(50) NOT NULL);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


<<<<<<< HEAD
    public void abrir() throws SQLException{
=======
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

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista TipoPersona
                TipoPersona tipoPersona = (TipoPersona) dato;
                String[] id = {tipoPersona.getCodigo()};
                abrir();
                Cursor c2 = db.query("TipoPersona", null, "codigo = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro TipoPersona
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista Persona
                Persona persona = (Persona) dato;
                String[] id = {persona.getIdPersona()};
                abrir();
                Cursor c2 = db.query("Persona", null, "id_persona = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro TipoPersona
                    return true;
                }
                return false;

            }
            case 3:{
                //verificar que exista Actividad
                Actividad actividad = (Actividad) dato;
                String value=actividad.getIdActividadString();
                String[] id = {value};
                abrir();
                Cursor c2 = db.query("Actividad", null, "id_actividad = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro Actividad
                    return true;
                }
                return false;
            }
            default:
                return false;
        }

    }

    //Insertar TipoPersona
    public String insertar(TipoPersona tipoPersona) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues tp = new ContentValues();

        tp.put("codigo", tipoPersona.getCodigo());
        tp.put("tipo_persona", tipoPersona.getTipoPersona());
        tp.put("descripcion", tipoPersona.getDescripcion());
        contador = db.insert("TipoPersona", null, tp);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    //Consultar TipoPersona
    public TipoPersona consultarTipoPersona(String codigo) {
        String[] id = {codigo};
        Cursor cursor = db.query("TipoPersona", camposTipoPersona, "codigo=?", id, null, null, null);
        if (cursor.moveToFirst()) {
            TipoPersona tipoPersona = new TipoPersona();
            tipoPersona.setCodigo(cursor.getString(0));
            tipoPersona.setTipoPersona(cursor.getString(1));
            tipoPersona.setDescripcion(cursor.getString(2));
            return tipoPersona;
        } else {
            return null;
        }
    }

    //Actualizar TipoPersona
    public String actualizar(TipoPersona tipoPersona) {
        if (verificarIntegridad(tipoPersona, 1)) {
            String[] id = {tipoPersona.getCodigo()};
            ContentValues cv = new ContentValues();
            cv.put("codigo", tipoPersona.getCodigo());
            cv.put("tipo_persona", tipoPersona.getTipoPersona());
            cv.put("descripcion", tipoPersona.getDescripcion());
            db.update("TipoPersona", cv, "codigo = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con carnet " + tipoPersona.getCodigo() + " no existe";
        }
    }

    //Eliminar TipoPersona
    public String eliminar(TipoPersona tipoPersona) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(tipoPersona, 1)) {
            contador += db.delete("TipoPersona", "codigo='" + tipoPersona.getCodigo() + "'", null);
        }

        regAfectados += contador;
        return regAfectados;
    }

    //Insertar Persona
    public String insertar(Persona persona) {


        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues alum = new ContentValues();
        alum.put("id_persona", persona.getIdPersona());
        alum.put("id_tipo_persona", persona.getIdTipoPersona());
        alum.put("nombre", persona.getNombre());
        alum.put("apellido", persona.getApellido());
        alum.put("dui", persona.getDui());
        alum.put("grado_academico", persona.getGradoAcademico());
        alum.put("genero", persona.getGenero());
        alum.put("email", persona.getEmail());
        contador = db.insert("Persona", null, alum);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    //actualizar Persona
    public String actualizar(Persona persona) {
        if (verificarIntegridad(persona, 2)) {
            String[] id = {persona.getIdPersona()};
            ContentValues cv = new ContentValues();
            cv.put("id_persona", persona.getIdPersona());
            cv.put("id_tipo_persona", persona.getIdTipoPersona());
            cv.put("nombre", persona.getNombre());
            cv.put("apellido", persona.getApellido());
            cv.put("dui", persona.getDui());
            cv.put("grado_academico", persona.getGradoAcademico());
            cv.put("genero", persona.getGenero());
            cv.put("email", persona.getEmail());
            db.update("Persona", cv, "id_persona = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con carnet " + persona.getIdPersona()+ " no existe";
        }

    }
    //Eliminar Persona
    public String eliminar(Persona persona) {

        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(persona, 2)) {
            contador += db.delete("Persona", "id_persona='" + persona.getIdPersona() + "'", null);
        }

        regAfectados += contador;
        return regAfectados;


    }
    //Consultar Persona
    public Persona consultarPersona(String idPersona) {
        String[] id = {idPersona};
        Cursor cursor = db.query("Persona", camposPersona, "id_persona = ?", id,
                null, null, null);
        if (cursor.moveToFirst()) {
            Persona persona = new Persona();
            persona.setIdPersona(cursor.getString(0));
            persona.setIdTipoPersona(cursor.getString(1));
            persona.setNombre(cursor.getString(2));
            persona.setApellido(cursor.getString(3));
            persona.setDui(cursor.getString(4));
            persona.setGradoAcademico(cursor.getString(5));
            persona.setGenero(cursor.getString(6));
            persona.setEmail(cursor.getString(7));

            return persona;
        } else {
            return null;
        }
    }
    //Insertar Actividad
    public String insertar(Actividad actividad) {


        String regInsertados = "Registro Insertado Nº= ";
        Integer idActividad=null;
        long contador = 0;
        ContentValues alum = new ContentValues();
        //alum.put("id_actividad", actividad.getIdActividad());
        alum.put("id_tipo_actividad",actividad.getIdTipoActividad());
        alum.put("id_persona",actividad.getIdPersona());
        alum.put("descripcion", actividad.getDescripcion());

        contador = db.insert("Actividad", null, alum);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {

            Cursor cursor=db.rawQuery("select * from Actividad where id_actividad=(select max(id_actividad) from Actividad)",null);
            if(cursor.moveToFirst())
               idActividad=cursor.getInt(0);

        }
        return  idActividad.toString() ;
    }

    //Consultar Actividad
    public Actividad consultarActividad(String idActividad) {
        String[] id = {idActividad};
        Cursor cursor = db.query("Actividad", camposActividad, "id_actividad = ?",id ,
                null, null, null);
        if (cursor.moveToFirst()) {
            Actividad actividad=new Actividad();
            actividad.setIdActividad(cursor.getInt(0));
            actividad.setIdTipoActividad(cursor.getInt(1));
            actividad.setIdPersona(cursor.getString(2));
            actividad.setDescripcion(cursor.getString(3));


            return actividad;
        } else {
            return null;
        }
    }
//Actualizar Actividad
    public String actualizar(Actividad actividad) {
        if (verificarIntegridad(actividad, 3)) {
            String[] id = {actividad.getIdActividadString()};
            ContentValues cv = new ContentValues();
            cv.put("id_actividad", actividad.getIdActividadString());
            cv.put("id_tipo_actividad", actividad.getIdTipoActividad());
            cv.put("id_persona", actividad.getIdPersona());
            cv.put("descripcion", actividad.getDescripcion());

            db.update("Actividad", cv, "id_actividad = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con id. " + actividad.getIdActividadString()+ " no existe";
        }

    }

    //Eliminar Actividad
    public String eliminar(Actividad actividad) {

        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(actividad, 3)) {
            contador += db.delete("Actividad", "id_actividad='" + actividad.getIdActividadString() + "'", null);
        }

        regAfectados += contador;
        return regAfectados;


    }

    public Integer getIdTabla(String tabla,String idLabel){
        Integer object=null;
        abrir();
        Cursor cursor=db.rawQuery("select * from '"+tabla+"' order by '"+idLabel+"' desc limit 1",null);
        if(cursor.moveToFirst()){
            object=cursor.getInt(0);
            System.out.println("id. tabla=" + object);

            return object;
        }


        cerrar();
        return null;
    }


    public ArrayAdapter<?> prepareSpinner(Context ctx, String table, String label, String value) {
        Class clase = label.getClass();
        abrir();
        Cursor cursor = db.rawQuery("select " + label + "," + value + " from " + table, null);
        List spinnerArray = new ArrayList<String>();


        if (cursor.moveToFirst()) {
            do {

                String lb = cursor.getString(0);


                spinnerArray.add(lb);

            } while (cursor.moveToNext());
            cursor.close();
            cerrar();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, spinnerArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }

    public Object getValueSelectedSpineer(String value,String label, String table, Boolean isValueString, Object filter) {

        abrir();
        if (isValueString == true) {
            Cursor cursor = db.rawQuery("select " + value + " from " + table + " where " + label + " like '" + filter + "'", null);

            if(cursor.moveToFirst())
                return cursor.getString(0);

        }
        else{
            Cursor cursor = db.rawQuery("select " + value + " from " + table + " where " + label + " like '" + filter + "'" , null);

            if(cursor.moveToFirst())
                return cursor.getInt(0);

        }
        cerrar();
        return null;
    }
=======
package work.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import work.tarea1.PrivetClass.Ciclo;
import work.tarea1.PrivetClass.DisponibilidadCiclo;
import work.tarea1.PrivetClass.asignacion;

/**
 * Created by fhmen on 27/04/2016.
 */
public class DataBaseHWork {


    //se crean los campos que seran las columnas de nuestra base,solo sirven para hacacer referencia
    private static final String[] camposAsignacion = new String[]{"cod_asignacion", "actividadId", "idLocal"};
    private static final String[] camposHorario = new String[]{"idHorario", "horario"};
    private static final String[] camposCiclo = new String []{"idciclo","ciclo"};
    private static final String[] camposDisponibilidadCiclo = new String []{"idDisponibilidad","idHorario","idLocal","idciclo","disponibilidad"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


      public DataBaseHWork(Context ctx) {
        this. context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea_v1.2.s3db" ;
        private static final int VERSION = 1;


        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE asignacion(cod_asignacion INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,actividadId INTEGER NOT NULL,idLocal INTEGER);" );
                db.execSQL("CREATE TABLE horario(idHorario INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, horaInicial DATETIME NOT NULL,horaFinal DATETIME NOT NULL);" );
                db.execSQL("CREATE TABLE ciclo(idciclo INTEGER PRIMARY KEY NOT NULL,ciclo INTEGER);");
                db.execSQL("CREATE TABLE disponibilidad_ciclo(idDisponibilidad INTEGER NOT NULL,idLocal INTEGER NOT NULL,idHorario INTEGER NOT NULL,idciclo INTEGER NOT NULL,disponibilidad Varchar(50),PRIMARY \n" +
                   "KEY(idDisponibilidad,idLocal,idHorario,idciclo));");

            }catch(SQLException e){
                e.printStackTrace();
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }
    public String insertar(Ciclo ciclo){
            String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues c = new ContentValues();
        c.put("idciclo",ciclo.getIdciclo());
        c.put("ciclo", ciclo.getCiclo());
        contador=db.insert("ciclo", null, c);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(DisponibilidadCiclo disponibilidadCiclo) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        if (verificarIntegridad(disponibilidadCiclo, 2)) {
            ContentValues disp = new ContentValues();
            disp.put("idDisponibilidad", disponibilidadCiclo.getDisponibilidad());
            disp.put("idHorario", disponibilidadCiclo.getIdHorario());
            disp.put("idciclo", disponibilidadCiclo.getIdciclo());
            disp.put("idLocal", disponibilidadCiclo.getIdLocal());
            disp.put("disponibilidad", disponibilidadCiclo.getDisponibilidad());
            contador = db.insert("disponibilidad_ciclo", null, disp);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
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

    public Ciclo consultarCiclo(String idCiclo){

        String[] id = {idCiclo};
        Cursor cursor = db.query("ciclo", camposCiclo, "idciclo = ?", id,null, null, null);
        if(cursor.moveToFirst()){
            Ciclo cl= new Ciclo();
            cl.setIdciclo(cursor.getInt(0));
            cl.setCiclo(cursor.getInt(1));

            return cl;
        }else{
            return null;
        }
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
    public DisponibilidadCiclo consultarDisponibilidad(String idDisponibilidad,String idHorario,String idLocal,String idciclo){
        String[] id = {idDisponibilidad, idHorario,idLocal,idciclo};
        Cursor cursor = db.query("disponibilidad_ciclo", camposDisponibilidadCiclo, "idDisponibilidad = ? AND idHorario = ? AND idLocal = ? AND idciclo = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DisponibilidadCiclo disp=new DisponibilidadCiclo();
            disp.setIdDisponibilidad(cursor.getInt(0));
            disp.setIdHorario(cursor.getInt(1));
            disp.setIdLocal(cursor.getInt(2));
            disp.setIdciclo(cursor.getInt(3));
            disp.setDisponibilidad(cursor.getString(4));
            return disp;
        }else{
            return null;
        }
    }
    public String eliminar(DisponibilidadCiclo dc){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="idDisponibilidad='"+String.valueOf(dc.getIdDisponibilidad())+"'";
        where = where + " AND idHorario='"+String.valueOf(dc.getIdHorario())+"'";
        where = where + " AND idLocal='"+String.valueOf(dc.getIdLocal())+"'";
        where=where+" AND idciclo="+String.valueOf(dc.getIdciclo());
        contador+=db.delete("disponibilidad_ciclo", where, null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String actualizar(DisponibilidadCiclo dc){

        if(verificarIntegridad(dc, 3)){
            String[] id = {String.valueOf(dc.getIdDisponibilidad()),String.valueOf(dc.getIdLocal()),String.valueOf(dc.getIdHorario()),String.valueOf(dc.getIdciclo())};
            ContentValues cv = new ContentValues();
            cv.put("disponibilidad",dc.getDisponibilidad());
            db.update("disponibilidad_ciclo", cv, "idDisponibilidad = ? AND idHorario = ? AND idLocal = ?  AND idciclo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String actualizar(Ciclo c){
        if(verificarIntegridad(c, 1)){
            String[] id = {String.valueOf(c.getIdciclo())};
            ContentValues cv = new ContentValues();
            cv.put("ciclo", c.getCiclo());
            db.update("ciclo", cv, "idciclo = ?", id);
            return "Registro Actualizado Correctamente";
        }else {
            return "Registro con codigo de ciclo " +String.valueOf(c.getIdciclo() )+ " no existe";
        }
    }

    public String eliminar(Ciclo c){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="idciclo='"+String.valueOf(c.getIdciclo())+"'";
        contador+=db.delete("ciclo", where, null);
        regAfectados+=contador;
        return regAfectados;
    }





    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:
            {
//verificar que exista Ciclo
               Ciclo ciclo2 = (Ciclo)dato;

                String[] idm = {String.valueOf(ciclo2.getIdciclo())};
                abrir();
                Cursor cm = db.query("ciclo", null, "idciclo = ?", idm, null,
                        null, null);
                if(cm.moveToFirst()){
//Se encontro ciclo
                    return true;
                }
                return false;
            }
            case 2:
            {
//verificar que al insertar registro DisponibibidadCiclo exista idhorario,idciclo
                DisponibilidadCiclo disponibilidadCiclo = (DisponibilidadCiclo)dato;
                String[] id1 = {String.valueOf(disponibilidadCiclo.getIdciclo())};
                String[] id2 = {String.valueOf(disponibilidadCiclo.getIdHorario())};

//abrir();
                Cursor cursor1 = db.query("ciclo", null, "idciclo = ?", id1, null,null, null);
                Cursor cursor2 = db.query("horario", null, "idHorario = ?", id2, null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            case 3:
            {
//verificar que al modificar nota exista id disponibilidad,id local, id horario  y el id ciclo
                DisponibilidadCiclo dc1 = (DisponibilidadCiclo)dato;
                String[] ids = {String.valueOf(dc1.getIdciclo()),String.valueOf(dc1.getIdLocal()),String.valueOf(dc1.getIdHorario()),String.valueOf(dc1.getIdciclo())};
                abrir();
                Cursor c = db.query("disponibilidad_ciclo", null, "idDisponibilidad= ?  AND idHorario = ? AND idLocal = ? AND idciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }

            public void cerrar(){
        DBHelper.close();

    }



    public String llenarBDCarnet(){

        abrir();
        db.execSQL("DELETE FROM asignacion");
        db.execSQL("DELETE FROM horario");
        db.execSQL("DELETE FROM ciclo");
       // db.execSQL("insert into asignacion (cod_asignacion,actividadId,idLocal) values (1,2,3)" );
        //db.execSQL("insert into horario(idHorario,horaInicial,horaFinal)values (1,'2007-01-01 10:00:00','2007-01-01 11:00:00')" );

        final int[] vidciclos ={1,2,3};
        final int[] VNciclos = {201601,201502,201401};

       Ciclo ciclo=new Ciclo();
        for (int i=0;i<3;i++){
            ciclo.setIdciclo(vidciclos[i]);
            ciclo.setCiclo(VNciclos[i]);
            insertar(ciclo);
        }
        //db.execSQL("insert into disponibilidad_ciclo(idDisponibilidad,idLocal,idHorario,idCiclo,disponibilidad) values(1,3,1,1,'disponible')");
        cerrar();
        return "Guardo Correctamente";

    }

>>>>>>> remotes/origin/otto
}
