package work.tarea1.PrivetClass;

import android.content.ContentValues;

/**
 * Created by fhmen on 12/05/2016.
 */
public class local {
    private String ID_local;
    private String direccion;
    private String capacidad;

    public local(String ID_local, String direccion, String capacidad) {
        this.ID_local = ID_local;
        this.direccion = direccion;
        this.capacidad = capacidad;
    }

    public ContentValues toContentValues() {
        ContentValues Data = new ContentValues();
        Data.put("ID_LOCAL", ID_local);
        Data.put("DIRECCION", direccion);
        Data.put("CAPACIDAD", capacidad);

        return Data;
    }


    public String getID_local() {
        return ID_local;
    }

    public void setID_local(String ID_local) {
        this.ID_local = ID_local;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}
