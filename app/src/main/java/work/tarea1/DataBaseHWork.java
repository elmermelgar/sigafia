package work.tarea1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import work.tarea1.PrivetClass.asignacion;
import work.tarea1.PrivetClass.horario;
import work.tarea1.PrivetClass.local;

/**
 * Created by fhmen on 27/04/2016.
 */
public class DataBaseHWork {


    //se crean los campos que seran las columnas de nuestra base,solo sirven para hacacer referencia
    private static final String[] camposAsignacion = new String[]
            {"idAsignacionLocal", "IdActividad", "ID_local"};
    private static final String[] camposHorario = new String[]
            {"IDHORARIO", "HORA_INICIO", "HORA_FIN"};
    private static final String[] camposLocal = new String[]
            {"ID_LOCAL", "DIRECCION", "CAPACIDAD"};

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

    public String borrarAsignacion(String s) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //   if (verificarIntegridad(alumno,3)) {
        contador += db.delete("ASIGNACION_LOCALES", "IDASIGNACIONLOCAL='" + s + "'", null);
        //   }
        //  contador+=db.delete( "alumno" , "carnet='" +alumno.getCarnet()+"'", null);
        regAfectados += contador;
        return regAfectados;


    }


    public String actualizarAsignacion(asignacion asignacion) {
        long contador = 0;
        String[] id = {asignacion.getIdAsignacionLocal()};// OJO con las Llaves para poder se String []
        String regInsertados = "Registro Actualizados: ";
        contador = db.update("ASIGNACION_LOCALES", asignacion.toContentValues(), "IDASIGNACIONLOCAL = ?", id);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Actualizar, \"id\" no encontrado";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    public String insertarHorario(horario horario) {
        long contador = 0;
        String regInsertados = "Registro Insertado Nº= ";
        contador = db.insert("HORARIO", null, horario.toContentValues());
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    public horario consultarHorario(String IdA) {
        String[] id = {IdA};// OJO con las Llaves para poder se String []
        Cursor cursor = db.query("HORARIO", camposHorario, "IDHORARIO = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            horario horario = new horario(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            return horario;
        } else {
            return null;
        }
    }

    public String actualizarHorario(horario horario) {
        long contador = 0;
        String[] id = {horario.getIdHorario()};// OJO con las Llaves para poder se String []
        String regInsertados = "Registro Actualizados: ";
        contador = db.update("HORARIO", horario.toContentValues(), "IDHORARIO = ?", id);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Actualizar, \"id\" no encontrado";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String borrarHorario(String s) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //   if (verificarIntegridad(alumno,3)) {
        contador += db.delete("HORARIO", "IDHORARIO='" + s + "'", null);
        //   }
        //  contador+=db.delete( "alumno" , "carnet='" +alumno.getCarnet()+"'", null);
        regAfectados += contador;
        return regAfectados;
    }

    public String insertarLocal(local local) {
        long contador = 0;
        String regInsertados = "Registro Insertado Nº= ";
        contador = db.insert("LOCAL", null, local.toContentValues());
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public local consultarLocal(String IdA) {
        String[] id = {IdA};// OJO con las Llaves para poder se String []
        Cursor cursor = db.query("LOCAL", camposLocal, "ID_LOCAL= ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            local local = new local(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            return local;
        } else {
            return null;
        }
    }

    public String actualizarLocal(local local) {
        long contador = 0;
        String[] id = {local.getID_local()};// OJO con las Llaves para poder se String []
        String regInsertados = "Registro Actualizados: ";
        contador = db.update("LOCAL", local.toContentValues(), "ID_LOCAL = ?", id);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Actualizar, \"id\" no encontrado";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String borrarLocal(String s) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //   if (verificarIntegridad(alumno,3)) {
        contador += db.delete("LOCAL", "ID_LOCAL='" + s + "'", null);
        //   }
        //  contador+=db.delete( "alumno" , "carnet='" +alumno.getCarnet()+"'", null);
        regAfectados += contador;
        return regAfectados;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea1.s3db";
        private static final int VERSION = 1;


        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //Creacion de las tabla horario
                db.execSQL("CREATE TABLE 'HORARIO'  ('IDHORARIO' INTEGER not null, 'HORA_INICIO' DATE, 'HORA_FIN' DATE, constraint PK_HORARIO primary key (IDHORARIO))");


                //Creacion de las tabla ASIGNACION_LOCALES
                db.execSQL("CREATE TABLE ASIGNACION_LOCALES(IDASIGNACIONLOCAL INTEGER not null,IDACTIVIDAD INTEGER,ID_LOCAL INTEGER,constraint PK_ASIGNACION_LOCALES primary key (IDASIGNACIONLOCAL));");
                //Creacion de foreign key
                db.execSQL("create index '3_FK' on ASIGNACION_LOCALES (IDACTIVIDAD ASC );");
                db.execSQL("create index '4_FK' on ASIGNACION_LOCALES (ID_LOCAL ASC);");


                //Creacion de las tablas para pruebas
                //Creacion de las tabla Actividad
                db.execSQL("create table ACTIVIDAD( IDACTIVIDAD INTEGER not null,   IDTIPOACTIVIDAD      INTEGER,   IDPERSONA            CHAR(8),   DESCRIPCION        VARCHAR2(150),constraint PK_ACTIVIDAD primary key (IDACTIVIDAD));");
                db.execSQL("create index 'RELATIONSHIP_1_FK' on ACTIVIDAD (IDTIPOACTIVIDAD ASC);");
                db.execSQL("create index '2_FK' on ACTIVIDAD (IDPERSONA ASC );");

                //Creacion de las tabla Actividad
                db.execSQL("create table LOCAL (ID_LOCAL  INTEGER not null, DIRECCION VARCHAR2(100),CAPACIDAD INTEGER,constraint PK_LOCAL primary key (ID_LOCAL));");




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


    public String llenarBD() {
        abrir();
        try {


            db.execSQL("create table ACTIVIDAD( IDACTIVIDAD INTEGER not null,   IDTIPOACTIVIDAD      INTEGER,   IDPERSONA            CHAR(8),   DESCRIPCION        VARCHAR2(150),constraint PK_ACTIVIDAD primary key (IDACTIVIDAD));");
            db.execSQL("create index 'RELATIONSHIP_1_FK' on ACTIVIDAD (IDTIPOACTIVIDAD ASC);");
            db.execSQL("create index '2_FK' on ACTIVIDAD (IDPERSONA ASC );");

            //Creacion de las tabla Actividad
            db.execSQL("create table LOCAL (ID_LOCAL  INTEGER not null, DIRECCION VARCHAR2(100),CAPACIDAD INTEGER,constraint PK_LOCAL primary key (ID_LOCAL));");


            db.execSQL("DELETE FROM ASIGNACION_LOCALES");
            db.execSQL("insert into ASIGNACION_LOCALES (IdActividad,ID_local) values (2,3)");


            db.execSQL("INSERT INTO 'ACTIVIDAD' VALUES(1,1,'dui1','clase de PDM');");
            db.execSQL("INSERT INTO 'ACTIVIDAD' VALUES(2,1,'dui2','clase de PDM numero 2');");
            db.execSQL("INSERT INTO 'ACTIVIDAD' VALUES(3,1,'dui3','clase de PDM sim pd333');");
            db.execSQL("  INSERT INTO 'LOCAL' VALUES(1,'AV UNIVERSITARIA ',25);");
            db.execSQL("  INSERT INTO 'LOCAL' VALUES(2,'AV 2221111 UNIVERSITARIA ',250);");
            db.execSQL("  INSERT INTO 'LOCAL' VALUES(3,'AV 33332222 UNIVERSITARIA ',251);");

        } catch (Exception e) {
            Log.d("my", e.toString());
            return "Error" + e.toString();
        }


        cerrar();
        return "Guardo Correctamente";

    }

}
