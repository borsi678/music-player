package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import mx.unam.ciencias.musicplayer.Albums;
import mx.unam.ciencias.musicplayer.Groups;
import mx.unam.ciencias.musicplayer.InGroup;
import mx.unam.ciencias.musicplayer.Performers;
import mx.unam.ciencias.musicplayer.Persons;
import mx.unam.ciencias.musicplayer.Rolas;
import mx.unam.ciencias.musicplayer.Types;

public final class OperacionesBaseDeDatosMusica {

    interface Tablas{
        String TYPES="types";
        String PERFORMERS="performers";
        String PERSONS="persons";
        String GROUPS="groups";
        String ALBUMS="albums";
        String ROLAS="rolas";
        String IN_GROUP="in_group";
    }

    private static BaseDeDatosMusica baseDatos;
    private static final String SELECT_FROM="SELECT * FROM %s";
    private static OperacionesBaseDeDatosMusica instancia = new OperacionesBaseDeDatosMusica();
    private final String[] proyeccionTypes=new String[]{Tablas.TYPES+"."+"id_type", "description"};

    private OperacionesBaseDeDatosMusica(){}

    public static OperacionesBaseDeDatosMusica obtenerInstancias(Context context){
        if(baseDatos == null){
            baseDatos=new BaseDeDatosMusica(context);
        }
        return instancia;
    }

    public Cursor obtenerTypes(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        String sql= String.format("SELECT * FROM %s", Tablas.TYPES);
        return db.rawQuery(sql,null);
    }

    public Cursor obtenerTypePorId(String id){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String selection= String.format("id_type=?");
        String[] selectionArgs={id};
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        builder.setTables(Tablas.TYPES);
        return builder.query(db, proyeccionTypes, selection, selectionArgs, null, null, null);
    }

    public String insertaType(Types tipo){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String idType=String.valueOf(tipo.getIdType());
        ContentValues valores=new ContentValues();
        valores.put("id_type", tipo.getIdType());
        valores.put("description", tipo.getDesciption());
        db.insertOrThrow(Tablas.TYPES, null, valores);
        return idType;
    }

    public boolean actualizaType(Types nuevoTipo){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        ContentValues valores= new ContentValues();
        valores.put("description", nuevoTipo.getDesciption());
        String whereClause = "id_type=?";
        String[] whereArgs= {String.valueOf(nuevoTipo.getIdType())};
        return db.update(Tablas.TYPES, valores, whereClause, whereArgs)>0;

    }

    public boolean eliminaType(String idType){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String whereClause="id_type=?";
        String[] whereArgs={idType};
        return db.delete(Tablas.TYPES, whereClause, whereArgs)>0;
    }

    public Cursor obtenerPerformers(){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String sql=String.format("SELECT * FROM %s", Tablas.PERFORMERS);
        return db.rawQuery(sql, null);
    }

    public String insertaPerformer(Performers performer){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String idPerformer=String.valueOf(performer.getIdPerformer());
        ContentValues valores= new ContentValues();
        valores.put("id_performer", idPerformer);
        valores.put("id_type", performer.getIdType());
        valores.put("name", performer.getName());
        db.insertOrThrow(Tablas.PERFORMERS, null, valores);
        return idPerformer;
    }

