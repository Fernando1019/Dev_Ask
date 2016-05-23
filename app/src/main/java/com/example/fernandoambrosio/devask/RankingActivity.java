package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.Logica.Juego;
import com.example.fernandoambrosio.devask.baseDeDatos.AccesoJuego;

/**
 * Created by josueChaqui on 19/04/2016.
 */
public class RankingActivity extends AppCompatActivity {
    private Context contexto;
    private TextView cantidadPreg, txCorrectas, txincorrectas, textView4;
    private Button aceptar, compartir, regresar;
    private int idCategoria, correctas;
    AccesoJuego acceso;
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
        regresar =(Button)findViewById(R.id.buttonRegresar);
        Juego juego = new Juego(this);
        //int[] cantidades = juego.seleccionarLogros();
        Bundle bundle = getIntent().getExtras();
        acceso = new AccesoJuego(this);

        correctas = bundle.getInt("correctas");
        idCategoria = bundle.getInt("idCategoria");
        txCorrectas.setText(String.valueOf(correctas));
        txincorrectas.setText(String.valueOf(10-correctas));
        cantidadPreg.setText(String.valueOf(10));
        textView4.setText(textView4.getText()+" "+acceso.seleccionarNombreCategoria(idCategoria));
        contexto=this;
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterfazJuego juego = new InterfazJuego(contexto);
                juego.cancelar();
                overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
                finish();

            }
        });
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
                intent.putExtra(Intent.EXTRA_TEXT, "En la Categoria de "+acceso.seleccionarNombreCategoria(idCategoria)+
                        " de Guatemala he respondido "+String.valueOf(correctas)+"/10 preguntas correctas."+
                        "\n Para aprender más sobre Guatemala descarga JasApp en Play Store.");
                startActivity(Intent.createChooser(intent, "Share with"));
            }
        });
    }
}
