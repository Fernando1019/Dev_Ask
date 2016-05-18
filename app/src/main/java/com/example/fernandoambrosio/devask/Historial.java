package com.example.fernandoambrosio.devask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.fernandoambrosio.devask.Logica.DataSource;
import com.example.fernandoambrosio.devask.Logica.InterfazJuego;
import com.example.fernandoambrosio.devask.tipos.Logro;
import com.example.fernandoambrosio.devask.tipos.LogroArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josueChaqui on 17/05/2016.
 */
public class Historial extends AppCompatActivity {
    private ListView lista;
    private Button regresar;
    private Context contexto;
    private String[] array = {"lunes","martes","miercoles","jueves"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.historial);
        lista = (ListView)findViewById(R.id.listHistorial);
        DataSource datasource = new DataSource();
        datasource.seleccioonarLogros(this);

        LogroArrayAdapter<Logro> adaptador = new LogroArrayAdapter<Logro>(this,datasource.LOGROS);
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
        lista.setAdapter(adaptador);
        regresar =(Button)findViewById(R.id.buttonRegresaHistorial);
        contexto = this;
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterfazJuego juego = new InterfazJuego(contexto);
                juego.cancelar();
                overridePendingTransition(R.anim.zoom_entrada,  R.anim.zoom_salida);
                finish();

            }
        });
    }

}
