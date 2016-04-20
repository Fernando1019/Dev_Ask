package com.example.fernandoambrosio.devask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by josueChaqui on 20/04/2016.
 */
public class AccesoUsuario {
    private DatabaseHelper dbHelper;
    public AccesoUsuario(Context contexto){
        dbHelper = new DatabaseHelper(contexto);
    }
    public int insert(Usuario usuario){
        delete(1);
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
        db.delete(Usuario.TABLE_NAME,Usuario.COLUMNA_ID+"= ?",new String[]{String.valueOf(alumno_id)});
        db.close();
    }
    public String getUsuario(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String consulta = "SELECT´"+Usuario.COLUMNA_NOMBRE +" FROM "+Usuario.TABLE_NAME+"WHERE "+Usuario.COLUMNA_ID+"= ?";
        Usuario usuario = new Usuario();
        int iCount=1;
        Cursor cursor = db.rawQuery(consulta, new String[]{String.valueOf(1)});
        if(cursor.moveToFirst()){
            do{
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.TABLE_NAME)));
            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return usuario.getNombre();
    }
}
