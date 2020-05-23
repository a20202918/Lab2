package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CrearEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);
        setTitle("Crear Empleado");

    
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


    Intent intentGuardar = new Intent();
    //Subir en formato JSON
    setResult(RESULT_OK,intentGuardar);
    finish();

    }
}
