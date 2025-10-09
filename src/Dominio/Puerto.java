package Dominio;
import java.util.ArrayList;


public class Puerto {
    
    private String numero;
    private String id;
    private String estado;
    private ArrayList<Vulnerabilidad> vulnerabilidades;

    public Puerto(String id, String numero, String estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.vulnerabilidades = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<Vulnerabilidad> getVulnerabilidades() {
        return vulnerabilidades;
    }

    public void agregarVulnerabilidad(Vulnerabilidad vulnerabilidad) {
        this.vulnerabilidades.add(vulnerabilidad);
    }

    @Override

    public String toString() {
        return "Puerto{" +
                "id='" + id + '\'' +
                ", numero='" + numero + '\'' +
                ", estado='" + estado + '\'' +
                ", vulnerabilidades=" + vulnerabilidades +
                '}';
    }

}