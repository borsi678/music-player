package mx.unam.ciencias.musicplayer;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MiniReproductorMusica {

    private static ReproductorMusica reproductor;
    private static MineroMusica minero;
    private static Scanner scanner=new Scanner(System.in);
    private static LinkedList<CancionConPath> canciones;

    public static void main(String[] args){
        System.out.println("Iniciando reproductor...");
        reproductor=new ReproductorMusica();
        minero=new MineroMusica();

        System.out.print("Dame un directorio:");
        String path= scanner.nextLine();

        canciones=minero.minaDirectorio(path);
        while(true) {
            menuOperaciones();
            realizaOperacion(scanner.nextInt());
        }
    }

    public static void menuOperaciones(){
        System.out.println("Menu:");
        System.out.println("1.Imprime lista de canciones");
        System.out.println("2.Reproduce no. de cancion");
        System.out.println("3.Para cancion");
        System.out.println("4.Deten cancion");
        System.out.println("5.Salir");
    }

    public static void realizaOperacion(int operacion){
        try {
            switch (operacion) {
                case 1:
                    imprimeLista();
                    break;
                case 2:
                    reproduceCancion();
                    break;
                case 3: reproductor.pausaCancion();
                        break;
                case 4: reproductor.detenCancion();
                        break;
                case 5:
                    System.out.println("Saliendo...");
                        System.exit(1);

                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }catch (IOException ex){
            System.out.println("Ocurrio un error");
        }
    }

    public static void imprimeLista(){
        for(CancionConPath cancion : canciones){
            Mp3File datosCancion=cancion.getCancion();
            ID3v2 metaDataCancion=datosCancion.getId3v2Tag();
            String cadenaCancion=String.format("Cancion: %s Album: %s Artista: %s Grupo:%s Genero: %s Año: %s",
                    metaDataCancion.getTitle(), metaDataCancion.getAlbum(), metaDataCancion.getArtist(),
                    metaDataCancion.getGrouping(), metaDataCancion.getGenre(), metaDataCancion.getYear());
            System.out.println(cadenaCancion);
        }
    }

    public static void reproduceCancion() throws IOException {
        System.out.println("Dame el numero de cancion");
        int numero=scanner.nextInt();
        if(numero <0 || numero>canciones.size()){
            System.out.println("Error, ingrese un numero correcto");
            return;
        }
        CancionConPath cancion=canciones.get(numero);
        reproductor.cargaCancion(cancion.getPath());
        reproductor.reproduceCancion();
    }


}
