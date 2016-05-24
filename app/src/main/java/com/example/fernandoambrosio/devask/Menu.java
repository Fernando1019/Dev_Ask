package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.baseDeDatos.DatabaseHelper;

/**
 * Created by Fernando Ambrosio on 19/04/2016.
 */
public class Menu extends AppCompatActivity {
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
                    Intent intent = new Intent(Menu.this, Historial.class);
                    overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
                    startActivity(intent);

                }
            }

        });

        Button boton_ayuda = (Button) findViewById(R.id.buttonAyuda);
        boton_ayuda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonAyuda) {
                    Intent intent = new Intent(Menu.this, Slide.class);
                    overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
                    startActivity(intent);

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