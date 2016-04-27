package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by josueChaqui on 19/04/2016.
 */
public class RankingActivity extends AppCompatActivity {
    private TextView cantidadPreg;
    private TextView correctas;
    private TextView incorrectas;
    private Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ranking);
        correctas= (TextView) this.findViewById(R.id.txCorrectas);
        incorrectas= (TextView) this.findViewById(R.id.txIncorrectas);
        cantidadPreg= (TextView) this.findViewById(R.id.txTotales);
        aceptar=(Button) this.findViewById(R.id.btAceptarRanking);
        Juego juego = new Juego(this);
        int[] cantidades = juego.seleccionarLogros();
        correctas.setText(String.valueOf(cantidades[0]));
        incorrectas.setText(String.valueOf(cantidades[2]));
        cantidadPreg.setText(String.valueOf(cantidades[1]));
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
