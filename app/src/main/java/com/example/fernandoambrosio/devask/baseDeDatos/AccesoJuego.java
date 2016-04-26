package com.example.fernandoambrosio.devask.baseDeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;


/**
 * Created by josueChaqui on 26/04/2016.
 */
public class AccesoJuego {
    private DatabaseHelper dbHelper;
    public AccesoJuego(Context contexto){

        dbHelper = new DatabaseHelper(contexto);
    }

    public PreguntaDirectaTipo getPreguntaTipo(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT contexto,  Respuesta  FROM Pregunta INNER JOIN" +
                "(SELECT Pregunta_idPregunta,Respuesta  FROM RespuestaDirecta WHERE idRespuestaDirecta = "+
                        String.valueOf(id)+") AS directa ON directa.Pregunta_idPregunta = " +
                        "directa.Pregunta_idPregunta;";
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaDirectaTipo preguntaDirectaTipo = new PreguntaDirectaTipo();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                preguntaDirectaTipo.setRespuesta(cursor.getString(cursor.getColumnIndex("Respuesta")));
                preguntaDirectaTipo.setContexto(cursor.getString(cursor.getColumnIndex("contexto")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return preguntaDirectaTipo;
    }

    public int cantidadDatosTabla(String tabla){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="SELECT count(idRespuesta"+tabla+") AS ids  FROM Respuesta"+tabla;
        Cursor cursor = db.rawQuery(consulta,null);
        int ids=0;
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                ids= cursor.getInt(cursor.getColumnIndex("ids"));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return ids;
    }
    public PreguntaVF getPregutaVF(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="SELECT contexto,  respuesta  FROM Pregunta INNER JOIN(SELECT " +
                "Pregunta_idPregunta, respuesta  FROM RespuestaVF WHERE idRespuestaVF = "+String.valueOf(id)+
                ") AS directa ON directa.Pregunta_idPregunta = directa.Pregunta_idPregunta;";
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaVF preguntavf = new PreguntaVF();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                preguntavf.setRespuesta(Boolean.valueOf(cursor.getString(cursor.getColumnIndex("Respuesta"))));
                preguntavf.setContexto(cursor.getString(cursor.getColumnIndex("contexto")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return preguntavf;
    }

}
