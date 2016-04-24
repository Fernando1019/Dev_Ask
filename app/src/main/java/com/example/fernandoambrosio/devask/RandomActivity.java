package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

/**
 * Created by Fernando Ambrosio on 23/04/2016.
 */
public class RandomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.random);


   }

    public void generar(View view) {
        Random random = new Random();
        int numero = random.nextInt(3) + 1;
        //TextView textView = (TextView)findViewById(R.id.textView5);
        //String s = String.valueOf(numero);
        //textView.setText(s);


        if (numero == 1) {
            Intent intent = new Intent(RandomActivity.this, PreguntaFv.class);
            startActivity(intent);
            finish();
        }
        if (numero == 2) {
            Intent intent = new Intent(RandomActivity.this, PreguntaDirecta.class);
            startActivity(intent);
            finish();
        }
        if (numero == 3) {
            Intent intent = new Intent(RandomActivity.this, PreguntaSeleccion.class);
            startActivity(intent);
            finish();
        }
    }
}
