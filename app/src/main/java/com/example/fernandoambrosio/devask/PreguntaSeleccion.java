package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.Logica.Juego;
import com.example.fernandoambrosio.devask.Logica.Musica;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 24/04/2016.
 */
public class PreguntaSeleccion  extends AppCompatActivity {
    private Context contexto;
    private  Musica musica;
    private Button respuesta1, respuesta2,respuesta3, btPausaFv, btDetenerVf;;
    private String respuestaCorrecta;
    private String resp1, resp2, resp3;
    private int cantidad,correctas, categoria;
    private String[] respuestas;
    private TextView cantidadView, cantidadCorrectas, txtCronoSeleccion, txtVSeleccion;
    private CountDownTimer crono;
    private  boolean pausado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pregunta_seleccion);
        pausado=true;
        txtVSeleccion = (TextView) this.findViewById(R.id.txtVSeleccion);
        respuesta1 = (Button) this.findViewById(R.id.buttonSelec1);
        respuesta2 = (Button) this.findViewById(R.id.buttonSelec2);
        respuesta3 = (Button) this.findViewById(R.id.buttonSelec3);
        cantidadView= (TextView) this.findViewById(R.id.txCantidadSel);
        cantidadCorrectas = (TextView) this.findViewById(R.id.txCorrectasSeleccion);
        txtCronoSeleccion= (TextView) this.findViewById(R.id.txtCronoSeleccion);
        btDetenerVf = (Button) this.findViewById(R.id.btStopSel);
        btPausaFv = (Button)this.findViewById(R.id.btPausaSel);
         musica = new Musica();
        crono =  new CountDownTimer(9000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCronoSeleccion.setText(String.valueOf(millisUntilFinished / 1000) );
            }

            public void onFinish() {
                musica.reproducirError(contexto);
                mandarNuevoJuego();
            }
        }.start();
        Bundle bundle = getIntent().getExtras();
        this.respuestaCorrecta= bundle.getString("correcta");
        categoria = bundle.getInt("idCategoria");
        cantidad= Integer.valueOf(bundle.getString("cantidad"));
        cantidadView.setText(String.valueOf(cantidad)+"/10");
        txtVSeleccion.setText(bundle.getString("pregunta"));

        correctas= Integer.valueOf(bundle.getString("correctas"));
        cantidadCorrectas.setText(String.valueOf(correctas));

        respuestas = new String[3];
        respuestas[0]= bundle.getString("respuesta1");
        respuestas[1]= bundle.getString("respuesta2");
        respuestas[2]= bundle.getString("respuesta3");

        Random random = new Random();
        resp1= respuestas[0];
        resp2= respuestas[1];
        resp3=respuestas[2];
        respuesta1.setText(resp1);
        respuesta2.setText(resp2);
        respuesta3.setText(resp3);
        contexto= this;
        respuesta1.setOnClickListener(  new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                musica.reproducirSeleccion(contexto);
                verificar(String.valueOf(respuesta1.getText()));
                }});

        respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musica.reproducirSeleccion(contexto);
                verificar(String.valueOf(respuesta2.getText()));
            }
        });

        respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musica.reproducirSeleccion(contexto);
                verificar(String.valueOf(respuesta3.getText()));
            }
        });
        btPausaFv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pausado) {
                    crono.start();
                    pausado=false;
                }
                else{
                    crono.cancel();
                    pausado=true;
                }
            }
        });
        btDetenerVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }

    private void verificar(String respuesta){
        crono.cancel();
        if (respuesta.compareTo(this.respuestas[Integer.valueOf(this.respuestaCorrecta)])==0){
            correctas++;
            Toast toast = Toast.makeText(this,"correcto",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            musica.reproducirCorrecto(contexto);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(this,"incorrecto",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            musica.reproducirError(contexto);
            toast.show();
        }
       this.mandarNuevoJuego();

    }
    private void mandarNuevoJuego(){
        if(cantidad<10){
            System.out.println("jueoo");
            System.out.println(cantidad);
            jugar();
            overridePendingTransition(R.anim.izquierda_entrada,  R.anim.izquierda_salida);
            finish();
        }
        else{
            InterfazJuego interfazJuego = new InterfazJuego(this);
            interfazJuego.seleccionarJuego(this.cantidad, this.correctas, this.categoria);
            overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
            finish();
        }
    }

    public  void jugar() {
        InterfazJuego interfazJuego = new InterfazJuego(this);
        interfazJuego.seleccionarJuego(this.cantidad, this.correctas, this.categoria);
        finish();
    }

}