    public boolean actualizaPerformer(Performers performer){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String idPerformer=String.valueOf(performer.getIdPerformer());
        ContentValues valores= new ContentValues();
        valores.put("id_type", performer.getIdType());
        valores.put("name", performer.getName());
        String whereClause="id_performer=?";
        String[] whereArgs={String.valueOf(performer.getIdPerformer())};
        return  db.update(Tablas.PERFORMERS, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaPerformer(String idPerformer){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String whereClause="id_performer=?";
        String[] whereArgs={idPerformer};
        return db.delete(Tablas.PERFORMERS, whereClause, whereArgs)>0;
    }
    public Cursor obtenerPersons(){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String sql= String.format("SELECT * FROM %s", Tablas.PERSONS);
        return db.rawQuery(sql, null);
    }

    public String insertaPerson(Persons persona){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        ContentValues valores= new ContentValues();
        String idPerson=String.valueOf(persona.getIdPerson());
        valores.put("id_person",idPerson);
        valores.put("stage_name", persona.getStageName());
        valores.put("real_name", persona.getRealName());
        valores.put("birth_date", persona.getBirthDate());
        valores.put("death_date", persona.getDeathDate());
        db.insertOrThrow(Tablas.PERSONS, null, valores);
        return idPerson;
    }

    public boolean actualizaPerson(Persons nuevaPersona){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        ContentValues valores= new ContentValues();
        valores.put("stage_name", nuevaPersona.getStageName());
        valores.put("real_name", nuevaPersona.getRealName());
        valores.put("birth_date", nuevaPersona.getBirthDate());
        valores.put("death_date", nuevaPersona.getDeathDate());
        String whereClause="id_person=?";
        String[] whereArgs= {String.valueOf(nuevaPersona.getIdPerson())};
        return db.update(Tablas.PERSONS, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaPerson(String idPerson){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String whereClause="id_person=?";
        String[] whereArgs= {idPerson};
        return db.delete(Tablas.PERSONS, whereClause, whereArgs)>0;
    }

    public Cursor obtenerGroups(){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String sql=String.format("SELECT * FROM %S", Tablas.GROUPS);
        return db.rawQuery(sql, null);
    }

    public String insertaGroup(Groups group){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String idGroup=String.valueOf(group.getIdGroup());
        ContentValues valores=new ContentValues();
        valores.put("id_group", idGroup);
        valores.put("name", group.getName());
        valores.put("start_date", group.getStartDate());
        valores.put("end_date", group.getEndDate());
        db.insertOrThrow(Tablas.GROUPS, null, valores);
        return idGroup;
    }

    public boolean actualizaGroup(Groups group){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String idGroup=String.valueOf(group.getIdGroup());
        ContentValues valores=new ContentValues();
        valores.put("id_group", idGroup);
        valores.put("name", group.getName());
        valores.put("start_date", group.getStartDate());
        valores.put("end_date", group.getEndDate());
        String whereClause="id_group=?";
        String[] whereArgs={idGroup};
        return db.update(Tablas.GROUPS, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaGroup(String idGroup){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String whereClause="id_group=?";
        String[] whereArgs= {idGroup};
        return db.delete(Tablas.PERSONS, whereClause, whereArgs)>0;
    }

    public Cursor obtenerAlbums(){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        String sql=String.format(SELECT_FROM, Tablas.ALBUMS);
        return db.rawQuery(sql, null);
    }

    public String insertaAlbum(Albums album){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        String idAlbum=String.valueOf(album.getIdAlbum());
        ContentValues valores= new ContentValues();
        valores.put("id_album", idAlbum);
        valores.put("path", album.getPath());
        valores.put("name", album.getName());
        valores.put("year", album.getYear());
        db.insertOrThrow(Tablas.ALBUMS, null, valores);
        return idAlbum;
    }

    public boolean actualizaAlbum(Albums nuevoAlbum){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        String idAlbum=String.valueOf(nuevoAlbum.getIdAlbum());
        ContentValues valores= new ContentValues();
        valores.put("path", nuevoAlbum.getPath());
        valores.put("name", nuevoAlbum.getName());
        valores.put("year", nuevoAlbum.getYear());
        String whereClause="id_album=?";
        String[] whereArgs={idAlbum};
        return db.update(Tablas.ALBUMS, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaAlbum(String idAlbum){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String whereClause="id_album=?";
        String[] whereArgs= {idAlbum};
        return db.delete(Tablas.PERSONS, whereClause, whereArgs)>0;
    }

    public Cursor obtenerRolas(){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String sql=String.format(SELECT_FROM,Tablas.ROLAS);
        return db.rawQuery(sql, null);
    }

    public String insertaRola(Rolas rola){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        String idRola=String.valueOf(rola.getIdRola());
        ContentValues valores= new ContentValues();
        valores.put("id_rola", idRola);
        valores.put("id_performer", rola.getIdPerformer());
        valores.put("id_album", rola.getIdAlbum());
        valores.put("path", rola.getPath());
        valores.put("title", rola.getTitle());
        valores.put("track", rola.getTrack());
        valores.put("year", rola.getYear());
        valores.put("genre", rola.getGenre());
        db.insertOrThrow(Tablas.ROLAS, null, valores);
        return idRola;
    }

    public boolean actualizaRola(Rolas nuevaRola) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String idRola = String.valueOf(nuevaRola.getIdRola());
        ContentValues valores = new ContentValues();
        valores.put("id_rola", idRola);
        valores.put("id_performer", nuevaRola.getIdPerformer());
        valores.put("id_album", nuevaRola.getIdAlbum());
        valores.put("path", nuevaRola.getPath());
        valores.put("title", nuevaRola.getTitle());
        valores.put("track", nuevaRola.getTrack());
        valores.put("year", nuevaRola.getYear());
        valores.put("genre", nuevaRola.getGenre());
        String whereClause="id_rola=?";
        String[] whereArgs={idRola};
        return db.update(Tablas.ROLAS, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaRola(String idRola) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause="id_rola=?";
        String[] whereArgs={idRola};
        return db.delete(Tablas.ROLAS, whereClause, whereArgs)>0;
    }

    public Cursor obtenerInGroups(){
        SQLiteDatabase db= baseDatos.getWritableDatabase();
        String sql=String.format(SELECT_FROM, Tablas.IN_GROUP);
        return db.rawQuery(sql, null);
    }

    public void insertaInGroup(InGroup ingroup){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores= new ContentValues();
        valores.put("id_person",ingroup.getIdPerson());
        valores.put("id_group", ingroup.getIdPerson());
        db.insertOrThrow(Tablas.IN_GROUP, null, valores);
    }

    public boolean actualizaInGroup(InGroup nuevoInGroup){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        ContentValues valores= new ContentValues();
        String idPerson=String.valueOf(nuevoInGroup.getIdPerson());
        valores.put("id_person",nuevoInGroup.getIdPerson());
        valores.put("id_group", nuevoInGroup.getIdPerson());
        String whereClause="id_person=?";
        String[] whereArgs={idPerson};
        return db.update(Tablas.IN_GROUP, valores, whereClause, whereArgs)>0;
    }

    public boolean eliminaInGroup(String idPerson){
        SQLiteDatabase db=baseDatos.getWritableDatabase();
        String whereClause="id_person=?";
        String[] whereArgs={idPerson};
        return db.delete(Tablas.IN_GROUP, whereClause, whereArgs)>0;
    }
}
