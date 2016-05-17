package work.tarea1.PrivetClass;

/**
 * Created by Navarro on 07/05/2016.
 */
public class DisponibilidadCiclo {
    private int idDisponibilidad;
    private String idLocal;
    private int idHorario;
    private int idciclo;
    private String disponibilidad;

    public DisponibilidadCiclo(int idDisponibilidad, String idLocal, int idHorario, int idciclo, String disponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
        this.idLocal = idLocal;
        this.idHorario = idHorario;
        this.idciclo = idciclo;
        this.disponibilidad = disponibilidad;
    }

    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdciclo() {
        return idciclo;
    }

    public void setIdciclo(int idciclo) {
        this.idciclo = idciclo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public DisponibilidadCiclo() {
    }
}


