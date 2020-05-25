package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiKey;
import com.example.laboratorio2.entidades.ApiTrabajo;
import com.example.laboratorio2.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    //Configuración del menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_4main,menu);
        return true;
    }

    //Botón con un + para crear un trabajo nuevo
    public void accionMasMain(MenuItem item){
        Intent intent = new Intent (this, CrearTrabajo.class);
        int requestCode = 1;
        startActivityForResult(intent, requestCode);
    }

    //Botón con un :) para irse a la lista de empleados
    public void accionSmileBar(MenuItem item){
        Intent intent = new Intent (this, EmpleadosLista.class);
        int requestCode = 1;
        startActivityForResult(intent, requestCode);
    }

    public void accionBotonEditar(View view){
        /* AÑADIR AQUÍ LA ACTIVIDAD A LA QUE SE DIRIGE AL PRESIONAR EL BOTÓN EDITAR
        Intent intent = new Intent (this, aqui.class);
        int requestCode = 1;
        startActivityForResult(intent, requestCode);*/
    }

    public void accionBotonBorrar(View view){

        Log.d("metodo","metodo");
        /* AÑADIR AQUÍ LA ACTIVIDAD A LA QUE SE DIRIGE AL PRESIONAR EL BOTÓN EDITAR
        Intent intent = new Intent (this, aqui.class);
        int requestCode = 1;
        startActivityForResult(intent, requestCode);*/
    }

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //obtenerDeInternet();
    }

    //Lista de trabajos
    public void obtenerDeInternet() {
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

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

                            StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {

                                            Log.d("exitoVol", response2);

                                            Gson gson = new Gson();
                                            ApiTrabajo apiTrabajo = gson.fromJson(response2, ApiTrabajo.class);

                                            ArrayList<String> listTrabajos;
                                            listTrabajos = new ArrayList<String>();

                                            Trabajo[] prueba = apiTrabajo.getTrabajos();
                                            for (int i = 0; i <prueba.length; i++){
                                                listTrabajos.add("\nId: " + prueba[i].getJobId() + "\nTitulo: "+prueba[i].getJobTitle()+"\nSalario Minimo: "+prueba[i].getMinSalary()+"\nSalario Maximo: "+prueba[i].getMaxSalary());
                                                //String pruebaId = prueba[i].getJobId();
                                                //Log.d("exitoVol", pruebaId);
                                            }
                                           // return listTrabajos;

                                            AdapterDatos adapter = new AdapterDatos(listTrabajos);
                                            recycler.setAdapter(adapter);
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
                            };

                            RequestQueue requestQueue2 = Volley.newRequestQueue(MainActivity.this);
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

