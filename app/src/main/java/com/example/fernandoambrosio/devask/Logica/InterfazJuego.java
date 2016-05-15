package com.example.fernandoambrosio.devask.Logica;

import android.content.Context;
import android.content.Intent;

import com.example.fernandoambrosio.devask.Categoria;
import com.example.fernandoambrosio.devask.PreguntaFv;
import com.example.fernandoambrosio.devask.PreguntaSeleccion;
import com.example.fernandoambrosio.devask.RankingActivity;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.Random;

/**
 * Created by josueChaqui on 9/05/2016.
 */
public class InterfazJuego {
   private Context contexto;

    public InterfazJuego(Context contexto) {
        this.contexto = contexto;
    }
    public void seleccionarJuego(int cantidad, int correctas, int idCategoria){
        cantidad++;
        Juego juego = new Juego(contexto);
        Aleatorio aleatorio = new Aleatorio();
        Intent intent = null;
        int numero = aleatorio.numero(2);

        if (numero == 1) {
            PreguntaVF vf = juego.crearPreguntaVf(idCategoria);
            intent = new Intent(contexto, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
        }
        //preguntaMultiple
        if (numero == 2) {
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple(idCategoria);
            intent = new Intent(contexto, PreguntaSeleccion.class);
            String[] respuestas = multiple.getRespuesta();
            intent.putExtra("pregunta",multiple.getContexto());
            intent.putExtra("respuesta1",respuestas[0]);
            intent.putExtra("respuesta2",respuestas[1]);
            intent.putExtra("respuesta3",respuestas[2]);
            System.out.println(multiple.getCorrecta());
            intent.putExtra("correcta",String.valueOf(multiple.getCorrecta()));

        }
        intent.putExtra("cantidad",String.valueOf(cantidad));
        intent.putExtra("correctas",String.valueOf(correctas));
        intent.putExtra("idCategoria",Integer.valueOf(idCategoria));
        contexto.startActivity(intent);
    }
    public void abrirCategorias(){
        Intent intent =new Intent(contexto, Categoria.class);
        contexto.startActivity(intent);
    }
    public void SeleccionarCategoria(String categoria){
        Juego juego = new Juego(contexto);
        int idCategoria = juego.seleccionarIdCategoria(categoria);
        this.seleccionarJuego(0,0,idCategoria);
    }
    public void registrar(int correctas, int IdCategoria){
        Juego juego = new Juego(contexto);
        Intent intent = new Intent(contexto,RankingActivity.class);
        String texto =juego.seleccionarNombreCategoria( IdCategoria, correctas);
        texto= texto+"\n desea Guardar o compartir en redes sociales";
        intent.putExtra("texto",texto);
        contexto.startActivity(intent);
    }
}
