package mx.unam.ciencias.musicplayer;

public class Performers {
    private int idPerformer;
    private int idType;
    private String name;
    private static Integer key = new Integer(0);

    public Performers(int idPerformer, int idType, String name) {
        this.idPerformer = idPerformer;
        this.idType = idType;
        this.name = name;
    }

    public int getIdPerformer() {
        return idPerformer;
    }

    public void setIdPerformer(int idPerformer) {
        this.idPerformer = idPerformer;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int generaIdPerformer(){
        key++;
        return key.intValue();
    }
}
