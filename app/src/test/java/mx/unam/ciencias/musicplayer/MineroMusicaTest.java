package mx.unam.ciencias.musicplayer;

import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MineroMusicaTest extends TestCase {

    @Test
    public void testMinaDirectorio() {
        //creaArchivos();
        MineroMusica minero = new MineroMusica();
        LinkedList<CancionConPath> canciones= minero.minaDirectorio("/media/angel/Disco1/Música/Doom Slayer - DOOM Eternal Original Game Soundtrack (OST 2020)/");
        String path;
        Mp3File archivoMp3;
        int i=0;
        String pathPrueba;
        for (CancionConPath cancion : canciones){
            if(i==5)
                i++;
            path=cancion.getPath();
            if(path.startsWith("testMinero/subcarpeta")){
                pathPrueba=String.format("testMinero/cancion0%d.mp3",i++);
                assertEquals(pathPrueba, path);
                continue;
            }
            pathPrueba=String.format("testMinero/cancion0%d.mp3",i++);
            assertEquals(pathPrueba, path);
        }

        //eliminaDirectorio();
    }

    @Test
    public void testConvierteArchivo() {
    }

    @Test
    public void testCompletDatos(){

    }

    @Test
    public void testVerificaDatos(){

    }

    @Test
    public void  testSetDatosPredeterminados(){

    }

    @Test
    public void testEsFormatoValido(){

    }

    @BeforeEach
    private void creaArchivos(){
        File carpeta=new File("testMinero");
        carpeta.mkdir();
        String nombreArchivo;
        File archivo;
        for(int i=0;i <10 ; i++){
            if(i==5){
                carpeta=new File("testMinero/subcarpeta");
                carpeta.mkdir();

                continue;
            }
            nombreArchivo=String.format("cancion0%d.mp3",i);
            archivo=new File(carpeta+"/"+nombreArchivo);
            try {
                archivo.createNewFile();
                archivo.setReadable(true);
                archivo.setExecutable(true);
                archivo.setWritable(true);
                try {
                    Mp3File cancion=new Mp3File(archivo);
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
                ID3v24Tag id3v24Tag=new ID3v24Tag();

            } catch (IOException e) {
                continue;
            }
        }
    }

    @AfterEach
    private void eliminaDirectorio(){
        File carpeta=new File("testMinero");
        File[] archivos=carpeta.listFiles();
        for(File archivo: archivos){
            archivo.delete();
        }
        carpeta=new File("testMinero/subcarpeta");
        archivos=carpeta.listFiles();
        for (File archivo: archivos)
            archivo.delete();
        carpeta.delete();
        carpeta=new File("testMinero");
        carpeta.deleteOnExit();
    }

}