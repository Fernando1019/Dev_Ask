package com.example.fernandoambrosio.devask.baseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandoambrosio.devask.tipos.Logro;
import com.example.fernandoambrosio.devask.tipos.PreguntaDirectaTipo;
import com.example.fernandoambrosio.devask.tipos.PreguntaOpcionMultiple;
import com.example.fernandoambrosio.devask.tipos.PreguntaVF;

import java.util.ArrayList;


/**
 * Created by josueChaqui on 26/04/2016.
 */
public class AccesoJuego {
    private DatabaseHelper dbHelper;
    public AccesoJuego(Context contexto){

        dbHelper = new DatabaseHelper(contexto);
    }

    public PreguntaOpcionMultiple getPreguntaTipo(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT respuesta1, respuesta2, respuesta3, pregunta FROM RespuestaDirecta WHERE idRespuestaDirecta =  "+
                        String.valueOf(id);
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaOpcionMultiple preguntaOpcionMultiple = new PreguntaOpcionMultiple();
        String pregunta = "";
        String[] respuestas = new String[3];
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                pregunta= cursor.getString(cursor.getColumnIndex("pregunta"));
                respuestas[0] = cursor.getString(cursor.getColumnIndex("respuesta1"));
                respuestas[1] = cursor.getString(cursor.getColumnIndex("respuesta2"));
                respuestas[2] = cursor.getString(cursor.getColumnIndex("respuesta3"));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        preguntaOpcionMultiple.setContexto(pregunta);
        preguntaOpcionMultiple.setRespuesta(respuestas);
        preguntaOpcionMultiple.setCorrecta(1);
        return preguntaOpcionMultiple;
    }

    public int[] cantidadDatosTabla(String tabla, int idCategoria){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta ="SELECT idRespuesta"+tabla+" AS ids  FROM Respuesta"+tabla+" WHERE categoria_idCategoria ="+String.valueOf(idCategoria);
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
        String consulta ="SELECT respuesta,  pregunta FROM RespuestaVF WHERE idRespuestaVF = "+String.valueOf(id);
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        PreguntaVF preguntavf = new PreguntaVF();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                preguntavf.setRespuesta(cursor.getString(cursor.getColumnIndex("respuesta")));
                preguntavf.setContexto(cursor.getString(cursor.getColumnIndex("pregunta")));
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

    private int cantidadDeLogros(){
        int total=0;
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        String consulta ="SELECT count(idLogro) as total FROM logro";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                total=Integer.valueOf(cursor.getString(cursor.getColumnIndex("total")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return total;
    }
    private int cantidadDeJugadores(){
        int total=0;
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        String consulta ="SELECT count(idjugador) as total FROM jugador";
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                total=Integer.valueOf(cursor.getString(cursor.getColumnIndex("total")));
            } while (cursor.moveToNext());
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return total;
    }
    public void insertarJugador(String nombre,int correctas, int categoria){
        int idJugador= cantidadDeJugadores()+1;
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idjugador",idJugador);
        values.put("nombre",nombre);
        db1.insert("jugador",null,values);
        if (db1 != null) {
            db1.close();
        }
        this.insertCantidades(correctas,idJugador, categoria);
    }
    public void insertCantidades(int correctas,  int idJugador, int categoria){
        int logro= cantidadDeLogros()+1;
        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idlogro",logro);
        values.put("respuestasCorrectas",correctas);
        values.put("TotalPregunta",10);
        values.put("jugador_idjugador",idJugador);
        values.put("categoria_idcategoria",categoria);
        db2.insert("logro",null,values);
        if (db2 != null) {
            db2.close();
        }
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
        db.close();
        return idCategoria;
    }
    public String seleccionarNombreCategoria(int id){
        String nombreCategoria= "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT nombre FROM Categoria WHERE idCategoria = "+String.valueOf(id);
        System.out.println(consulta);
        Cursor cursor = db.rawQuery(consulta,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                nombreCategoria=cursor.getString(cursor.getColumnIndex("nombre"));
                cursor.close();
            } while (cursor.moveToNext());
        }
        db.close();
        return nombreCategoria;
    }
    public ArrayList<Logro> seleccionarLogros(){
        ArrayList<Logro> logroArrayList = new ArrayList<Logro>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT nombre, respuestasCorrectas FROM jugador INNER JOIN \n" +
                "(SELECT * FROM logro) as llogroObtenido ON llogroObtenido.jugador_idjugador = jugador.idjugador";
        System.out.println(consulta);
         Cursor cursor = db.rawQuery(consulta,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                String nombre=cursor.getString(cursor.getColumnIndex("nombre"));
                int respuestasCorrectas=cursor.getInt(cursor.getColumnIndex("respuestasCorrectas"));
                logroArrayList.add(new Logro(nombre,respuestasCorrectas));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return logroArrayList;
    }

}
