package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.Logica.Juego;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 23/04/2016.
 */
public class PreguntaFv extends AppCompatActivity {
    private TextView preguntaFV;
    private Button botonFalso;
    private  Button botonVerdadero;
    private TextView cantidadView;
    private String respuesta;
    private int cantidad,correctas, categoria;
    private TextView cantidadCorrectas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.preguntafv);
        preguntaFV = (TextView) this.findViewById(R.id.tvPreguntaFv);
        botonFalso = (Button) this.findViewById(R.id.buttonF);
        botonVerdadero = (Button) this.findViewById(R.id.buttonV);
        cantidadView= (TextView) this.findViewById(R.id.txCantidadFv);
        cantidadCorrectas = (TextView) this.findViewById(R.id.txCorrectasFv);
        Bundle bundle = getIntent().getExtras();
        categoria = bundle.getInt("idCategoria");
        respuesta= bundle.getString("respuesta");
        cantidad= Integer.valueOf(bundle.getString("cantidad"));
        correctas= Integer.valueOf(bundle.getString("correctas"));

        preguntaFV.setText(bundle.getString("pregunta"));
        cantidadView.setText(String.valueOf(cantidad)+"/10");
        cantidadCorrectas.setText(String.valueOf(correctas));

        botonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarPregunta("Falso");
            }
        });

        botonVerdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarPregunta("Verdadero");
            }
        });
    }

    public void verificarPregunta(String respuestaSeleccionada){

        if (this.respuesta.compareTo(respuestaSeleccionada)==0){
            correctas++;
            Toast toast = Toast.makeText(this,"correcto",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(this,"incorrecto",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
        if(cantidad<10){
            jugar();
        }
        else{
            Juego juego = new Juego(this);
            juego.actualizarLogro(10, Integer.valueOf(this.correctas));
            Intent intent = new Intent(this,RankingActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public  void jugar() {
        InterfazJuego interfazJuego = new InterfazJuego(this);
        interfazJuego.seleccionarJuego(this.cantidad, this.correctas, this.categoria);
        finish();
    }
}


