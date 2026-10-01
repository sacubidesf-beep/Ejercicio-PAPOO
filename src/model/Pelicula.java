package model;

public class Pelicula {
    private int id;
    private String titulo;
    private String genero;
    private String director;
    private int duracionMinutos;
    private int anioEstreno;

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String genero, String director, int duracionMinutos, int anioEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.director = director;
        this.duracionMinutos = duracionMinutos;
        this.anioEstreno = anioEstreno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", director='" + director + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", anioEstreno=" + anioEstreno +
                '}';
    }
}
