package com.example.fernandoambrosio.devask.baseDeDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fernandoambrosio.devask.tipos.Usuario;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Fernando Ambrosio on 18/04/2016.
 */
public class DatabaseHelper extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "preguntas.db";


    public DatabaseHelper(Context contex ){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);

    }


}
