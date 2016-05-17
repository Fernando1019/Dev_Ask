package com.example.fernandoambrosio.devask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fernandoambrosio.devask.Logica.DataSource;
import com.example.fernandoambrosio.devask.tipos.Logro;
import com.example.fernandoambrosio.devask.tipos.LogroArrayAdapter;

import java.util.List;

/**
 * Created by josueChaqui on 17/05/2016.
 */
public class Historial extends AppCompatActivity {
    private ListView lista;
    private ArrayAdapter<String> adaptador;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.entrada);
        lista = (ListView)findViewById(R.id.listHistorial);
        DataSource dataSource = new DataSource();
        dataSource.seleccioonarLogros(this);

        adaptador = new LogroArrayAdapter<>(
                this,dataSource.LOGROS
                );
        lista.setAdapter(adaptador);
    }
}
