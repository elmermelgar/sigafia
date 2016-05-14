package work.tarea1.PrivetClass;

/**
 * Created by Elmer Melgar on 12/5/2016.
 */
public class TipoActividad {

    private int idTipoActividad;
    private String tipoActividad;

    public TipoActividad(int idTipoActividad, String tipoActividad) {
        this.idTipoActividad=idTipoActividad;
        this.tipoActividad=tipoActividad;
    }

    public TipoActividad() {

    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

}
