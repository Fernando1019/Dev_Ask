package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by Fernando Ambrosio on 24/04/2016.
 */
public class PreguntaDirecta  extends AppCompatActivity {
    private EditText eTxtRespuesta;
    private TextView txtViewPregDirecta;
    private Button  aceptarDirecta;
    private String respuesta;
    private int cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pregunta_directa);
        txtViewPregDirecta = (TextView) this.findViewById(R.id.TxtViewPregDirecta);
        Bundle bundle = getIntent().getExtras();
        txtViewPregDirecta.setText(bundle.getString("pregunta"));
        respuesta= bundle.getString("respuesta");
        aceptarDirecta = (Button) this.findViewById(R.id.btAceptarDirecta);
        aceptarDirecta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificarPregunta();
                jugar();

            }
        });
    }
    private void VerificarPregunta(){
        String respuestaUsuario =  String.valueOf(eTxtRespuesta.getText());
        if (respuestaUsuario.compareTo(respuesta)==0){

        }
    }
    public  void jugar() {
        Juego juego = new Juego(this);
        Random random = new Random();
        Intent intent = null;
        int numero = random.nextInt(3) + 1;

        if (numero == 1) {
            PreguntaVF vf = juego.crearPreguntaVf();
             intent = new Intent(PreguntaDirecta.this, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
        }
        if (numero == 2) {
            PreguntaDirectaTipo directa = juego.crearPreguntaDirecta();
             intent = new Intent(PreguntaDirecta.this, PreguntaDirecta.class);
            intent.putExtra("pregunta",directa.getContexto());
            intent.putExtra("pregunta",directa.getRespuesta());
        }
        if (numero == 3) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple();
             intent = new Intent(PreguntaDirecta.this, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            intent.putExtra("correcta",multiple.getCorrecta());

        }
        startActivity(intent);
        finish();
    }
}
