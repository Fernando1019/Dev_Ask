package com.example.fernandoambrosio.devask.tipos;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Logro {
    private Usuario jugador;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;
    private int totalRespuestas;

    public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public int getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(int respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public int getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    public void setRespuestasIncorrectas(int respuestasIncorrectas) {
        this.respuestasIncorrectas = respuestasIncorrectas;



    }

    public int getTotalRespuestas() {
        return totalRespuestas;
    }

    public void setTotalRespuestas(int totalRespuestas) {
        this.totalRespuestas = totalRespuestas;
    }
}
