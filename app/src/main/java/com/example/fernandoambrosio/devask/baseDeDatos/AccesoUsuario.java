package com.example.fernandoambrosio.devask.baseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandoambrosio.devask.tipos.Usuario;

/**
 * Created by josueChaqui on 20/04/2016.
 */
public class AccesoUsuario {
    private DatabaseHelper dbHelper;
    public AccesoUsuario(Context contexto){

        dbHelper = new DatabaseHelper(contexto);
    }

    public int insert(Usuario usuario){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Usuario.COLUMNA_ID,1);
        values.put(Usuario.COLUMNA_NOMBRE,usuario.getNombre());
        long StudentId =db.insert("usuario",null,values);
        db.close();
        return(int) StudentId;
    }
    private void delete(int alumno_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Usuario.TABLE_NAME,Usuario.COLUMNA_ID+"= 1",new String[]{String.valueOf(alumno_id)});
        db.close();
    }
    public String getUsuario(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT "+Usuario.COLUMNA_NOMBRE +" FROM "+Usuario.TABLE_NAME+" WHERE "+Usuario.COLUMNA_ID+"= 1";
        Usuario usuario = new Usuario();
        Cursor cursor = db.rawQuery(consulta, null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                usuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                cursor.close();
            } while (cursor.moveToNext());
        }
        else {
            System.out.println("a");
        }
        db.close();
        return usuario.getNombre();
    }

}
