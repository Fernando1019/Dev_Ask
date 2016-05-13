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
        PreguntaOpcionMultiple multiple = new PreguntaOpcionMultiple();
        int[] cantidadIds= acceso.cantidadDatosTabla("Directa", idCategoria);
        int[] idPreguntas = aleatorio.tresNumerosAleatorios(cantidadIds.length);
        int idCorrecta =aleatorio.numero();
        multiple.setContexto(SeleccionarPregunta(cantidadIds[idPreguntas[idCorrecta]]));
        multiple.setCorrecta(idCorrecta);
        multiple.setRespuesta(respuestas(idPreguntas));
        return multiple;
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

    public int seleccionarIdCategoria(String categoria){
        int idCategoria= acceso.seleccionarIdCategoria(categoria);
        return idCategoria;
    }
    public int[] seleccionarLogros(){
        int[] retorno = new int[3];
        int[] cantidades= acceso.CantidadPreguntas();
        retorno[0]=cantidades[0];
        retorno[1]=cantidades[1];
        retorno[2]=cantidades[1]-cantidades[0];
        return retorno;
    }
    private String SeleccionarPregunta(int id){
        PreguntaDirectaTipo preg=acceso.getPreguntaTipo(id);
        return preg.getContexto();

    }
    private String[] respuestas(int[] ids){
        String[] retRespuestas= new String[3];
        int i =0;
        for(int pregunta:ids){
            PreguntaDirectaTipo preg=acceso.getPreguntaTipo(pregunta);
            retRespuestas[i]=preg.getRespuesta();
            i++;
        }
        return retRespuestas;
    }
}
