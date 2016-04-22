package com.example.fernandoambrosio.devask.tipos;

/**
 * Created by Fernando Ambrosio on 18/04/2016.
 */
public class Usuario {


    public static  final String TABLE_NAME= "usuario";
    public static  final String COLUMNA_ID= "id";
    public static  final String COLUMNA_NOMBRE= "nombre";

    public static  final String CREATE_DB_TABLE= "create table " + TABLE_NAME + "( "+
                                                    COLUMNA_ID + " integer primary key autoincrement," +
                                                    COLUMNA_NOMBRE + " text" +
                                                     " );";

    int id;
    String nombre;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }


    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getNombre() {

        return this.nombre;
    }
}
