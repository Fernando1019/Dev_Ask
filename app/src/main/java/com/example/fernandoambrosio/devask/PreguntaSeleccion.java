package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private ProgressBar barraDeProgreso;
    private  Musica musica;
    private Button respuesta1, respuesta2,respuesta3, btPausaFv, btDetenerVf;;
    private String respuestaCorrecta;
    private String resp1, resp2, resp3;
    private int cantidad,correctas, categoria;
    private String[] respuestas;
    private TextView cantidadView, cantidadCorrectas, txtCronoSeleccion, txtVSeleccion;
    private CountDownTimer crono;
    private  boolean pausado;
    public static final int segundos=16;
    public static final int milisegundos= segundos*1000;
    public static final int delay= 2;
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

        barraDeProgreso =(ProgressBar)findViewById(R.id.barraDeProgresoSel);
        barraDeProgreso.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        barraDeProgreso.setMax(maximoProgreso());
         musica = new Musica();

        Bundle bundle = getIntent().getExtras();
        this.respuestaCorrecta= bundle.getString("correcta");
        categoria = bundle.getInt("idCategoria");
        cantidad= Integer.valueOf(bundle.getString("cantidad"));

        cantidadView.setText(String.valueOf(cantidad)+"/10");
        txtVSeleccion.setText(bundle.getString("pregunta"));

        correctas= Integer.valueOf(bundle.getString("correctas"));
        contexto= this;
        if(cantidad>10){
            InterfazJuego interfaz = new InterfazJuego(contexto);
            interfaz.registrar(this.correctas,this.categoria);
            overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
            finish();
        }
        cantidadCorrectas.setText(String.valueOf(correctas));
        crono =  new CountDownTimer(16000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCronoSeleccion.setText(String.valueOf(millisUntilFinished / 1000) );
                barraDeProgreso.setProgress(establecer_progreso(millisUntilFinished));
            }

            public void onFinish() {
                musica.reproducirError(contexto);
                mandarNuevoJuego();
            }
        }.start();
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

        btDetenerVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crono.cancel();
                InterfazJuego juego = new InterfazJuego(contexto);
                juego.cancelar();
                overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
                finish();
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
    }
    private int establecer_progreso(long milisegundos){
        return (int) ((this.milisegundos-milisegundos)/1000);

    }
    private int maximoProgreso(){
        return segundos-delay;
    }

}
