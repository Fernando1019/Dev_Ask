package com.jasapp.fernandoambrosio.devask.tipos;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Logro {
    private String jugador;
    private int respuestasCorrectas;
    private String categorias;

    public Logro(String jugador, int respuestasCorrectas, String categoria) {
        this.jugador = jugador;
        this.respuestasCorrectas = respuestasCorrectas;
        this.categorias=categoria;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(int respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString(){return categorias+","+ jugador+"correctas: "+String.valueOf(respuestasCorrectas);
    }
}