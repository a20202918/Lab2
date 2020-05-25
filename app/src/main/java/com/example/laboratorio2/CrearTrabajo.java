package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiDepartamento;
import com.example.laboratorio2.entidades.ApiKey;
import com.example.laboratorio2.entidades.Departamento;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrearTrabajo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

        //LLenamos el Spinner con los valores obtenidos del web service
        obtenerSppiner();

        //Le colocamos el título al Layout
        setTitle("Crear Trabajo");
    }


    public void FinalizarRegistro(View view) {
        //Guardar en web service
        EditText editTextNombreEmpleo = findViewById(R.id.editTextNombreEmpleo);
        String nombreEmpleo = editTextNombreEmpleo.getText().toString();
        EditText editTextSalarioMinimo = findViewById(R.id.editTextSalarioMinimo);
        String salarioMinimo = editTextSalarioMinimo.getText().toString();
        EditText editTextSalarioMaximo = findViewById(R.id.editTextSalarioMaximo);
        String salarioMaximo = editTextSalarioMaximo.getText().toString();
        EditText editTextAbreviacion = findViewById(R.id.editTextAbreviacion);
        String abreviacion = editTextAbreviacion.getText().toString();

        if (nombreEmpleo.isEmpty()) {
            editTextNombreEmpleo.setError("Por favor ingrese el nombre");
        } else if (salarioMinimo.isEmpty()) {
            editTextSalarioMinimo.setError("Por favor ingrese el salario mínimo");
        } else if (salarioMaximo.isEmpty()) {
            editTextSalarioMaximo.setError("Por favor ingrese el salario maximo");
        } else if (abreviacion.isEmpty()) {
            editTextAbreviacion.setError("Por favor ingrese una abreviación al empleo");
        } else if (abreviacion.length() != 2) {
            editTextAbreviacion.setError("La abreviación debe de contener solo dos carácteres");
        } else {
            finish();
        }

    }

    //Obtención de los valores para llenar el Spinner
    public void obtenerSppiner() {
        if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey/";

            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            final Gson gson = new Gson();
                            ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                            final String key = apiKey.getApi_key();
                            Log.d("exito", response);

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";
                            StringRequest stringRequest1 = new StringRequest(StringRequest.Method.GET, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {
                                            Log.d("exito", response2);

                                            Gson gson1 = new Gson();
                                            ApiDepartamento apiDepartamento = gson1.fromJson(response2, ApiDepartamento.class);

                                            ArrayList<String> listDepartamento;
                                            listDepartamento = new ArrayList<String>();

                                            Departamento[] listar = apiDepartamento.getDepartamentos();

                                            for (int i = 0; i < listar.length; i++) {
                                                listDepartamento.add("" + listar[i].getDepartmentName());
                                                Log.d("Llenar con el Nombre: ", String.valueOf(listDepartamento));
                                            }
                                            ArrayAdapter<String> adaptarSpinner = new ArrayAdapter<>(CrearTrabajo.this,
                                                    android.R.layout.simple_spinner_dropdown_item, listDepartamento);
                                            Spinner spinnerDepartamento = findViewById(R.id.spinnerDepartamento);
                                            spinnerDepartamento.setAdapter(adaptarSpinner);
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("error", error.getMessage());
                                }
                            }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String, String> cabecera = new HashMap<>();
                                    cabecera.put("api-key", key);
                                    return cabecera;
                                }
                            };

                            RequestQueue requestQueue2 = Volley.newRequestQueue(CrearTrabajo.this);
                            requestQueue2.add(stringRequest1);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("errorVol", error.getMessage());
                    TextView textView = findViewById(R.id.textViewRespuestaApi);
                    textView.setText(error.getMessage());

                }
            }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
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


