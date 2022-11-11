package mx.unam.ciencias.musicplayer;

public class Persons {
    private int idPerson;
    private String stageName;
    private String realName;
    private String birthDate;
    private String deathDate;
    private static Integer key=new Integer(0);

    public Persons(int idPerson, String stageName, String realName, String birthDate, String deathDate) {
        this.idPerson = idPerson;
        this.stageName = stageName;
        this.realName = realName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String generaIdPerson(){
        key++;
        return key.toString();
    }
}
