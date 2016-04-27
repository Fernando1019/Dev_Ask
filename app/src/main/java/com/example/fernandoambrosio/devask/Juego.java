package com.example.fernandoambrosio.devask;

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
    public Juego(Context contexto){
        acceso = new AccesoJuego(contexto);

    }
    public PreguntaVF crearPreguntaVf(){
        int cantidadIds= acceso.cantidadDatosTabla("VF");
        int numero = numeroRandom(cantidadIds);
        PreguntaVF fv =  acceso.getPregutaVF(numero);
        System.out.println(fv.getRespuesta());
        return fv;
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
        int nume = numeroRandom(3);
        respuesta[nume]= pregunta.getRespuesta();
        multiple.setCorrecta(nume);
        int i=0;
        do{
            int num = numeroRandom(cantidadIds);
            if(num!=0) {
                PreguntaDirectaTipo preguntaD = acceso.getPreguntaTipo(num);
                System.out.println(String.valueOf(i));
                if (respuesta[nume].compareTo(preguntaD.getRespuesta()) != 0) {
                    if(i!=nume) {
                        respuesta[i] = preguntaD.getRespuesta();
                    }
                    i++;
                }
            }
        }while(i<3);
        multiple.setRespuesta(respuesta);
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
