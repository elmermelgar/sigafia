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
    private String cod_asignacion;
    private String actividadId;
    private String idLocal;

    public asignacion(String cod_asignacion, String actividadId, String idLocal) {
        this.cod_asignacion = cod_asignacion;
        this.actividadId = actividadId;
        this.idLocal = idLocal;
    }


    /**
     * convierte el objeto directamente para insertar
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ContentValues toContentValues() {
        ContentValues Data = new ContentValues();

       if(Objects.equals(cod_asignacion, "0")) Data.put("cod_asignacion" , cod_asignacion );
        Data.put("actividadId" , actividadId);
        Data.put("idLocal", idLocal);


        return Data;
    }


    public String getCod_asignacion() {
        return cod_asignacion;
    }

    public void setCod_asignacion(String cod_asignacion) {
        this.cod_asignacion = cod_asignacion;
    }

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }
}
