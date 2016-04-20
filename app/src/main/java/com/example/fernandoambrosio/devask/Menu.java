package com.example.fernandoambrosio.devask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fernando Ambrosio on 19/04/2016.
 */
public class Menu extends AppCompatActivity {

    DatabaseHelper base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu);


        //String nombre = getIntent().getStringExtra("nombre");
          //     TextView textView = (TextView)findViewById(R.id.usuario);
            //    textView.setText(nombre);

        //String nombre = getIntent().getStringExtra("nombre");
       // base = new DatabaseHelper(this);
        //String nombre = base.setNombre();
       // TextView textView = (TextView)findViewById(R.id.usuario);
        //textView.setText(nombre);
    }

}
