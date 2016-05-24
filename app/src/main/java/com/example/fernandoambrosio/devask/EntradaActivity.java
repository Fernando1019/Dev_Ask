package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Created by Fernando Ambrosio on 16/04/2016.
 */
public class EntradaActivity extends  AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 4000; // 4 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.entrada);


        new Handler().postDelayed(new Runnable(){
            public void run(){
            if (leerArchivo()){
                iniciarActivity();
            }
                else{
                escribirArchivo();
                iniciarSlide();
            }
            };
        }, DURACION_SPLASH);


    }
    private boolean leerArchivo(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("prueba_int.txt")));

            String texto = fin.readLine();
            fin.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    private void escribirArchivo(){
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));

            fout.write("Texto de prueba.");
            fout.close();
        }
        catch (Exception ex)
        {
        }
    }
    private void iniciarActivity(){
        Intent intent = new Intent(EntradaActivity.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_entrada,R.anim.zoom_salida);
        finish();
    }

    private void iniciarSlide(){
        Intent intent = new Intent(EntradaActivity.this, Slide.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_entrada,R.anim.zoom_salida);
        finish();
    }

}
