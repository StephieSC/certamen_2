package com.example.stephie.certamen2.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stephie.certamen2.presentador.MainPresentadorImpl;
import com.example.stephie.certamen2.R;
import com.example.stephie.certamen2.modelo.Datos;
import com.example.stephie.certamen2.vista.recycler.RepositorioAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton1 = (Button) findViewById(R.id.buscar);

        boton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText usuario = (EditText) findViewById(R.id.usuario);
                Intent boton1  = new Intent(MainActivity.this,MainPresentadorImpl.class);
                Bundle b = new Bundle();
                b.putString("NOMBRE",usuario.getText().toString());
                //Añadimos la información al intent
                boton1.putExtras(b);
                startActivity(boton1);
            }
        });
    }

    public static class RepositoriosActivity extends AppCompatActivity {

        private RecyclerView recycler;
        private List<Datos> items = new ArrayList();
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager lManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_repositorios);

            // Obtener el Recycler
            setContentView(R.layout.activity_repositorios);
            recycler= (RecyclerView)findViewById(R.id.reciclador);
            recycler.setHasFixedSize(true);
            // Crear un nuevo adaptador
            adapter = new RepositorioAdapter(items);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
            recycler.setLayoutManager(layoutManager);
            recycler.setItemAnimator(new DefaultItemAnimator());
            recycler.setAdapter(adapter);



            //notificar que los elementos cambiaron
            adapter.notifyDataSetChanged();

        }
    }
}
