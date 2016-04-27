package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoUsuario;
import com.example.fernandoambrosio.devask.baseDeDatos.DatabaseHelper;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;
import com.example.fernandoambrosio.devask.tipos.Usuario;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 19/04/2016.
 */
public class Menu extends AppCompatActivity {
    private AccesoUsuario acceso;
    DatabaseHelper base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu);

        acceso = new AccesoUsuario(this);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        String nombre = acceso.getUsuario();
        String textoAnterior = (String) textView2.getText();
        textView2.setText(textoAnterior + " " + nombre);


        Button boton_puntos = (Button) findViewById(R.id.buttonPuntos);
        boton_puntos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonPuntos) {
                    Intent intent = new Intent(Menu.this, RankingActivity.class);
                    startActivity(intent);
                    finish();
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
        Juego juego = new Juego(this);
        Random random = new Random();
        int numero = random.nextInt(3) + 1;
        Intent intent = null;
        if (numero == 1) {
            PreguntaVF vf = juego.crearPreguntaVf();
            intent = new Intent(Menu.this, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
        }
        if (numero == 2) {
            PreguntaDirectaTipo directa = juego.crearPreguntaDirecta();
             intent = new Intent(Menu.this, PreguntaDirecta.class);
            intent.putExtra("pregunta",directa.getContexto());
            intent.putExtra("respuesta",directa.getRespuesta());
        }
        if (numero == 3) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple();
            intent = new Intent(Menu.this, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            System.out.println(multiple.getCorrecta());
            intent.putExtra("correcta",String.valueOf(multiple.getCorrecta()));

        }

        intent.putExtra("cantidad",String.valueOf(1));
        intent.putExtra("correctas",String.valueOf(0));
        startActivity(intent);
        finish();
    }
}