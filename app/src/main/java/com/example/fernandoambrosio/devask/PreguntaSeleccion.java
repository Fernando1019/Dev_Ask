package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 24/04/2016.
 */
public class PreguntaSeleccion  extends AppCompatActivity {
    private TextView txtVSeleccion;
    private Button respuesta1;
    private Button respuesta2;
    private Button respuesta3;
    private String respuestaCorrecta;
    private String resp1, resp2, resp3;
    private int cantidad,correctas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pregunta_seleccion);
        txtVSeleccion = (TextView) this.findViewById(R.id.txtVSeleccion);
        respuesta1 = (Button) this.findViewById(R.id.buttonSelec1);
        respuesta2 = (Button) this.findViewById(R.id.buttonSelec2);
        respuesta3 = (Button) this.findViewById(R.id.buttonSelec3);
        Bundle bundle = getIntent().getExtras();
        txtVSeleccion.setText(bundle.getString("pregunta"));
        respuestaCorrecta= bundle.getString("correcta");
        Random random = new Random();
        int numero = random.nextInt(3)+1;
        resp1= bundle.getString("respuesta"+String.valueOf(numero));
        numero = random.nextInt(3)+1;
        resp2= bundle.getString("respuesta"+String.valueOf(numero));
        numero = random.nextInt(3)+1;
        resp3= bundle.getString("respuesta"+String.valueOf(numero));
        respuesta1.setText(resp1);
        respuesta2.setText(resp2);
        respuesta3.setText(resp3);

    }
    public  void jugar() {
        Juego juego = new Juego(this);
        Random random = new Random();
        Intent intent = null;
        int numero = random.nextInt(3) + 1;

        if (numero == 1) {
            PreguntaVF vf = juego.crearPreguntaVf();
            intent = new Intent(this, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
        }
        if (numero == 2) {
            PreguntaDirectaTipo directa = juego.crearPreguntaDirecta();
            intent = new Intent(this, PreguntaDirecta.class);
            intent.putExtra("pregunta",directa.getContexto());
            intent.putExtra("pregunta",directa.getRespuesta());
        }
        if (numero == 3) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple();
            intent = new Intent(this, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            intent.putExtra("correcta",multiple.getCorrecta());

        }
        intent.putExtra("cantidad",String.valueOf(cantidad));
        intent.putExtra("correctas",String.valueOf(correctas));
        startActivity(intent);
        finish();
    }
}
