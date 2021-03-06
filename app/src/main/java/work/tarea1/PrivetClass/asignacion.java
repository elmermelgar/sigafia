package work.tarea1.PrivetClass;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;
import android.text.Editable;

import java.util.Objects;

/**
 * Created by fhmen on 27/04/2016.
 */
public class asignacion {
    private String ID_local;
    private String IdActividad;
    private String idAsignacionLocal;//llave primaria

    public asignacion(String idAsignacionLocal, String IdActividad, String ID_local) {
        this.ID_local = ID_local;
        this.IdActividad = IdActividad;
        this.idAsignacionLocal = idAsignacionLocal;
    }


    /**
     * convierte el objeto directamente para insertar
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ContentValues toContentValues() {
        ContentValues Data = new ContentValues();
        Data.put("idAsignacionLocal", idAsignacionLocal);
        Data.put("IdActividad", IdActividad);
        Data.put("ID_local", ID_local);


        return Data;
    }

    public String getID_local() {
        return ID_local;
    }

    public void setID_local(String ID_local) {
        this.ID_local = ID_local;
    }

    public String getIdActividad() {
        return IdActividad;
    }

    public void setIdActividad(String idActividad) {
        IdActividad = idActividad;
    }

    public String getIdAsignacionLocal() {
        return idAsignacionLocal;
    }

    public void setIdAsignacionLocal(String idAsignacionLocal) {
        this.idAsignacionLocal = idAsignacionLocal;
    }
}
