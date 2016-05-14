package work.tarea1.PrivetClass;

/**
 * Created by David-PC on 11/5/2016.
 */
public class TipoPersona {


    private String codigo;
    private String tipoPersona;
    private String descripcion;


    public TipoPersona() {
    }

    public TipoPersona(String codigo, String tipoPersona, String descripcion) {
        this.codigo=codigo;
        this.tipoPersona = tipoPersona;
        this.descripcion = descripcion;
    }



    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
