package com.jasapp.fernandoambrosio.devask.tipos;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Logro {
    private String jugador;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;

    public Logro(String jugador, int respuestasCorrectas) {
        this.jugador = jugador;
        this.respuestasCorrectas = respuestasCorrectas;
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

    @Override
    public String toString(){return jugador+","+"correctas: "+String.valueOf(respuestasCorrectas);
    }
}