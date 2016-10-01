package com.example.stephie.certamen2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainListaActivity extends AppCompatActivity {

    // Atributos
    ListView listView;
    ArrayAdapter adapter;
    Datos datos;
    static String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Localizar los controles
        TextView textoUsuario = (TextView)findViewById(R.id.textoUsuario);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        this.usr=bundle.getString("NOMBRE");
        textoUsuario.setText("Lista de repositorios del usuario " + bundle.getString("NOMBRE"));

        // Obtener instancia de la lista
        listView = (ListView) findViewById(R.id.listView);

        // Crear adaptador y setear
       adapter = new DatosAdapter(this,0);


        if (adapter != null) {
            listView.setAdapter(adapter);
        } else {
            Intent boton1 = new Intent(MainListaActivity.this, Error.class);
            startActivity(boton1);
        }


    }

   public void onItemClick(AdapterView<?> parent, View view, int pos) {
        Uri uri = Uri.parse(adapter.get(pos).getURL());

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public static String getUSR(){return usr;}
}




