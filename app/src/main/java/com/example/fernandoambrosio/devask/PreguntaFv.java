package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import org.w3c.dom.Text;

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
    private int cantidad,correctas;
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
        preguntaFV.setText(bundle.getString("pregunta"));
        respuesta= bundle.getString("respuesta");
        cantidad= Integer.valueOf(bundle.getString("cantidad"));
        cantidadView.setText(String.valueOf(cantidad)+"/10");
        correctas= Integer.valueOf(bundle.getString("correctas"));
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
        cantidad++;
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
            intent.putExtra("respuesta",directa.getRespuesta());
        }
        if (numero == 3) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple();
            intent = new Intent(this, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            System.out.println(multiple.getCorrecta());
            intent.putExtra("correcta",String.valueOf(multiple.getCorrecta()));

        }
        intent.putExtra("cantidad",String.valueOf(cantidad));
        intent.putExtra("correctas",String.valueOf(correctas));
        startActivity(intent);
        finish();
    }
}


