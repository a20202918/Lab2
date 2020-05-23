package com.example.laboratorio2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ListarDepartamentos {
    String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";

    StringRequest stringRequestDepartamentos = new StringRequest(StringRequest.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
    );
    




}
