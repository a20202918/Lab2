package com.example.laboratorio2;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> listDatos;

    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, final int position) {
        holder.asignarDatos(listDatos.get(position));
        holder.buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton","Click en borrar"+position);
                Borrar borrar = new Borrar();
                borrar.botonBorrar(position);
            }
        });

        holder.buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton","Click en editar"+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        Button buttonEditar;
        Button buttonBorrar;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.textViewInfo);
            buttonEditar = itemView.findViewById(R.id.buttonEditar);
            buttonBorrar = itemView.findViewById(R.id.buttonBorrar);
        }

        public void asignarDatos(String datos){
            dato.setText(datos);
        }

    }
}
