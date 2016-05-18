package com.example.fernandoambrosio.devask.Logica;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;

import com.example.fernandoambrosio.devask.R;

/**
 * Created by josueChaqui on 13/05/2016.
 */
public class Musica {
    private CountDownTimer crono;
    private MediaPlayer reproductor;
    public void reproducirSeleccion(Context context){
        if(reproductor.isPlaying()) {
            reproductor.pause();
        }
        reproductor = MediaPlayer.create(context, R.raw.seleccion);

        reproductor.start();
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
               reproductor.stop();
            }
        },3000);
    }
    public void reproducirError( Context context){
        if(reproductor.isPlaying()) {
            reproductor.pause();
        }
        reproductor = MediaPlayer.create(context, R.raw.fallo);
        reproductor.start();
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                reproductor.stop();
            }
        },3000);
    }
    public void reproducirCorrecto(Context context){
        if(reproductor.isPlaying()) {
            reproductor.pause();
        }
        reproductor = MediaPlayer.create(context,R.raw.ganar);
        reproductor.start();

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                reproductor.stop();
            }
        },3000);
    }
}
