package com.example.laboratorio2.entidades;

public class ApiBorrar {
    private String msg;
    private String estado;
    private int cuota;

    public ApiBorrar(String msg, String estado, int cuota) {
        this.msg = msg;
        this.estado = estado;
        this.cuota = cuota;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
