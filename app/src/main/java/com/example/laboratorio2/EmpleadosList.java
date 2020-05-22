package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class EmpleadosList extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_4main,menu);
        return true;
    }

    public void accionMasBar(MenuItem item){
        Intent intent = new Intent (this, CrearTrabajo.class);
        startActivity(intent);
    }

    public void accionSmileBar(MenuItem item){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    ArrayList<String> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listDatos = new ArrayList<String>();

        for (int i = 0; i <= 50; i++) {
            listDatos.add("Dato#" + i + " ");
        }

        AdapterDatos adapter = new AdapterDatos(listDatos);
        recycler.setAdapter(adapter);
    }

}
