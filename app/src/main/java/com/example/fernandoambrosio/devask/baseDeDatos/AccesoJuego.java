package com.example.fernandoambrosio.devask.baseDeDatos;

import android.content.ContentValues;
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
        String consulta = "SELECT contexto,  directa.Respuesta AS resp  FROM Preguntas INNER JOIN" +
                "(SELECT idPregunta AS id , Respuesta  FROM RespuestaDirecta WHERE idRespuestaDirecta = "+
                        String.valueOf(id)+") AS directa ON directa.id  = " +
                        "Preguntas.idPregunta;";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaDirectaTipo preguntaDirectaTipo = new PreguntaDirectaTipo();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                preguntaDirectaTipo.setRespuesta(cursor.getString(cursor.getColumnIndex("resp")));
                System.out.println(preguntaDirectaTipo.getRespuesta());
                preguntaDirectaTipo.setContexto(cursor.getString(cursor.getColumnIndex("contexto")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return preguntaDirectaTipo;
    }

    public int[] cantidadDatosTabla(String tabla, int idCategoria){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="SELECT idRespuesta"+tabla+" AS ids  FROM Respuesta"+tabla+""+String.valueOf(idCategoria);
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        int ids[]=new int[cursor.getCount()];
        int a=0;
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                ids[a]= cursor.getInt(cursor.getColumnIndex("ids"));
                a++;
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return ids;
    }
    public PreguntaVF getPregutaVF(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="SELECT contexto,  directa.respuesta AS resp  FROM Preguntas INNER JOIN(SELECT " +
                "idPregunta, respuesta  FROM RespuestaVF WHERE idRespuestaVF = "+String.valueOf(id)+
                ") AS directa ON directa.idPregunta = Preguntas.idPregunta;";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaVF preguntavf = new PreguntaVF();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                preguntavf.setRespuesta(cursor.getString(cursor.getColumnIndex("resp")));
                preguntavf.setContexto(cursor.getString(cursor.getColumnIndex("contexto")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return preguntavf;
    }
    public int[] CantidadPreguntas(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT correctas, totalPregunta from Logro";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        int[] cantidades = new int[2];
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                cantidades[0]=Integer.valueOf(cursor.getString(cursor.getColumnIndex("correctas")));
                cantidades[1]=Integer.valueOf(cursor.getString(cursor.getColumnIndex("totalPregunta")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        else{
            cantidades[0]=0;
            cantidades[1]=0;
        }
        db.close();
        return  cantidades;
    }
    public void actualizarCantidades(int correctas, int cantidad){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="UPDATE Logro SET totalPregunta= "+String.valueOf(cantidad)+", nombre="+String.valueOf(correctas)+" WHERE idLogro=1";
        System.out.println(consulta);
        db.rawQuery(consulta,null);
        db.close();
    }
    public void insertCantidades(int correctas, int cantidad){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idLogro",1);
        values.put("correctas",correctas);
        values.put("totalPregunta",10);
        values.put("idJugador",1);
        long StudentId =db.insert("Logro",null,values);
        db.close();
    }
    public int seleccionarIdCategoria(String categoria){
        int idCategoria=0;
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        String consulta ="SELECT idCategoria FROM Categoria WHERE  nombre=\""+categoria+"\"";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                idCategoria=Integer.valueOf(cursor.getString(cursor.getColumnIndex("idCategoria")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        return idCategoria;
    }
}
