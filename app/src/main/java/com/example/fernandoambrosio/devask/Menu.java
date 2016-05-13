package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.Logica.Juego;
import com.example.fernandoambrosio.devask.baseDeDatos.AccesoUsuario;
import com.example.fernandoambrosio.devask.baseDeDatos.DatabaseHelper;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 19/04/2016.
 */
public class Menu extends AppCompatActivity {
    private AccesoUsuario acceso;
    DatabaseHelper base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("Menu");
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu);
        System.out.print("Meni");


        Button boton_puntos = (Button) findViewById(R.id.buttonPuntos);
        boton_puntos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonPuntos) {
                    Intent intent = new Intent(Menu.this, RankingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

        Button boton_jugar = (Button) findViewById(R.id.buttonJugar);
        boton_jugar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonJugar) {
                   jugar();
                }
            }

        });
    }
    public  void jugar() {
        InterfazJuego interfazJuego = new InterfazJuego(this);
        interfazJuego.abrirCategorias();
        finish();
    }
}