package work.tarea1.PrivetClass;

/**
 * Created by Elmer Melgar on 11/5/2016.
 */
public class TipoValoracion {


    private int idTipoValoracion;
    private String descripciónTipoValoracion;
    private String tipoValoracion;

    public TipoValoracion(int idTipoValoracion, String tipoValoracion, String descripciónTipoValoracion) {
        this.idTipoValoracion=idTipoValoracion;
        this.tipoValoracion=tipoValoracion;
        this.descripciónTipoValoracion=descripciónTipoValoracion;
    }

    public TipoValoracion() {

    }

    public int getIdTipoValoracion() {
        return idTipoValoracion;
    }

    public void setIdTipoValoracion(int idTipoValoracion) {
        this.idTipoValoracion = idTipoValoracion;
    }

    public String getDescripciónTipoValoracion() {
        return descripciónTipoValoracion;
    }

    public void setDescripciónTipoValoracion(String descripciónTipoValoracion) {
        this.descripciónTipoValoracion = descripciónTipoValoracion;
    }

    public String getTipoValoracion() {
        return tipoValoracion;
    }

    public void setTipoValoracion(String tipoValoracion) {
        this.tipoValoracion = tipoValoracion;
    }

}
