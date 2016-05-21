package com.example.fernandoambrosio.devask.Logica;

import android.content.Context;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoJuego;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Juego {
    private AccesoJuego acceso;
    private Aleatorio aleatorio;
    public Juego(Context contexto){
        acceso = new AccesoJuego(contexto);
        aleatorio = new Aleatorio();

    }
    public PreguntaVF crearPreguntaVf(int idCategoria){
        int[] cantidadIds= acceso.cantidadDatosTabla("VF",idCategoria);
        int numero = aleatorio.numero(cantidadIds.length);
        PreguntaVF fv =  acceso.getPregutaVF(cantidadIds[numero]);
        System.out.println(fv.getRespuesta());
        return fv;
    }
    public PreguntaOpcionMultiple crearPreguntaOpcionMultiple(int idCategoria){
        int[] cantidadIds= acceso.cantidadDatosTabla("Directa",idCategoria);
        int numero = aleatorio.numero(cantidadIds.length);
        PreguntaOpcionMultiple multiple = new PreguntaOpcionMultiple();
       multiple = acceso.getPreguntaTipo(numero);
        return multiple;
    }


    public int seleccionarIdCategoria(String categoria){
        int idCategoria= acceso.seleccionarIdCategoria(categoria);
        return idCategoria;
    }
    public String seleccionarNombreCategoria(int idCategoria){
        String nombre = acceso.seleccionarNombreCategoria(idCategoria);
        return nombre;

    }
    public int[] seleccionarLogros(){
        int[] retorno = new int[3];
        int[] cantidades= acceso.CantidadPreguntas();
        retorno[0]=cantidades[0];
        retorno[1]=cantidades[1];
        retorno[2]=cantidades[1]-cantidades[0];
        return retorno;
    }


    public boolean  insertarJugador(String nombre, int correctas, int categoria ){
        if (nombre !=""){
            acceso.insertarJugador(nombre,correctas, categoria);
            return  true;
        }
        else{
            return false;
        }
    }
}
