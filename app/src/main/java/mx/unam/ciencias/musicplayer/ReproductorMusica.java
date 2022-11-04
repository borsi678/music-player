package mx.unam.ciencias.musicplayer;

import android.media.MediaPlayer;

import java.io.IOException;

public class ReproductorMusica {

    private MediaPlayer reproductor;

    public ReproductorMusica(){
        reproductor=new MediaPlayer();
    }

    public void cargaCancion(String path) throws IOException {
        reproductor.setDataSource(path);
        reproductor.prepareAsync();
    }

    public void reproduceCancion(){
        reproductor.start();
    }

    public void pausaCancion(){
        reproductor.pause();
    }

    public void detenCancion(){
        reproductor.stop();
        reproductor.release();
    }


}
