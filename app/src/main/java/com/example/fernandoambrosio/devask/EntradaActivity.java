package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Fernando Ambrosio on 16/04/2016.
 */
public class EntradaActivity extends  AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 6000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.entrada);


        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(EntradaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);

    }


}
