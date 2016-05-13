package com.example.fernandoambrosio.devask.Logica;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.fernandoambrosio.devask.R;

/**
 * Created by josueChaqui on 13/05/2016.
 */
public class Musica {
    private MediaPlayer reproductor;
    public void reproducirSeleccion(Context context){
        reproductor = MediaPlayer.create(context, R.raw.seleccion);
        reproductor.start();
    }
    public void reproducirError( Context context){
        reproductor = MediaPlayer.create(context, R.raw.fallo);
        reproductor.start();
    }
    public void reproducirCorrecto(Context context){
        reproductor = MediaPlayer.create(context,R.raw.ganar);
        reproductor.start();
    }
}
