package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
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
 * Created by Fernando Ambrosio on 23/04/2016.
 */
public class PreguntaFv extends AppCompatActivity {
    private Context contexto;
    private Musica musica;
    private TextView preguntaFV, txtCronoFv;
    private Button botonFalso, botonVerdadero, btPausaFv, btDetenerVf;
    private TextView cantidadView;
    private String respuesta;
    private int cantidad,correctas, categoria;
    private TextView cantidadCorrectas;
    private CountDownTimer crono;
    private  boolean pausado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.preguntafv);
        pausado=true;
        preguntaFV = (TextView) this.findViewById(R.id.tvPreguntaFv);
        botonFalso = (Button) this.findViewById(R.id.buttonF);
        botonVerdadero = (Button) this.findViewById(R.id.buttonV);
        cantidadView= (TextView) this.findViewById(R.id.txCantidadFv);
        txtCronoFv= (TextView) this.findViewById(R.id.txtCronoFV);
        cantidadCorrectas = (TextView) this.findViewById(R.id.txCorrectasFv);
        btDetenerVf = (Button) this.findViewById(R.id.btStopVf);
        btPausaFv = (Button)this.findViewById(R.id.btPausaVf);
        musica = new Musica();
        Bundle bundle = getIntent().getExtras();
        categoria = bundle.getInt("idCategoria");
        respuesta= bundle.getString("respuesta");
        cantidad= Integer.valueOf(bundle.getString("cantidad"));
        correctas= Integer.valueOf(bundle.getString("correctas"));
        if(cantidad>10){
            InterfazJuego interfaz = new InterfazJuego(contexto);
            interfaz.registrar(this.correctas,this.categoria);
            overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
            finish();
        }

        preguntaFV.setText(bundle.getString("pregunta"));
        cantidadView.setText(String.valueOf(cantidad)+"/10");
        cantidadCorrectas.setText(String.valueOf(correctas));
        crono = new CountDownTimer(16000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCronoFv.setText(String.valueOf(millisUntilFinished / 1000) );
            }

            public void onFinish() {
                musica.reproducirError(contexto);
                mandarNuevoJuego();
            }
        }.start();
        btPausaFv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pausado) {
                    crono.start();
                    pausado=false;
                }
                else{
                    try {
                        crono.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausado=true;
                }
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
        botonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musica.reproducirSeleccion(contexto);
                verificarPregunta("Falso");
            }
        });

        botonVerdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musica.reproducirSeleccion(contexto);
                verificarPregunta("Verdadero");
            }
        });
        contexto= this;
    }

    public void verificarPregunta(String respuestaSeleccionada){
        crono.cancel();
        if (this.respuesta.compareTo(respuestaSeleccionada)==0){
            correctas++;
            Toast toast = Toast.makeText(this,"correcto",Toast.LENGTH_SHORT);
            musica.reproducirCorrecto(contexto);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
        else{
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
            InterfazJuego interfaz = new InterfazJuego(contexto);
            interfaz.registrar(this.correctas,this.categoria);
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


