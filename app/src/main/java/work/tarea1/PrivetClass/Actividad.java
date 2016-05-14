package work.tarea1.PrivetClass;

/**
 * Created by David-PC on 13/5/2016.
 */
public class Actividad {

    private Integer idActividad;
    private Integer idTipoActividad;
    private String idPersona;

    private String idActividadString;

    public Actividad(Integer idTipoActividad, String idPersona, String descripcion) {

        this.idTipoActividad = idTipoActividad;
        this.idPersona = idPersona;
        this.descripcion = descripcion;
    }

    public Actividad(Integer idActividad, Integer idTipoActividad, String idPersona, String descripcion) {
        this.idActividad = idActividad;
        this.idTipoActividad = idTipoActividad;
        this.idPersona = idPersona;
        this.descripcion = descripcion;
    }
    public Actividad(String idActividad, Integer idTipoActividad, String idPersona, String descripcion) {
        this.idActividadString = idActividad;
        this.idTipoActividad = idTipoActividad;
        this.idPersona = idPersona;
        this.descripcion = descripcion;
    }

    public Actividad() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActvidad) {
        this.idTipoActividad = idTipoActvidad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    private String descripcion;

    public String getIdActividadString() {
        return idActividadString;
    }

    public void setIdActividadString(String idActividadString) {
        this.idActividadString = idActividadString;
    }
}
