package logica;

public class Main {

    private static s = new Scanner();
    private static f = new File();
    private static usuarios = new ArrayList<Usuario>();
    private static pcs = new ArrayList<PC>();
    private static puertos = new ArrayList<Puerto>();
    private static vulnerabilidades = new ArrayList<Vulnerabilidad>();  

    public static void main(String[] args) {

        leerPcs();
        leerPuertos();
        leerUsuarios();
        leerVulnerabilidades();

        do{
            System.out.println("Seleccione una opción:");
            System.out.println("1. Administrador");
            System.out.println("2. Usuario");
            System.out.println("3. Salir");
            int opcion = s.nextInt();
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
            while (opcion != 2)
            s.close();
        }

        public static void leerPcs() {
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
        public static void leerPuertos() {
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
        public static void leerUsuarios() {
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
        public static void leerVulnerabilidades() {
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
                while (opcion != 2);
        }
}