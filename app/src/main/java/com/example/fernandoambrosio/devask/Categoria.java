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
import android.widget.ViewSwitcher;
import android.os.Handler;

import com.example.fernandoambrosio.devask.Logica.Aleatorio;
import com.example.fernandoambrosio.devask.Logica.InterfazJuego;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by josueChaqui on 13/05/2016.
 */
public class Categoria extends AppCompatActivity {
    private int[] galeria = {R.drawable.cultura, R.drawable.gastronomia, R.drawable.geografia,
            R.drawable.musica, R.drawable.personajes,};
    private String[] categorias={"Tradiciones","Gastronomia","Geografia","Musica","personajes"};
    private static final Integer DURACION = 200;
    private ImageView imagen1;
    private ImageView imagen2;
    private ImageView imagen3;
    private ImageView imagen4;
    private ImageView imagen5;
    private ImageSwitcher imageSwitcher;
    private Timer timer = null;
    private int position;
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
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(Categoria.this);
            }
        });


        //Transiciones
        Animation entrada = AnimationUtils.loadAnimation(this, R.anim.entrada);
        Animation salida = AnimationUtils.loadAnimation(this, R.anim.salida);
        imageSwitcher.setInAnimation(entrada);
        imageSwitcher.setOutAnimation(salida);

        //Seleccion de Imagenes para enviar a cada Categoria
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria(categorias[0]);
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria(categorias[1]);
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria(categorias[2]);
            }
        });
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria(categorias[3]);
            }
        });
        imagen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarCategoria(categorias[4]);
            }
        });
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        stop();
                        EnviarCategoria(categorias[position]);
                    }
                },3000);
            }
        });
    }
    public void start() {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    private void EnviarCategoria(String categoria){
        InterfazJuego interfazJuego = new InterfazJuego(this);
        interfazJuego.SeleccionarCategoria(categoria);
        finish();
    }
    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(galeria[position]);
                        position++;
                        if (position == galeria.length) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURACION);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

    }
}
