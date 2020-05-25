package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laboratorio2.entidades.ApiKey;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrearEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);
        //Titulo del activity
        setTitle("Crear Empleado");
        //creamos el sppiner
        llenarSppinerJobId();
        llenarSppinermangerId();
        llenarSppinerdepartmentId();


    
    }

    private void llenarSppinerdepartmentId() {
        if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";

            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();
                            ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                            final String key = apiKey.getApi_key();

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";

                            StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {

                                            Log.d("exitoVol", response2);

                                            Gson gson = new Gson();
                                            Spinner spinnerdepartment = findViewById(R.id.spinnerECdepartamento);
                                           // ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                                            //        android.R.layout.simple_spinner_dropdown_item, );
                                           // spinnerdepartment.setAdapter(adapterSpinner);
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

                            RequestQueue requestQueue2 = Volley.newRequestQueue(CrearEmpleado.this);
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

    private boolean isInternetAvailable() {
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

    private void llenarSppinermangerId() {
        if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";

            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();
                            ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                            final String key = apiKey.getApi_key();

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";

                            StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {

                                            Log.d("exitoVol", response2);

                                            Gson gson = new Gson();
                                            Spinner spinnermanager = findViewById(R.id.spinnerECjefe);
                                            //ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                                            //        android.R.layout.simple_spinner_dropdown_item, );
                                            //spinnermanager.setAdapter(adapterSpinner);
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

                            RequestQueue requestQueue2 = Volley.newRequestQueue(CrearEmpleado.this);
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

    private void llenarSppinerJobId() {
        if (isInternetAvailable()) {
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";

            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();
                            ApiKey apiKey = gson.fromJson(response, ApiKey.class);
                            final String key = apiKey.getApi_key();

                            String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

                            StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response2) {

                                            Log.d("exitoVol", response2);

                                            Gson gson = new Gson();

                                            Spinner spinnertrabajo = findViewById(R.id.spinnerECtrabajo);
                                            //ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                                              //      android.R.layout.simple_spinner_dropdown_item, );
                                            //spinnertrabajo.setAdapter(adapterSpinner);
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

                            RequestQueue requestQueue2 = Volley.newRequestQueue(CrearEmpleado.this);
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

    public void finalizarCrearEmpleado (View view){

        //Se activa con el boton siguiente
        //Button buttonGrabarEmpleado = findViewById(R.id.buttonCEgrabar);

        //Se debe iniciar de la lista empleados un intent
        /*Button b = finViewById(R.id.buttonagregarpersonadelabarra);
        b.SetOnClickListener(new View.OnclickListener(){
        public void onClick(View v)
        Intent intent = new Intent(activity_empleados_list.this, activity_crear_empleado.class);
        int requestCode =1;
        startActivityForResult(intent,requestCode);
        }
         */


    EditText editTextCEnombre = findViewById(R.id.editTextCEnombre);
    String CEnombre = editTextCEnombre.getText().toString();
    EditText editTextCEapellido = findViewById(R.id.editTextCEapellido);
    String CEapellido = editTextCEapellido.getText().toString();
    EditText editTextCEemail = findViewById(R.id.editTextCEemail);
    String CEemail = editTextCEemail.getText().toString();
    EditText editTextCEcelular = findViewById(R.id.editTextCEcelular);
    String CEcelular = editTextCEcelular.getText().toString();
    EditText editTextCEsalario =  findViewById(R.id.editTextCEsalario);
    String CEsalario = editTextCEsalario.getText().toString();
    EditText editTextCEcomision = findViewById(R.id.editTextCEcomision);
    String CEcomision = editTextCEcomision.getText().toString();

        if (CEapellido.isEmpty()) {
            editTextCEapellido.setError("Por favor ingrese el apellido");
        } else if (CEemail.isEmpty()) {
            editTextCEemail.setError("Por favor ingrese el email");
        } else {
            finish();
        }

    Intent intentGuardar = new Intent();
    //Subir en formato JSON
    setResult(RESULT_OK,intentGuardar);
    finish();

    }
}
