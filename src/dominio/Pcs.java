package Lógica;
import java.util.ArrayList;

public class Pcs {

    private String id;
    private String ip;
    private String so;
    private ArrayList<Puerto> puertos;

    public Pcs(String id, String ip, String so) {
        this.id = id;
        this.ip = ip;
        this.so = so;
        this.puertos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getSo() {
        return so;
    }

    public ArrayList<Puerto> getPuertos() {
        return puertos;
    }

    public void agregarPuerto(Puerto puerto) {
        this.puertos.add(puerto);
    }

    @Override
    public String toString() {
        return "PC{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +  
                ", so='" + so + '\'' +  
                ", puertos=" + puertos +
                '}';
    }



}