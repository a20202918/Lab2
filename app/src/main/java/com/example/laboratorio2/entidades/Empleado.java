package com.example.laboratorio2.entidades;

public class Empleado extends Trabajador {
    private int phoneNumber;
    private Trabajo jobId;
    private double salary;
    private double commissionPct;
    private Trabajador managerId;
    private Departamento departamentId;
    private String createdBy;

    public Empleado(String employeeId, String firstName, String lastName, String email, int phoneNumber, Trabajo jobId, double salary, double commissionPct, Trabajador managerId, Departamento departamentId, String createdBy) {
        super(employeeId, firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.departamentId = departamentId;
        this.createdBy = createdBy;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Trabajo getJobId() {
        return jobId;
    }

    public void setJobId(Trabajo jobId) {
        this.jobId = jobId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Trabajador getManagerId() {
        return managerId;
    }

    public void setManagerId(Trabajador managerId) {
        this.managerId = managerId;
    }

    public Departamento getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Departamento departamentId) {
        this.departamentId = departamentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
