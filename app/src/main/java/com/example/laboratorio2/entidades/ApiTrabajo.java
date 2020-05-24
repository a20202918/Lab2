package com.example.laboratorio2.entidades;

public class ApiTrabajo extends Api{

    private Trabajo[] trabajos;

    public ApiTrabajo(String estado, int cuota, Trabajo[] trabajos) {
        super(estado, cuota);
        this.trabajos = trabajos;
    }

    public Trabajo[] getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(Trabajo[] trabajos) {
        this.trabajos = trabajos;
    }
}

