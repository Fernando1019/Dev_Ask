package com.jasapp.fernandoambrosio.devask.Logica;

import android.content.Context;

import com.jasapp.fernandoambrosio.devask.baseDeDatos.AccesoJuego;
import com.jasapp.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.jasapp.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.ArrayList;
import java.util.Iterator;

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
    public PreguntaVF crearPreguntaVf(int idCategoria, ArrayList<Integer> idsSeleccionados){
        int[] cantidadIds= acceso.cantidadDatosTabla("VF",idCategoria);
        int numero =seleccionarId(cantidadIds,idsSeleccionados);
        PreguntaVF fv =  acceso.getPregutaVF(cantidadIds[numero]);
        fv.setId(cantidadIds[numero]);
        return fv;
    }
    public PreguntaOpcionMultiple crearPreguntaOpcionMultiple(int idCategoria, ArrayList<Integer> idsSeleccionados){
        int[] cantidadIds= acceso.cantidadDatosTabla("Directa",idCategoria);
        int numero = seleccionarId(cantidadIds,idsSeleccionados);
        PreguntaOpcionMultiple multiple = new PreguntaOpcionMultiple();
        PreguntaOpcionMultiple nMultiple = new PreguntaOpcionMultiple();
        int[] respuestas = correcta();
       multiple = acceso.getPreguntaTipo(cantidadIds[numero]);
        multiple.setId(cantidadIds[numero]);
        nMultiple=multiple;
        String [] aRespuestas =multiple.getRespuesta();
        String[] nRespuestas =  new String[3];
        int i=0;
        nMultiple.setCorrecta(respuestas[i]);
        for(int n:respuestas){
            nRespuestas[n]=aRespuestas[i];
            i++;
        }
        nMultiple.setRespuesta(nRespuestas);
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



    public boolean  insertarJugador(String nombre, int correctas, int categoria ){
        if (nombre !=""){
            acceso.insertarJugador(nombre,correctas, categoria);
            return  true;
        }
        else{
            return false;
        }
    }
    private int seleccionarId(int[] cantidadIds, ArrayList<Integer> idsSeleccionados){
        while (true){
            boolean bandera= true;
            System.out.println(cantidadIds.length);
            int numero = aleatorio.numeroMenosCero(cantidadIds.length);
            Iterator<Integer> nombreIterator = idsSeleccionados.iterator();
            while(nombreIterator.hasNext()){
                int elemento = nombreIterator.next();
                if(elemento==cantidadIds[numero]){
                    bandera=false;
                    break;
                }

            }
            if (bandera) {
                return numero;
            }
        }
    }
    private int[] correcta(){
        int[] numeros = new int[3];
        int i=0;
        while(i<2){
            boolean bandera = true;
            int numero = aleatorio.numeroSinRestricciones();
            System.out.println("aa");
            System.out.println(numero);
            System.out.println(i);
            for(int n:numeros){
                if( n== numero){
                    bandera=false;
                    break;
                }
            }
            if(bandera){
                numeros[i]=numero;
                i++;
            }
        }
        return numeros;
    }
}
