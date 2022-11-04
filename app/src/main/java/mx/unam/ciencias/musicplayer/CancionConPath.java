package mx.unam.ciencias.musicplayer;

import com.mpatric.mp3agic.Mp3File;

public class CancionConPath {
    private Mp3File cancion;
    private String path;

    public CancionConPath(){}

    public CancionConPath(Mp3File cancion, String path){
        this.cancion=cancion;
        this.path=path;
    }

    public Mp3File getCancion() {
        return cancion;
    }

    public String getPath(){
        return path;
    }
}
