package dominio;

public class Vulnerabilidad {
    
    private String puerto;
    private String nombre;
    private String descripcion;

    public Vulnerabilidad(String puerto, String nombre, String descripcion) {
        this.puerto = puerto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override

    public String toString() {
        return "Vulnerabilidad{" +
                "puerto='" + puerto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}