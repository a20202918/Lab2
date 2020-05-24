package com.example.laboratorio2.entidades;

import com.google.gson.annotations.SerializedName;

public class ApiKey extends Api{

    @SerializedName(value = "api-key")
    private String api_key;

    public ApiKey(String estado, int cuota, String api_key) {
        super(estado, cuota);
        this.api_key = api_key;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
