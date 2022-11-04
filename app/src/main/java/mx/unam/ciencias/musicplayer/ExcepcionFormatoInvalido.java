package mx.unam.ciencias.musicplayer;

public class ExcepcionFormatoInvalido extends IllegalArgumentException{

    public ExcepcionFormatoInvalido(){}

    public ExcepcionFormatoInvalido(String mensaje){
        super(mensaje);
    }
}
