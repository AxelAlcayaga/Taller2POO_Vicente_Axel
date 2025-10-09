package logica;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import dominio.Puerto;
import dominio.Pc;
import dominio.Usuario;
import dominio.Vulnerabilidad;




public class Main {

    private static Scanner s;
    private static File f;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Pc> pcs = new ArrayList<>();
    private static ArrayList<Puerto> puertos = new ArrayList<>();
    private static ArrayList<Vulnerabilidad> vulnerabilidades = new ArrayList<>();  

    public static void main(String[] args) throws FileNotFoundException {

        leerPuertos();
        leerPcs();
        leerUsuarios();
        leerVulnerabilidades();
        int opcion = 0;

        do{
            System.out.println("Seleccione una opción:");
            System.out.println("1. Administrador");
            System.out.println("2. Usuario");
            System.out.println("3. Salir");
            s = new Scanner(System.in);
            opcion = s.nextInt();
            s.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1: 
                    menuAdmin();
                    // Lógica para administrador
                    break;
                case 2:
                    menuUsuario();
                    // Lógica para usuario
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        while (opcion != 4);
    }


        public static void leerPcs() throws FileNotFoundException {
            f = new File("pcs.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split("|");
                String id = datos[0];
                String ip = datos[1];
                String so = datos[2];
                PC pc = new PC(id, ip, so);
                pcs.add(pc);
                // Agregar la PC a una lista o mapa según sea necesario
            }   
            // Lógica para leer PCs desde un archivo
        }           
        public static void leerPuertos() throws FileNotFoundException {
            f = new File("puertos.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split("|");
                String id = datos[0];
                String numero = datos[1];
                String estado = datos[2];
                Puerto puerto = new Puerto(id, numero, estado);
                puertos.add(puerto);
                // Agregar el puerto a una lista o mapa según sea necesario
            }   
            // Lógica para leer Puertos desde un archivo
        }
        public static void leerUsuarios() throws FileNotFoundException {
            f = new File("usuarios.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split("|");
                String username = datos[0];
                String contraseña = datos[1];
                String rol = datos[2];
                Usuario usuario = new Usuario(username, contraseña, rol);
                usuarios.add(usuario);
                // Agregar el usuario a una lista o mapa según sea necesario
            }   
            // Lógica para leer Usuarios desde un archivo
        }
        public static void leerVulnerabilidades() throws FileNotFoundException {
            f = new File("vulnerabilidades.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split("|");
                String puerto = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                Vulnerabilidad vulnerabilidad = new Vulnerabilidad(puerto, nombre, descripcion);
                vulnerabilidades.add(vulnerabilidad);
                // Agregar la vulnerabilidad a una lista o mapa según sea necesario
            }   
            // Lógica para leer Vulnerabilidades desde un archivo
        }   
        public static void menuAdmin() {
            int opcion;
            do {
                System.out.println("Menú Administrador:");
                System.out.println("Ingrese usuario:");
                String username = s.nextLine();
                System.out.println("Ingrese contraseña:");
                String contraseña = s.nextLine();
                boolean autenticado = false;
                for (Usuario u : usuarios) {
                    if (u.getUsername().equals(username) && u.getContraseña().equals(contraseña) && u.getRol().equals("admin")) {
                        autenticado = true;
                        break;
                    }
                }
                if (autenticado) {
                    System.out.println("1. Ver todas las PCs");
                    System.out.println("2. Ver todas las vulnerabilidades");
                    System.out.println("3. Salir");
                    opcion = s.nextInt();
                    s.nextLine(); // Consumir el salto de línea
                    switch (opcion) {
                        case 1:
                            for (Pc pc : pcs) {
                                System.out.println(pc);
                            }
                            break;
                        case 2:
                            for (Vulnerabilidad v : vulnerabilidades) {
                                System.out.println(v);
                            }
                            break;
                        case 3:
                            System.out.println("Saliendo del menú administrador...");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Credenciales inválidas o no es un administrador.");
                }
            } while (opcion != 3);
        }
        public static void menuUsuario() {
            int opcion;
            do {
                System.out.println("Menú Usuario:");
                System.out.println("Ingrese usuario:");
                String username = s.nextLine();
                System.out.println("Ingrese contraseña:");
                String contraseña = s.nextLine();
                boolean autenticado = false;
                for (Usuario u : usuarios) {
                    if (u.getUsername().equals(username) && u.getContraseña().equals(contraseña) && u.getRol().equals("user")) {
                        autenticado = true;
                        break;
                    }
                }
                if (autenticado) {
                    System.out.println("1. Ver mis PCs");
                    System.out.println("2. Salir");
                    opcion = s.nextInt();
                    s.nextLine(); // Consumir el salto de línea
                    switch (opcion) {
                        case 1:
                            for (Pc pc : pcs) {
                                if (pc.getOwner().equals(username)) {
                                    System.out.println(pc);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Saliendo del menú usuario...");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Credenciales inválidas o no es un usuario.");
                }
            } while (opcion != 2);
        }
}
