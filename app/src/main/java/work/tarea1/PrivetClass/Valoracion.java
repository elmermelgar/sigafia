package work.tarea1.PrivetClass;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;

import java.util.Objects;

/**
 * Created by Elmer Melgar on 9/5/2016.
 */
public class Valoracion {


    private int idValoración;
    private int idTipoValoración;
    private String idPersona;
    private String descripciónValoración;
    private int idAsignaciónLocal;

    public Valoracion(int idValoración, int idTipoValoración, int idAsignaciónLocal, String idPersona, String descripciónValoración) {
        this.idValoración = idValoración;
        this.idTipoValoración = idTipoValoración;
        this.idPersona = idPersona;
        this.descripciónValoración = descripciónValoración;
        this.idAsignaciónLocal = idAsignaciónLocal;
    }

    public Valoracion() {

    }



    public int getIdValoración() {
        return idValoración;
    }

    public int getIdTipoValoración() {
        return idTipoValoración;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public String getDescripciónValoración() {
        return descripciónValoración;
    }

    public void setIdValoración(int idValoración) {
        this.idValoración = idValoración;
    }

    public void setIdTipoValoración(int idTipoValoración) {
        this.idTipoValoración = idTipoValoración;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public void setDescripciónValoración(String descripciónValoración) {
        this.descripciónValoración = descripciónValoración;
    }

    public int getIdAsignaciónLocal() {
        return idAsignaciónLocal;
    }

    public void setIdAsignaciónLocal(int idAsignaciónLocal) {
        this.idAsignaciónLocal = idAsignaciónLocal;
    }


}
