package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.Nullable;

public class BaseDeDatosMusica extends SQLiteOpenHelper {

    interface Tablas{
        String TYPES="types";
        String PERFORMERS="performers";
        String PERSONS="persons";
        String GROUPS="groups";
        String ALBUMS="albums";
        String ROLAS="rolas";
        String IN_GROUP="in_group";

    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "musica.db";
    private static final String TABLE_TYPES = "CREATE TABLE types ("
            +"id_type INTEGER PRIMARY KEY,"
            +"description TEXT )";
    private static final String INSERT_TYPES_0="INSERT INTO types VALUES(0, 'Person')";
    private static final String INSERT_TYPES_1="INSERT INTO types VALUES(1, 'Group')";
    private static final String INSERT_TYPES_2="INSERT INTO types VALUES(2, 'Unknown')";
    private static final String TABLE_PERFORMERS = "CREATE TABLE performers(" +
            "id_performer INTEGER PRIMARY KEY," +
            "id_type INTEGER," +
            "name TEXT," +
            "FOREIGN KEY (id_type) REFERENCES types(id_type) )";
    private static final String TABLE_PERSONS = "CREATE TABLE persons (" +
            "id_person INTEGER PRIMARY KEY," +
            "stage_name TEXT," +
            "real_name TEXT," +
            "birth_date TEXT," +
            "death_date TEXT )";
    private static final String TABLE_GROUPS = "CREATE TABLE groups (" +
            "id_group INTEGER PRIMARY KEY," +
            "name TEXT," +
            "start_date TEXT," +
            "end_date TEXT )";
    private static final String TABLE_ALBUMS = "CREATE TABLE albums (" +
            "id_album INTEGER PRIMARY KEY," +
            "path  TEXT," +
            "name TEXT," +
            "year INTEGER )";
    private static final String TABLE_ROLAS = "CREATE TABLE rolas (" +
            "id_rola INTEGER PRIMARY KEY," +
            "id_performer INTEGER," +
            "id_album INTEGER," +
            "path TEXT," +
            "title TEXT," +
            "track INTEGER," +
            "year INTEGER," +
            "genre TEXT," +
            "FOREIGN KEY (id_performer) REFERENCES performers(id_performer)," +
            "FOREIGN KEY (id_album) REFERENCES albums(id_album) )";
    private static final String TABLE_IN_GROUP = "CREATE TABLE in_group (" +
            "id_person INTEGER," +
            "id_group INTEGER," +
            "PRIMARY KEY (id_person, id_group)," +
            "FOREIGN KEY (id_person) REFERENCES persons(id_person)," +
            "FOREIGN KEY (id_group) REFERENCES groups(id_group) )";

    public BaseDeDatosMusica (@Nullable Context context){
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_TYPES);
        sqLiteDatabase.execSQL(INSERT_TYPES_0);
        sqLiteDatabase.execSQL(INSERT_TYPES_1);
        sqLiteDatabase.execSQL(INSERT_TYPES_2);
        sqLiteDatabase.execSQL(TABLE_PERFORMERS);
        sqLiteDatabase.execSQL(TABLE_PERSONS);
        sqLiteDatabase.execSQL(TABLE_GROUPS);
        sqLiteDatabase.execSQL(TABLE_ALBUMS);
        sqLiteDatabase.execSQL(TABLE_ROLAS);
        sqLiteDatabase.execSQL(TABLE_IN_GROUP);
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase){
        super.onOpen(sqLiteDatabase);
        if (!sqLiteDatabase.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
            } else {
                sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tablas.TYPES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.PERFORMERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.PERSONS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.GROUPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.ALBUMS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.ROLAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tablas.IN_GROUP);
        onCreate(sqLiteDatabase);
    }
}
