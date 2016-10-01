package com.example.stephie.certamen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent boton1  = new Intent(MainActivity.this,MainListaActivity.class);
                Bundle b = new Bundle();
                b.putString("NOMBRE",usuario.getText().toString());
                //Añadimos la información al intent
                boton1.putExtras(b);
                startActivity(boton1);
            }
        });
    }
}
