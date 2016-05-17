package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.Logica.Juego;

/**
 * Created by josueChaqui on 19/04/2016.
 */
public class RankingActivity extends AppCompatActivity {
    private TextView cantidadPreg, txCorrectas, txincorrectas, textView4;
    private Button aceptar, compartir;
    private int idCategoria, correctas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ranking);
        txCorrectas= (TextView) this.findViewById(R.id.txCorrectas);
        txincorrectas= (TextView) this.findViewById(R.id.txIncorrectas);
        cantidadPreg= (TextView) this.findViewById(R.id.txTotales);
        textView4= (TextView) this.findViewById(R.id.textView4);
        aceptar=(Button) this.findViewById(R.id.btAceptarRanking);
        compartir=(Button) this.findViewById(R.id.btCompartir);
        Juego juego = new Juego(this);
        //int[] cantidades = juego.seleccionarLogros();
        Bundle bundle = getIntent().getExtras();
        int categoria = bundle.getInt("idCategoria");
        correctas = bundle.getInt("correctas");
        idCategoria = bundle.getInt("idCategoria");
        txCorrectas.setText(String.valueOf(correctas));
        txCorrectas.setText(String.valueOf(10-correctas));
        cantidadPreg.setText(String.valueOf(10));
        textView4.setText(textView4.getText()+" "+categoria);


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingActivity.this, MainActivity.class);
                intent.putExtra("categoria",idCategoria);
                intent.putExtra("correctas",correctas);
                startActivity(intent);
                finish();
            }
        });
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "En la Categoria de (Jas App)");
                startActivity(Intent.createChooser(intent, "Share with"));
            }
        });
    }
}
