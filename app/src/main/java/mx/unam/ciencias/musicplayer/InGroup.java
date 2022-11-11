package mx.unam.ciencias.musicplayer;

public class InGroup {
    private int idPerson;
    private int idGroup;

    public InGroup(int idPerson, int idGroup) {
        this.idPerson = idPerson;
        this.idGroup = idGroup;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
