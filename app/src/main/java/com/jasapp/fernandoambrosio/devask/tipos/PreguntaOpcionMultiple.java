package com.jasapp.fernandoambrosio.devask.tipos;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class PreguntaOpcionMultiple extends Pregunta {
    String[] respuesta = new String[4];
    int correcta;

    public String[] getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String[] respuesta) {
        this.respuesta = respuesta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }
}
