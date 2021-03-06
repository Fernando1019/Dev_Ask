package com.jasapp.fernandoambrosio.devask.Logica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.jasapp.fernandoambrosio.devask.Categoria;
import com.jasapp.fernandoambrosio.devask.Historial;
import com.jasapp.fernandoambrosio.devask.Menu;
import com.jasapp.fernandoambrosio.devask.PreguntaFv;
import com.jasapp.fernandoambrosio.devask.PreguntaSeleccion;
import com.jasapp.fernandoambrosio.devask.RankingActivity;
import com.jasapp.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.jasapp.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.ArrayList;

/**
 * Created by josueChaqui on 9/05/2016.
 */
public class InterfazJuego extends AppCompatActivity {
   private Context contexto;

    public InterfazJuego(Context contexto) {
        this.contexto = contexto;
    }
    public void seleccionarJuego(int cantidad, int correctas, int idCategoria, ArrayList<Integer> utilizadas){
        ArrayList<Integer> nUtilizados = utilizadas;
        cantidad++;
        Juego juego = new Juego(contexto);
        Aleatorio aleatorio = new Aleatorio();
        Intent intent = null;
        int numero = aleatorio.numeroMenosCero(3);

        if (numero == 1) {
            System.out.println("1");
            PreguntaVF vf = juego.crearPreguntaVf(idCategoria,utilizadas);
            nUtilizados.add(vf.getId());
            intent = new Intent(contexto, PreguntaFv.class);
            intent.putExtra("pregunta",vf.getContexto());
            intent.putExtra("respuesta",vf.getRespuesta());
        }
        //preguntaMultiple
        if (numero == 2) {
            System.out.println("2");
            PreguntaOpcionMultiple multiple = juego.crearPreguntaOpcionMultiple(idCategoria,utilizadas);
            nUtilizados.add(multiple.getId());
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
        intent.putExtra("usados",nUtilizados);
        contexto.startActivity(intent);
    }
    public void abrirCategorias(){
        Intent intent =new Intent(contexto, Categoria.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        contexto.startActivity(intent);


    }
    public void SeleccionarCategoria(String categoria){
        Juego juego = new Juego(contexto);
        int idCategoria = juego.seleccionarIdCategoria(categoria);
        this.seleccionarJuego(0,0,idCategoria, new ArrayList<Integer>());
    }
    public void registrar(int correctas, int IdCategoria){
        Juego juego = new Juego(contexto);
        Intent intent = new Intent(contexto,RankingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        String categoria =juego.seleccionarNombreCategoria( IdCategoria);
        intent.putExtra("categoria",categoria);
        intent.putExtra("correctas",correctas);
        System.out.println(IdCategoria);
        intent.putExtra("idCategoria",Integer.valueOf(IdCategoria));
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        contexto.startActivity(intent);
    }
    public void insertarJugador(int correctas, String usuario, int categoria){
        Juego juego = new Juego(contexto);
        boolean correcto = juego.insertarJugador(usuario,correctas, categoria);

        if (correcto) {
            Intent intent = new Intent(contexto,Historial.class);
            contexto.startActivity(intent);
        }
    }
    public void cancelar(){
        Intent intent = new Intent(contexto,Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        contexto.startActivity(intent);
    }

}
