package com.example.fernandoambrosio.devask.baseDeDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando Ambrosio on 18/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=4;

    private static final String DATABASE_NAME="preguntas.db";

    public DatabaseHelper(Context contexto){
        super(contexto,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //crear tablas de base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaUsuario="CREATE TABLE `usuario` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `nombre` TEXT NOT NULL);";
        db.execSQL(tablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //para eliminar tablas
        db.execSQL("DROP TABLE IF EXISTS `usuario` ");
        onCreate(db);
    }
}
