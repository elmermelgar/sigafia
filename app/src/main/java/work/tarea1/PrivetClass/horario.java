package work.tarea1.PrivetClass;

import android.content.ContentValues;

/**
 * Created by fhmen on 11/05/2016.
 */
public class horario {
    private String idHorario;
    private String horaInicio;
    private String horaFinal;

    public horario(String idHorario, String horaInicio, String horaFinal) {
        this.idHorario = idHorario;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public ContentValues toContentValues() {
        ContentValues Data = new ContentValues();
        Data.put("IDHORARIO", idHorario);
        Data.put("HORA_INICIO", horaInicio);
        Data.put("HORA_FIN", horaFinal);


        return Data;
    }


    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
}
