package mx.unam.ciencias.musicplayer;

public class Rolas {
    private int idRola;
    private int idPerformer;
    private int idAlbum;
    private String path;
    private String title;
    private int track;
    private int year;
    private String genre;

    public Rolas(int idRola, int idPerformer, int idAlbum, String path, String title, int track,
                 int year, String genre) {
        this.idRola = idRola;
        this.idPerformer = idPerformer;
        this.idAlbum = idAlbum;
        this.path = path;
        this.title = title;
        this.track = track;
        this.year = year;
        this.genre = genre;
    }

    public int getIdRola() {
        return idRola;
    }

    public void setIdRola(int idRola) {
        this.idRola = idRola;
    }

    public int getIdPerformer() {
        return idPerformer;
    }

    public void setIdPerformer(int idPerformer) {
        this.idPerformer = idPerformer;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
