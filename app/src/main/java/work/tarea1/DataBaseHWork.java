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

}
