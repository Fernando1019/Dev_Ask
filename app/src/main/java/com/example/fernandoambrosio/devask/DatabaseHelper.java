package com.example.fernandoambrosio.devask;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando Ambrosio on 18/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "preguntas";
    //private static final String TABLE_NAME = "usuario";
    //private static final String COLUMNA_ID = "id";
    //private static final String COLUMNA_NOMBRE = "nombre";
    private SQLiteDatabase db;


    //private static final String TABLA_USUARIO = "CREATE TABLE `usuario` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `nombre` TEXT NOT NULL);";
            //"create table usuario (id integer primary key not null auto increment , " +
    //"nombre text not null);";

    public DatabaseHelper(Context contex ){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
        db= this.getWritableDatabase();

    }


  //  public void insertarUsuario(Usuario u){
    //    db = this.getWritableDatabase();
      //  ContentValues values = new ContentValues();
        //values.put(COLUMNA_NOMBRE, u.getNombre());

        //db.insert(TABLE_NAME, null, values );
    //}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Usuario.CREATE_DB_TABLE);
        //this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST " + Usuario.TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

  //  public  String setNombre() {
    //          String query ="SELECT nombre FROM usuario ORDER BY `id` DESC LIMIT 1";
      //          Cursor cursor =db.rawQuery(query,null);
        //        String nombre="";
          //      if (cursor.moveToFirst()){
            //            do{
              //                  nombre= cursor.getString(0);
                //            }
                  //   while(cursor.moveToNext());
                    //}
             //return  nombre;

//                    }

}
