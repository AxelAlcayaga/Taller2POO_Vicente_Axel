package dominio;
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
        String base = "Puerto " + numero + " (" + estado + ")";
        if (vulnerabilidades == null || vulnerabilidades.isEmpty()
            || !"Abierto".equalsIgnoreCase(estado)) return base;

        StringBuilder sb = new StringBuilder(base).append("  [");
        for (int i = 0; i < vulnerabilidades.size(); i++) {
            sb.append(vulnerabilidades.get(i).getNombre());
            if (i < vulnerabilidades.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}