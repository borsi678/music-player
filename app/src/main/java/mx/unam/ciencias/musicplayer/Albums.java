package mx.unam.ciencias.musicplayer;

public class Albums {
    private int idAlbum;
    private String path;
    private String name;
    private int year;

    public Albums(int idAlbum, String path, String name, int year) {
        this.idAlbum = idAlbum;
        this.path = path;
        this.name = name;
        this.year = year;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
