package work.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import work.tarea1.PrivetClass.*;

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

    private static final String[] camposPersona = new String []
            {"id_persona" , "id_tipo_persona" , "nombre", "apellido" , "dui" , "grado_academico" , "genero" , "email", "usuario" , "clave"  };
    private static final String[] camposTipoPersona = new String []
            {"codigo" , "tipo_persona", "descripcion" };

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
        //private static final String BASE_DATOS = "tarea1.s3db" ;
        private static final String BASE_DATOS = "tarea1BD.s3db" ;
        private static final int VERSION = 1;


                    public DatabaseHelper(Context context) {
                        super(context, BASE_DATOS, null, VERSION);
                    }



                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        try{
                            db.execSQL("CREATE TABLE 'asignacion' ('cod_asignacion' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'actividadId' INTEGER NOT NULL, 'idLocal' INTEGER);" );
                            db.execSQL("CREATE TABLE 'horario' ('idHorario' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, 'horaInicial' DATETIME NOT NULL, 'horaFinal' DATETIME NOT NULL);" );

                            //Tablas Persona y TipoPersona
                            db.execSQL("CREATE TABLE 'Persona'('id_persona' VARCHAR(8) NOT NULL PRIMARY KEY,"
                                    +"'id_tipo_persona' INTEGER,'nombre' VARCHAR(80),'apellido' VARCHAR(80),'dui' VARCHAR(10),'grado_academico' VARCHAR(50)," +
                                    "'genero' VARCHAR(1),'email' VARCHAR(50));" );
                            db.execSQL("CREATE TABLE 'TipoPersona'('codigo' VARCHAR(10) NOT NULL"
                                    +" PRIMARY KEY,'tipo_persona' VARCHAR(30),'descripcion' VARCHAR(150));");
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



    public String llenarBDCarnet(){
        abrir();
        try {
            db.execSQL("DELETE FROM asignacion");

            db.execSQL("insert into asignacion (actividadId,idLocal) values (2,3)" );

            //Insersion tabla TipoPersona
            db.execSQL("DELETE FROM TipoPersona");
            db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('d115','Docente','Docente') ");
            db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('e115','Estudiante','Estudiante') ");
            //db.execSQL("INSERT INTO TipoPersona(codigo,tipo_persona,descripcion) values('x115','Externo','Externo') ");
            //Insersion tabla Persona
            db.execSQL("DELETE FROM Persona");

        }catch (Exception e){
            Log.d("my",e.toString());
            return "Guardo Error";
        }


        cerrar();
        return "Guardo Correctamente";

    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:
            {
                //verificar que exista TipoPersona
                TipoPersona tipoPersona=(TipoPersona)dato;
                String[] id = {tipoPersona.getCodigo()};
                abrir();
                Cursor c2 = db.query( "TipoPersona" , null, "codigo = ?" , id, null, null,
                        null);
                if(c2.moveToFirst()){
                //Se encontro TipoPersona
                    return true;
                }
                return false;
            }
            case 2:
            {

            }
            default:
                return false;
        }

    }
    //Insertar TipoPersona
    public String insertar(TipoPersona tipoPersona){
        String regInsertados="Registro Insertado Nº= " ;
        long contador=0;
        ContentValues tp = new ContentValues();

        tp.put("codigo", tipoPersona.getCodigo());
        tp.put("tipo_persona", tipoPersona.getTipoPersona());
        tp.put("descripcion", tipoPersona.getDescripcion());
        contador=db.insert("TipoPersona" , null, tp);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción" ;
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }
    //Consultar TipoPersona
    public TipoPersona consultarTipoPersona(String codigo){
        String [] id={codigo};
        Cursor cursor=db.query("TipoPersona",camposTipoPersona,"codigo=?",id,null,null,null);
        if(cursor.moveToFirst()){
            TipoPersona tipoPersona= new TipoPersona();
            tipoPersona.setCodigo(cursor.getString(0));
            tipoPersona.setTipoPersona(cursor.getString(1));
            tipoPersona.setDescripcion(cursor.getString(2));
            return  tipoPersona;
        }else{
            return null;
    }
    }

    //Actualizar TipoPersona
    public String actualizar(TipoPersona tipoPersona){
        if(verificarIntegridad(tipoPersona, 1)){
            String[] id = {tipoPersona.getCodigo()};
            ContentValues cv = new ContentValues();
            cv.put("codigo",tipoPersona.getCodigo());
            cv.put("tipo_persona", tipoPersona.getTipoPersona());
            cv.put("descripcion", tipoPersona.getDescripcion());
            db.update("TipoPersona", cv, "codigo = ?", id);
            return "Registro Actualizado Correctamente" ;
        }else{
            return "Registro con carnet " + tipoPersona.getCodigo() + " no existe";
        }
    }

    //Eliminar TipoPersona
    public String eliminar(TipoPersona tipoPersona){
        String regAfectados="filas afectadas= " ;
        int contador=0;
        if (verificarIntegridad(tipoPersona,1)) {
            contador+=db.delete( "TipoPersona", "codigo='" +tipoPersona.getCodigo()+"'", null);
        }

        regAfectados+=contador;
        return regAfectados;
    }

    //Insertar Persona
    public String insertar(Persona persona){


        String regInsertados="Registro Insertado Nº= " ;
        long contador=0;
        ContentValues alum = new ContentValues();
        alum.put("id_persona" , persona.getIdPersona());
        alum.put("id_tipo_persona" , persona.getIdTipoPersona());
        alum.put("nombre", persona.getNombre());
        alum.put("apellido", persona.getApellido());
        alum.put("dui", persona.getDui());
        alum.put("grado_academico" , persona.getGradoAcademico());
        alum.put("genero" , persona.getGenero());
        alum.put("email" , persona.getEmail());
        contador=db.insert("Persona" , null, alum);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción" ;
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizar(Persona persona){

        return null;
    }

    public String eliminar(Persona persona){
        return null;
    }

    public Persona consultarPersona(String idPersona){
        String[] id = {idPersona};
        Cursor cursor = db.query("Persona" , camposPersona, "id_persona = ?" , id,
                null, null, null);
        if(cursor.moveToFirst()) {
            Persona persona=new Persona();
            persona.setIdPersona(cursor.getString(0));
            persona.setIdTipoPersona(cursor.getInt(1));
            persona.setNombre(cursor.getString(2));
            persona.setApellido(cursor.getString(3));
            persona.setDui(cursor.getString(4));
            persona.setGradoAcademico(cursor.getString(5));
            persona.setGenero(cursor.getString(6));
            persona.setEmail(cursor.getString(7));

            return persona;
        }else{
            return null;
        }
    }

}
