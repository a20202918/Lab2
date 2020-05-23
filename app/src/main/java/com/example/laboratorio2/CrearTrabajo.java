package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CrearTrabajo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

        setTitle("Crear Trabajo");
    }



    public void FinalizarRegistro(View view){
        //Guardar en web service
        EditText editTextNombreEmpleo = findViewById(R.id.editTextNombreEmpleo);
        String nombreEmpleo = editTextNombreEmpleo.getText().toString();
        EditText editTextSalarioMinimo = findViewById(R.id.editTextSalarioMinimo);
        String salarioMinimo = editTextSalarioMinimo.getText().toString();
        EditText editTextSalarioMaximo = findViewById(R.id.editTextSalarioMaximo);
        String salarioMaximo = editTextSalarioMaximo.getText().toString();
        EditText editTextAbreviacion = findViewById(R.id.editTextAbreviacion);
        String abreviacion = editTextAbreviacion.getText().toString();

        if(nombreEmpleo.isEmpty()){
            editTextNombreEmpleo.setError("Por favor ingrese el nombre");
        }else if(salarioMinimo.isEmpty()){
            editTextSalarioMinimo.setError("Por favor ingrese el salario mínimo");
        }else if(salarioMaximo.isEmpty()){
            editTextSalarioMaximo.setError("Por favor ingrese el salario maximo");
        }else if(abreviacion.isEmpty()){
            editTextAbreviacion.setError("Por favor ingrese una abreviación al empleo");
        }else{
            finish();
        }

    }
}
