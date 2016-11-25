package com.example.stephie.certamen2.presentador;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.stephie.certamen2.vista.Error;
import com.example.stephie.certamen2.R;
import com.example.stephie.certamen2.modelo.Datos;

import java.util.List;


public class MainPresentadorImpl extends AppCompatActivity {

    // Atributos
    RecyclerView recyclerView;
    ListaAdapter adapter;
    List<Datos> datos;
    static String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Localizar los controles
        TextView textoUsuario = (TextView)findViewById(R.id.usuario);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        this.usr=bundle.getString("NOMBRE");
        textoUsuario.setText("Lista de repositorios del usuario " + bundle.getString("NOMBRE"));

        // Obtener instancia de la lista
        recyclerView = (RecyclerView) findViewById(R.id.reciclador);

        // Crear adaptador y setear
       datos = new DatosAdapterImpl().getParse();
        adapter=  new ListaAdapter(datos);

        if (datos!= null && adapter != null) {
            recyclerView.setAdapter(adapter);
        } else {
            Intent boton1 = new Intent(MainPresentadorImpl.this, Error.class);
            startActivity(boton1);
        }


    }

   public void onItemClick(AdapterView<?> parent, View view, int pos) {
        Uri uri = Uri.parse(datos.get(pos).getURL());

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public static String getUSR(){return usr;}
}




