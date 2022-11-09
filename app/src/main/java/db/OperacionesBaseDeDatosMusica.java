package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class OperacionesBaseDeDatosMusica {
    private static BaseDeDatosMusica baseDatos;
    private static OperacionesBaseDeDatosMusica instancia = new OperacionesBaseDeDatosMusica();
    private static final String TYPES_JOIN
    private OperacionesBaseDeDatosMusica(){}

    public static OperacionesBaseDeDatosMusica obtenerInstancias(Context context){
        if(baseDatos == null){
            baseDatos=new BaseDeDatosMusica(context);
        }
        return instancia;
    }

    public Cursor obtenerTypes(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables();
    }

}
