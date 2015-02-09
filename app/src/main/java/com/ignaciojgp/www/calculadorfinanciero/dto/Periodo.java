package com.ignaciojgp.www.calculadorfinanciero.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public class Periodo {
    private long id;
    private Date inicio, fin;

    public Periodo(int id, Date inicio, Date fin) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
}
