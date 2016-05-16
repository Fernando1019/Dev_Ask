package com.example.fernandoambrosio.devask;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.example.fernandoambrosio.devask.Logica.Aleatorio;
import com.example.fernandoambrosio.devask.Logica.InterfazJuego;

/**
 * Created by josueChaqui on 13/05/2016.
 */
public class Categoria extends AppCompatActivity {
    private ImageView imagen1;
    private ImageView imagen2;
    private ImageView imagen3;
    private ImageView imagen4;
    private ImageView imagen5;
    private ImageSwitcher imageSwitcher;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.categorias);
        imagen1 = (ImageView)  this.findViewById(R.id.imageView7);
        imagen2 = (ImageView)  this.findViewById(R.id.imageView8);
        imagen3 = (ImageView)  this.findViewById(R.id.imageView9);
        imagen4 = (ImageView)  this.findViewById(R.id.imageView10);
        imagen5 = (ImageView)  this.findViewById(R.id.imageView11);

        //Transiciones
        Animation entrada = AnimationUtils.loadAnimation(this, R.anim.entrada);
        Animation salida = AnimationUtils.loadAnimation(this, R.anim.salida);
        imageSwitcher.setInAnimation(entrada);
        imageSwitcher.setOutAnimation(salida);

        //Seleccion de Imagenes para enviar a cada Categoria
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria("Tradiciones");
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria("Gastronomia");
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria("Geografia");
            }
        });
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria("Musica");
            }
        });
        imagen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria("personajes");
            }
        });
    }

    private void EnviarCategoria(String categoria){
        InterfazJuego interfazJuego = new InterfazJuego(this);
        interfazJuego.SeleccionarCategoria(categoria);
        finish();
    }
}
