package com.example.laboratorio2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiBorrar;
import com.example.laboratorio2.entidades.ApiKey;
import com.example.laboratorio2.entidades.ApiTrabajo;
import com.example.laboratorio2.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BorrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        obtenerCreatedBy();

    }

    public void obtenerCreatedBy(){
        if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";
            //Log.d("prueba","a");
            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();
                            ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                            final String key = apiKey.getApi_key();
                            //Log.d("exitoVol", key);

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/trabajo";

                            StringRequest stringRequest2 = new StringRequest(StringRequest.Method.DELETE, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {

                                            Log.d("exitoVol", response2);

                                            Gson gson = new Gson();
                                            ApiBorrar apiBorrar = gson.fromJson(response2, ApiBorrar.class);

                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(BorrarActivity.this);
                                            alertDialog.setMessage(apiBorrar.getMsg());
                                            alertDialog.setNeutralButton("Aceptar",
                                                    new DialogInterface.OnClickListener(){

                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Log.d("alerta", "click en aceptar");
                                                        }
                                                    });
                                            alertDialog.show();


                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("errorVol", error.getMessage());
                                        }
                                    }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String, String> cabecera = new HashMap<>();
                                    cabecera.put("api-key", key);
                                    return cabecera;
                                }

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> parametros = new HashMap<>();
                                    parametros.put("id", "AD_RF");
                                    return parametros;
                                }
                            };

                            RequestQueue requestQueue2 = Volley.newRequestQueue(BorrarActivity.this);
                            requestQueue2.add(stringRequest2);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("errorVol", error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("groupKey", "eyKJPXNNyrSN3jp95J6K");
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    public void obtenerDeInternet() {
       // if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";
            Log.d("prueba","a");
            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("exitoVol", response2);
                            Gson gson = new Gson();
                            ApiBorrar apiBorrar = gson.fromJson(response2, ApiBorrar.class);

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(BorrarActivity.this);
                            alertDialog.setMessage(apiBorrar.getMsg());
                            alertDialog.setNeutralButton("Aceptar",
                            new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("alerta", "click en aceptar");
                                }
                            });
                            alertDialog.show();

                            }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("errorVol", error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("groupKey", "eyKJPXNNyrSN3jp95J6K");
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
   // }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null) return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null) return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;

        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) return false;

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) return true;
            return false;

        }
    }

}
