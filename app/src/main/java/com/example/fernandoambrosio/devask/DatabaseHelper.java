package com.example.fernandoambrosio.devask;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando Ambrosio on 18/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "preguntas.db";
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_NOMBRE = "nombre";
    SQLiteDatabase db;


    private static final String TABLA_USUARIO = "create table usaurio (id integer primary key not null auto increment , " +
    "nombre text not null);";

    public DatabaseHelper(Context contex ){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);

    }


    public void insertarUsuario(Usuario u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_NOMBRE, u.getNombre());

        db.insert(TABLE_NAME, null, values );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIO);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
