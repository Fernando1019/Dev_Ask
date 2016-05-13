package com.example.fernandoambrosio.devask;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.fernandoambrosio.devask.Logica.Aleatorio;

/**
 * Created by josueChaqui on 13/05/2016.
 */
public class Categoria extends AppCompatActivity {
    private ImageView imagen1;
    private ImageView imagen2;
    private ImageView imagen3;
    private ImageView imagen4;
    private ImageView imagen5;
    private Thread hilo = new  Thread(new Runnable() {
        Aleatorio aleatorio  = new Aleatorio();
        @Override
        public void run() {
            try {
                Thread.currentThread().sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int n= aleatorio.numeroMenosCero(5);
            switch (n){
                case 1:
                        imagen1.setImageResource(R.drawable.culturaos);
                        break;
                case 2:
                    imagen2.setImageResource(R.drawable.gastronomiaos);
                    break;
                case 3:
                    imagen3.setImageResource(R.drawable.geografiaos);
                    break;
                case 4:
                    imagen4.setImageResource(R.drawable.musicaos);
                    break;
                case 5:
                    imagen5.setImageResource(R.drawable.personajesos);
                    break;
            }
        }
    });
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
        hilo.start();
    }
}
