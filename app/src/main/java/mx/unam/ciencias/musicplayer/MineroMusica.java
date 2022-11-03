package mx.unam.ciencias.musicplayer;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.mpatric.mp3agic.*;

public class MineroMusica {
    private LinkedList<Mp3File> canciones;
    public MineroMusica(){
        this.canciones=new LinkedList<Mp3File>();
    }

    public LinkedList<Mp3File> minaDirectorio(String ruta){
        File carpeta = new File(ruta);
        if(carpeta.isDirectory())
            this.minaDirectorio(carpeta,canciones);
        else {
            try {
                canciones.add(convierteArchivo(carpeta.getAbsolutePath()));
            }catch (InvalidDataException | UnsupportedTagException | IOException ex){
            }
        }
        return canciones;
    }

    private void minaDirectorio(File carpeta, LinkedList<Mp3File> canciones) {
        String[] listaArchivos = carpeta.list();
        for (String cancion : listaArchivos) {
            File archivoCancion = new File(carpeta.getAbsolutePath()+cancion);
            if(archivoCancion.isDirectory()){
                minaDirectorio(archivoCancion, canciones);
            }
            if(archivoCancion.canRead() && cancion.endsWith(".mp3")){
                try {
                    canciones.add(convierteArchivo(archivoCancion.getAbsolutePath() + cancion));
                } catch (InvalidDataException | UnsupportedTagException | IOException ex){
                    continue;
                }
            }
        }
    }

    public Mp3File convierteArchivo(String rutaArchivo) throws InvalidDataException, UnsupportedTagException, IOException {
        return new Mp3File(rutaArchivo);
    }
}
