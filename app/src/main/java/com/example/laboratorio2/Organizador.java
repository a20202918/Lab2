package com.example.laboratorio2;

public class Organizador {

    private String info;
    private boolean buttonBorrar;
    private boolean buttonEditar;

    public Organizador(String info, boolean buttonBorrar, boolean buttonEditar) {
        this.info = info;
        this.buttonBorrar = buttonBorrar;
        this.buttonEditar = buttonEditar;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isButtonBorrar() {
        return buttonBorrar;
    }

    public void setButtonBorrar(boolean buttonBorrar) {
        this.buttonBorrar = buttonBorrar;
    }

    public boolean isButtonEditar() {
        return buttonEditar;
    }

    public void setButtonEditar(boolean buttonEditar) {
        this.buttonEditar = buttonEditar;
    }
}
