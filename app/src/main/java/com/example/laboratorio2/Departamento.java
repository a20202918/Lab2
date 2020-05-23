package com.example.laboratorio2;

public class Departamento {
    private int departamentId;
    private String departamentName;
    private String departamentShortName;

    public Departamento(int departamentId, String departamentName, String departamentShortName) {
        this.departamentId = departamentId;
        this.departamentName = departamentName;
        this.departamentShortName = departamentShortName;
    }

    public int getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(int departamentId) {
        this.departamentId = departamentId;
    }

    public String getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(String departamentName) {
        this.departamentName = departamentName;
    }

    public String getDepartamentShortName() {
        return departamentShortName;
    }

    public void setDepartamentShortName(String departamentShortName) {
        this.departamentShortName = departamentShortName;
    }
}
