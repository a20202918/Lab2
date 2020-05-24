package com.example.laboratorio2.entidades;

public class ApiEmp extends Api{
    Empleado[] empleados;

    public ApiEmp(String estado, int cuota, Empleado[] empleados) {
        super(estado, cuota);
        this.empleados = empleados;
    }

    public Empleado[] getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado[] empleados) {
        this.empleados = empleados;
    }
}
