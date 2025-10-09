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
                System.out.println("1. Agregar PC");
                System.out.println("2. Eliminar PC");
                System.out.println("3. Modificar PC");
                System.out.println("4. Ver PCs");
                System.out.println("5. Agregar Puerto");
                System.out.println("6. Eliminar Puerto");
                System.out.println("7. Modificar Puerto");
                System.out.println("8. Ver Puertos");
                System.out.println("9. Agregar Usuario");
                System.out.println("10. Eliminar Usuario");
                System.out.println("11. Modificar Usuario");
                System.out.println("12. Ver Usuarios");
                System.out.println("13. Agregar Vulnerabilidad");
                System.out.println("14. Eliminar Vulnerabilidad");
                System.out.println("15. Modificar Vulnerabilidad");
                System.out.println("16. Ver Vulnerabilidades");
                System.out.println("17. Salir");
                opcion = s.nextInt();
                s.nextLine(); // Consumir el salto de línea
                switch (opcion) {
                    case 1:
                        // Lógica para agregar PC
                        break;
                    case 2:
                        // Lógica para eliminar PC
                        break;
                    case 3:
                        // Lógica para modificar PC
                        break;
                    case 4:
                        // Lógica para ver PCs
                        break;
                    case 5:
                        // Lógica para agregar Puerto
                        break;
                    case 6:
                        // Lógica para eliminar Puerto
                        break;
                    case 7:
                        // Lógica para modificar Puerto
                        break;
                    case 8:
                        // Lógica para ver Puertos
                        break;
                    case 9:
                        // Lógica para agregar Usuario
                        break;
                    case 10:
                        // Lógica para eliminar Usuario
                        break;
                    case 11:
                        // Lógica para modificar Usuario
                        break;
                    case 12:
                        // Lógica para ver Usuarios
                        break;
                    case 13:
                        // Lógica para agregar Vulnerabilidad
                        break;
                    case 14:
                        // Lógica para eliminar Vulnerabilidad
                        break;
                    case 15:
                        // Lógica para modificar Vulnerabilidad
                        break;
                    case 16:
                        // Lógica para ver Vulnerabilidades
                        break;
    }