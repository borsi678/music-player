package mx.unam.ciencias.musicplayer;

public class Types {
    private int idType;
    private String desciption;

    public Types(int idType, String desciption) {
        this.idType = idType;
        this.desciption = desciption;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
