package com.example.laboratorio2.entidades;

public class Departamento {

    private int departamentId;
    private String departamentName;
    private String managerId;
    private int locationId;
    private String departamentShortName;

    public Departamento(int departamentId, String departamentName, String managerId, int locationId, String departamentShortName) {
        this.departamentId = departamentId;
        this.departamentName = departamentName;
        this.managerId = "";
        this.locationId = locationId;
        this.departamentShortName = departamentShortName;

    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDepartamentShortName() {
        return departamentShortName;
    }

    public void setDepartamentShortName(String departamentShortName) {
        this.departamentShortName = departamentShortName;

    }
}
