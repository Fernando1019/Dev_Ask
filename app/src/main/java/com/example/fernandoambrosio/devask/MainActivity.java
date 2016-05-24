package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;


public class MainActivity extends AppCompatActivity {
    private EditText tUsuario;
    private int idCategoria,correctas;
    private Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registro);
        // Botón de salida (final de la aplicación)
        Bundle bundle = getIntent().getExtras();
        idCategoria = bundle.getInt("categoria");
        correctas = bundle.getInt("correctas");
        tUsuario =(EditText)findViewById(R.id.tUsuario);
        contexto= this;
        Button cancelar = (Button)findViewById(R.id.buttonCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto,Historial.class);
                overridePendingTransition(R.anim.izquierda_entrada,  R.anim.izquierda_salida);
                startActivity(intent);
                finish();
            }
        });

      Button boton_ingresar = (Button)findViewById(R.id.buttonAceptar);
        boton_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.buttonAceptar) {
                    InterfazJuego juego = new InterfazJuego(contexto);
                    juego.insertarJugador(correctas,tUsuario.getText().toString(),idCategoria);
                    overridePendingTransition(R.anim.izquierda_entrada,  R.anim.izquierda_salida);
                    finish();
                }
            }
        });





    }
}
