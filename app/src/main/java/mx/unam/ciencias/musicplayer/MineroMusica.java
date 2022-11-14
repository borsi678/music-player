package mx.unam.ciencias.musicplayer;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * <p Clase que se encarga de extraer las etiquetas ID3v24 de las canciones de un directorio</p>
 */
public class MineroMusica {


    /**Constructor unico*/
    public MineroMusica(){

    }

    /**
     * Metodo que se encarga de verificar si la ruta es un directorio o un archivo para llamar los
     * metodos adecuados.
     * @param ruta la ruta para minar los archivos.
     * @return la lista de canciones con su metadata extraida.
     */
    public LinkedList<CancionConPath> minaDirectorio(String ruta){
        LinkedList<CancionConPath> canciones=new LinkedList<>();
        if(canciones.size()>0)
            canciones=new LinkedList<>();
        File carpeta = new File(ruta);
        if(carpeta.isDirectory())
            canciones=this.minaDirectorio(carpeta,canciones);
        else {
            try {
                canciones.add(new CancionConPath(convierteArchivo(carpeta), ruta));
            }catch (InvalidDataException | UnsupportedTagException | IOException ex){
            }
        }
        return canciones;
    }

    /**
     * Metodo recursivo que se encarga de extrar las etiquetas ID3v24 del directorio especificado.
     * @param carpeta la carpeta a minar los datos.
     * @return la lista de canciones con la metadata extraida.
     */
    private LinkedList<CancionConPath> minaDirectorio(File carpeta, LinkedList<CancionConPath> canciones) {
        String[] listaArchivos = carpeta.list();
        for (String cancion : listaArchivos) {
            File archivoCancion = new File(carpeta.getAbsolutePath()+"/"+cancion);
            if(archivoCancion.isDirectory()){
                minaDirectorio(archivoCancion, canciones);
                continue;
            }
            if(esFormatoValido(cancion)){
                try {
                    Mp3File prueba= convierteArchivo(archivoCancion);
                    canciones.add(new CancionConPath(convierteArchivo(archivoCancion), archivoCancion.getAbsolutePath()));
                } catch (InvalidDataException | UnsupportedTagException | IOException ex){
                    continue;
                }
            }
        }
        return canciones;
    }

    /**
     * Metodo que se encarga de convertir el archivo a una instancia de Mp3File.
     * @param archivo el archivo a convertir
     * @return la instancia de Mp3File del archivo
     * @throws InvalidDataException si el archivo no es archivo de musica.
     * @throws UnsupportedTagException si el archivo no tiene etiquetas ID3v24.
     * @throws IOException si ocurre un error al convertir el archivo.
     */
    public Mp3File convierteArchivo(File archivo) throws InvalidDataException, UnsupportedTagException, IOException {
        Mp3File cancion=new Mp3File(archivo);
        cancion=completaDatos(cancion);
        return cancion;
    }

    /**
     * Metodo auxiliar que se encarga de completar los datos del archivo por valores por default.
     * @param cancion el archivo con instancia de Mp3File para poder extraer los datos.
     * @return la cancion con valores pro default si fue necesario.
     */
    private Mp3File completaDatos(Mp3File cancion){
        if(!cancion.hasId3v2Tag() || !cancion.hasId3v1Tag()){
            cancion=setDatosPredeterminados(cancion);
        }
        cancion = verificaDatos(cancion);
        return cancion;
    }
    
    private Mp3File verificaDatos(Mp3File cancion){
        ID3v2 id3v24Tag= cancion.getId3v2Tag();
        String dato=id3v24Tag.getArtist();
        if(dato==null)
            id3v24Tag.setArtist("Unknown");
        dato=id3v24Tag.getComposer();
        if(dato==null || dato.equals(""))
            id3v24Tag.setComposer("Unknown");
        dato=id3v24Tag.getTitle();
        if(dato==null )
            id3v24Tag.setTitle("Unknown");
        dato=id3v24Tag.getAlbum();
        if(dato==null || dato.equals(""))
            id3v24Tag.setAlbum("Unknown");
        dato=id3v24Tag.getYear();
        if(dato==null || dato.equals(""))
            id3v24Tag.setYear("2021");
        dato=id3v24Tag.getGenreDescription();
        if(dato==null || dato.equals(""))
            id3v24Tag.setGenreDescription("Other");
        int genero=id3v24Tag.getGenre();
        if(genero < 0 || genero >79)
            id3v24Tag.setGenre(32);
        dato=id3v24Tag.getTrack();
        if(dato==null || dato.equals(""))
            id3v24Tag.setComposer("0");
        cancion.setId3v2Tag(id3v24Tag);
        return cancion;
    }

    private Mp3File setDatosPredeterminados(Mp3File cancion){
        ID3v24Tag id3v2Tag= new ID3v24Tag();
        id3v2Tag.setArtist("Unknown");
        id3v2Tag.setComposer("Unknown");
        id3v2Tag.setTitle("Unknown");
        id3v2Tag.setAlbum("Unknown");
        id3v2Tag.setRecordingTime("2021");
        id3v2Tag.setYear("2021");
        id3v2Tag.setGenreDescription("Other");
        id3v2Tag.setGenre(32);
        id3v2Tag.setTrack("0");
        cancion.setId3v2Tag(id3v2Tag);
        return cancion;
    }

    private boolean esFormatoValido(String nombreCancion){
        String[] formatos = {".wav", ".aiff", ".pcm", ".flac", ".alac", ".wma", ".mp3", ".ogg", ".aac", ".wma", ".m4a"};
        for (String formato : formatos){
            if(nombreCancion.toLowerCase().endsWith(formato))
                return true;
        }
        return false;
    }
}
