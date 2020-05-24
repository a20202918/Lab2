package com.example.laboratorio2.entidades;

public class Api {
    private String estado;
    private int cuota;


    public Api(String estado, int cuota) {
        this.estado = estado;
        this.cuota = cuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

}
