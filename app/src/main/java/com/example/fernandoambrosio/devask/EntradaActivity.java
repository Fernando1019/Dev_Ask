package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private final int DURACION_SPLASH = 5000; // 5 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.entrada);


        new Handler().postDelayed(new Runnable(){
            public void run(){
               pasarDeActividad();
                finish();
            };
        }, DURACION_SPLASH);

    }

    private void pasarDeActividad(){
        String a = this.leerArchivo();
        if (a.compareTo("n")==0){
            escribirArchivo();
            // Cuando pasen los 5 segundos, pasamos a la actividad principal de la aplicación
            Intent intent = new Intent(EntradaActivity.this, Slide.class);
            startActivity(intent);
        }
        else {
            // Cuando pasen los 5 segundos, pasamos a la actividad principal de la aplicación
            Intent intent = new Intent(EntradaActivity.this, Menu.class);
            startActivity(intent);
        }

    }
    private void escribirArchivo(){
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("existencia.txt", Context.MODE_PRIVATE));

            fout.write("True");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }
    private String leerArchivo(){
        String texto="";
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("existencia.txt")));

            texto = fin.readLine();
            fin.close();
        }
        catch (Exception ex)
        {
            return("n");
        }
        return texto;
    }

}
