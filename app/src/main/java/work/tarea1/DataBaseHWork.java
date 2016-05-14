package work.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.tarea1.PrivetClass.*;

/**
 * Created by fhmen on 27/04/2016.
 */
public class DataBaseHWork {


    //variable para recuperar el id de actividad y setear el widget


    //se crean los campos que seran las columnas de nuestra base,solo sirven para hacacer referencia
    private static final String[] camposAsignacion = new String[]
            {"cod_asignacion",
                    "actividadId",
                    "idLocal"};
    private static final String[] camposHorario = new String[]
            {"idHorario",
                    "horario"};

    private static final String[] camposPersona = new String[]
            {"id_persona", "id_tipo_persona", "nombre", "apellido", "dui", "grado_academico", "genero", "email"};
    private static final String[] camposTipoPersona = new String[]
            {"codigo", "tipo_persona", "descripcion"};

    private static final String[] camposActividad = new String[]
            {"id_actividad", "id_tipo_actividad", "id_persona", "descripcion"};
    //private HashMap<String, String> spinnerTipoPersonaMap;

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DataBaseHWork(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public String insertar(asignacion asignacion) {
        long contador = 0;
        String regInsertados = "Registro Insertado Nº= ";
        contador = db.insert("asignacion", null, asignacion.toContentValues());
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    public asignacion consultarasignacion(String IdA) {
        String[] id = {IdA};
        Cursor cursor = db.query("asignacion", camposAsignacion, "cod_asignacion = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            asignacion asignacion = new asignacion(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            return asignacion;
        } else {
            return null;
        }

    }
/*
    public HashMap<String, String> getSpinnerTipoPersonaMap() {
        return spinnerTipoPersonaMap;
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

                //Tablas Persona y TipoPersona
                db.execSQL("CREATE TABLE 'Persona'('id_persona' VARCHAR(8) NOT NULL PRIMARY KEY,"
                        + "'id_tipo_persona' VARCHAR(10),'nombre' VARCHAR(80),'apellido' VARCHAR(80),'dui' VARCHAR(10),'grado_academico' VARCHAR(50)," +
                        "'genero' VARCHAR(1),'email' VARCHAR(50));");
                db.execSQL("CREATE TABLE 'TipoPersona'('codigo' VARCHAR(10) NOT NULL"
                        + " PRIMARY KEY,'tipo_persona' VARCHAR(30),'descripcion' VARCHAR(150));");

                db.execSQL("CREATE TABLE 'Actividad'('id_actividad'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'id_tipo_actividad' INTEGER NOT NULL,'id_persona' VARCHAR(8) NOT NULL,'descripcion' VARCHAR(500));") ;

                db.execSQL("CREATE TABLE 'TipoActividad' ('id_tipo_actividad' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'tipo_actividad' VARCHAR(50) NOT NULL);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }


    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }


    public void cerrar() {
        DBHelper.close();
    }


    public String llenarBDCarnet() {
        abrir();
        try {
            db.execSQL("DELETE FROM asignacion");

            db.execSQL("insert into asignacion (actividadId,idLocal) values (2,3)");

            //Insersion tabla TipoPersona
            db.execSQL("DELETE FROM TipoPersona");
            db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('d115','Docente','Docente') ");
            db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('e115','Estudiante','Estudiante') ");
            //db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('x115','Externo','Externo') ");
            //Insersion tabla Persona
            db.execSQL("DELETE FROM Persona");
            db.execSQL("INSERT INTO Persona(id_persona,id_tipo_persona,nombre,apellido,dui,grado_academico,genero,email) values('lr12003','e115','David','Lopez','049804221','bachiller','M','daviddust21@gmail.com') ");
            db.execSQL("INSERT INTO Persona(id_persona,id_tipo_persona,nombre,apellido,dui,grado_academico,genero,email) values('cm98001','d115','Cesar','Milan','049804221','ingeniero','M','cesar.milan@gmail.com') ");

            db.execSQL("DELETE FROM TipoActividad");
            db.execSQL("INSERT INTO TipoActividad(tipo_actividad) values('Clase') ");
            db.execSQL("INSERT INTO TipoActividad(tipo_actividad) values('Discusión') ");
            db.execSQL("INSERT INTO TipoActividad(tipo_actividad) values('Conferencia') ");

        } catch (Exception e) {
            Log.d("my", e.toString());
            return "Error al guardar";
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
}