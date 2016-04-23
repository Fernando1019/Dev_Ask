package com.example.fernandoambrosio.devask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoUsuario;
import com.example.fernandoambrosio.devask.baseDeDatos.DatabaseHelper;

/**
 * Created by Fernando Ambrosio on 19/04/2016.
 */
public class Menu extends AppCompatActivity {
    private AccesoUsuario acceso;
    DatabaseHelper base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu);
        acceso = new AccesoUsuario(this);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        String nombre =acceso.getUsuario();
        String textoAnterior = (String) textView2.getText();
        textView2.setText(textoAnterior+" "+nombre);
    }

}
