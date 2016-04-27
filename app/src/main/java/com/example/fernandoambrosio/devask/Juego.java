package com.example.fernandoambrosio.devask;

import android.content.Context;

import com.example.fernandoambrosio.devask.baseDeDatos.AccesoJuego;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Juego {
    private AccesoJuego acceso;
    public Juego(Context contexto){
        acceso = new AccesoJuego(contexto);

    }
    public PreguntaVF crearPreguntaVf(){
        int cantidadIds= acceso.cantidadDatosTabla("VF");
        int numero = numeroRandom(cantidadIds);
        return acceso.getPregutaVF(numero);
    }
    public PreguntaDirectaTipo crearPreguntaDirecta(){
        int cantidadIds= acceso.cantidadDatosTabla("Directa");
        int numero = numeroRandom(cantidadIds);
        return acceso.getPreguntaTipo(numero);
    }
    public PreguntaOpcionMultiple crearPreguntaOpcionMultiple(){
        PreguntaOpcionMultiple multiple = new PreguntaOpcionMultiple();
        int cantidadIds= acceso.cantidadDatosTabla("Directa");
        int numero = numeroRandom(cantidadIds);
        PreguntaDirectaTipo pregunta =acceso.getPreguntaTipo(numero);
        multiple.setContexto(pregunta.getContexto());
        String[] respuesta = new String[3];
        respuesta[0]= pregunta.getRespuesta();
        multiple.setCorrecta(0);
        int i=1;
        do{
            int num = numeroRandom(cantidadIds);
            PreguntaDirectaTipo preguntaD = acceso.getPreguntaTipo(num);
            if(respuesta[i-1].compareTo(preguntaD.getRespuesta())!=0){
                    respuesta[i]=preguntaD.getRespuesta();
                    i++;
            }
        }while(i<3);
        return multiple;
    }
    private int numeroRandom(int cant){
        Random random = new Random();
        return random.nextInt(cant);
    }
    public void actualizarLogro(int cantidadPreguntas, int correctas){
        int[] cantidadesAnteriores = acceso.CantidadPreguntas();
        int nuevaCantidadCorrectas= cantidadesAnteriores[0]+correctas;
        int nuevaCantidadPreguntas= cantidadesAnteriores[1]+cantidadPreguntas;
        if (cantidadesAnteriores[0]==0){
            acceso.insertCantidades(nuevaCantidadCorrectas, nuevaCantidadPreguntas);
        }
        else {
            acceso.actualizarCantidades(nuevaCantidadCorrectas, nuevaCantidadPreguntas);
        }
    }
    public int[] seleccionarLogros(){
        int[] retorno = new int[3];
        int[] cantidades= acceso.CantidadPreguntas();
        retorno[0]=cantidades[0];
        retorno[1]=cantidades[1];
        retorno[2]=cantidades[1]-cantidades[0];
        return retorno;
    }
}
