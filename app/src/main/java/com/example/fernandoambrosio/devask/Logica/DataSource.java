package com.example.fernandoambrosio.devask.Logica;

/**
 * Created by josueChaqui on 17/05/2016.
 */
import android.content.Context;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoJuego;
import com.example.fernandoambrosio.devask.tipos.Logro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataSource {

    public List LOGROS = new ArrayList<Logro>();

    public void seleccioonarLogros(Context contexto){
        AccesoJuego acceso = new AccesoJuego(contexto);
        ArrayList<Logro>  logros = acceso.seleccionarLogros();
        Iterator<Logro> logro = logros.iterator();
        while(logro.hasNext()){
            Logro elemento = logro.next();
            LOGROS.add(new Logro(elemento.getJugador(),elemento.getRespuestasCorrectas()));
        }
    }

}