package com.example.fernandoambrosio.devask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoUsuario;

import com.example.fernandoambrosio.devask.tipos.Usuario;


public class MainActivity extends AppCompatActivity {
    private AccesoUsuario acceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        acceso = new AccesoUsuario(this);
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registro);

        // Botón de salida (final de la aplicación)
        Button boton_salida = (Button)findViewById(R.id.buttonCancelar);
        boton_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        Button boton_ingresar = (Button)findViewById(R.id.buttonAceptar);
        boton_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.buttonAceptar) {

                    EditText a = (EditText)findViewById(R.id.tUsuario);
                    String str = a.getText().toString();

                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    intent.putExtra("nombre", str);
                    finish();
                    Usuario u = new Usuario();
                    u.setNombre(str);
                    acceso.insert(u);
                    startActivity(intent);
                }
            }
        });




    }
}
