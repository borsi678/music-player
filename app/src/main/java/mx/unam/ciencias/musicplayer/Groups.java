package mx.unam.ciencias.musicplayer;

public class Groups {
    private int idGroup;
    private String  name;
    private String startDate;
    private String endDate;

    public Groups(int idGroup, String name, String startDate, String endDate) {
        this.idGroup = idGroup;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
