package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 24/04/2016.
 */
public class PreguntaDirecta  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pregunta_directa);
    }
    public  void jugar() {
        Juego juego = new Juego(this);
        Random random = new Random();
        int numero = random.nextInt(3) + 1;

        if (numero == 1) {
            PreguntaVF vf = juego.crearPreguntaVf();
            Intent intent = new Intent(PreguntaDirecta.this, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
            startActivity(intent);
            finish();
        }
        if (numero == 2) {
            PreguntaDirectaTipo directa = juego.crearPreguntaDirecta();
            Intent intent = new Intent(PreguntaDirecta.this, PreguntaDirecta.class);
            intent.putExtra("pregunta",directa.getContexto());
            intent.putExtra("pregunta",directa.getRespuesta());
            startActivity(intent);
            finish();
        }
        if (numero == 3) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple();
            Intent intent = new Intent(PreguntaDirecta.this, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            intent.putExtra("correcta",multiple.getCorrecta());
            startActivity(intent);
            finish();
        }
    }
}
