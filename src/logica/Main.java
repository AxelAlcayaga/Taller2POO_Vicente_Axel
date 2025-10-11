//Axel Ignacio Alcayaga Flores, 20.832.945-6, ICCI
//Vicente Andrés Rojas Lillo, 22.141.463-2, ICCI
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
    
    private static String sha256Base64(String plain) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(plain.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
    	
    	
        leerPcs();
        leerPuertos();
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
        while (opcion != 3);
        s.close();
    }


        public static void leerPcs() throws FileNotFoundException {
            f = new File("pcs.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split("\\|");
                String id = datos[0];
                String ip = datos[1];
                String so = datos[2];
                Pc pc = new Pc(id, ip, so);
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
                String[] datos = linea.split("\\|");
                String id = datos[0];
                String numero = datos[1];
                String estado = datos[2];
                Puerto puerto = new Puerto(id, numero, estado);
                puertos.add(puerto);
                for (Pc pc : pcs) {
                	if (pc.getId().equals(id)) {
                		pc.agregarPuerto(puerto);
                	}
                }
                // Agregar el puerto a una lista o mapa según sea necesario
            }   
            // Lógica para leer Puertos desde un archivo
        }
        public static void leerUsuarios() throws FileNotFoundException {
            f = new File("usuarios.txt");
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] datos = linea.split(";");
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
                String[] datos = linea.split("\\|");
                String puerto = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                Vulnerabilidad vulnerabilidad = new Vulnerabilidad(puerto, nombre, descripcion);
                vulnerabilidades.add(vulnerabilidad);
                for (Puerto p : puertos) {
                	if (p.getNumero().equals(puerto)) {
                		p.agregarVulnerabilidad(vulnerabilidad);
                	}
                }
                // Agregar la vulnerabilidad a una lista o mapa según sea necesario
            }   
            // Lógica para leer Vulnerabilidades desde un archivo
        }   
        public static void menuAdmin() {
        	System.out.println("Menú Administrador:");
            System.out.print("Ingrese usuario: ");
            String username = s.nextLine();
            System.out.print("Ingrese contraseña: ");
            String passIngresada = s.nextLine();

            String hashIngresado = sha256Base64(passIngresada);
            boolean autenticado = false;

            // Verificar credenciales
            for (Usuario u : usuarios) {
                if (u.getUsername().equals(username)
                        && hashIngresado.equals(u.getContraseña())
                        && u.getRol().equalsIgnoreCase("ADMIN")) {
                    autenticado = true;
                    break;
                }
            }

            // Si no se autentica, salir
            if (!autenticado) {
                System.out.println("Credenciales inválidas o no es un administrador.");
                return;
            }

            // Si se autentica, mostrar el submenú admin
            int opcion;
            do {        	    
            	System.out.println("----------------------------------------");
        	    System.out.println("Menú Administrador:");
        	    System.out.println("1. Ver todas las PCs");
        	    System.out.println("2. Agregar PC");
        	    System.out.println("3. Eliminar PC");
        	    System.out.println("4. Clasificar PC");
        	    System.out.println("5. Salir");
        	    System.out.print("Seleccione una opción: ");

        	    while (!s.hasNextInt()) s.nextLine();
        	    opcion = s.nextInt(); s.nextLine();

        	    switch (opcion) {
        	        case 1:
        	            for (Pc pc : pcs) {
        	                System.out.println("----------------------------------------");
        	                System.out.println(pc);
        	            }
        	            break;
        	        case 2:
        	            agregarPc();   
        	            break;
        	        case 3:
        	            eliminarPc();  
        	            break;
        	        case 4:
        	        	clasificarPc();
        	        	break;
        	        case 5:
        	            System.out.println("Saliendo del menú administrador...");
        	            System.out.println("");
        	            break;
        	        default:
        	            System.out.println("Opción inválida. Intente de nuevo.");
        	    }
        	} while (opcion != 5);
        }
        
            
     // Cuenta vulnerabilidades de puertos abiertos
        private static int contarVulnerabilidadesAbiertas(Pc pc) {
            int c = 0;
            for (Puerto p : pc.getPuertos()) {
                if ("Abierto".equalsIgnoreCase(p.getEstado())) {
                    if (p.getVulnerabilidades() != null) 
                    	c += p.getVulnerabilidades().size();
                }
            }
            return c;
        }

        // Regla: Bajo(0), Medio(1-2), Alto(>=3)
        private static String nivelRiesgo(int v) {
            if (v >= 3) return "ALTO";
            if (v >= 1) return "MEDIO";
            return "BAJO";
        }

        // Clase de IP por primer octeto
        //es decir 0.0.0.0 – 127.255.255.255, si el primer octeto está entre 0 y 127 sera clase A
        private static String claseIP(String ip) {
            try {
                int o1 = Integer.parseInt(ip.split("\\.")[0]);
                if (o1 >= 0 && o1 <= 127)  return "Clase A";
                if (o1 >= 128 && o1 <= 191) return "Clase B";
                if (o1 >= 192 && o1 <= 223) return "Clase C";
                return "Fuera de A/B/C";
            } catch (Exception e) {
                return "IP inválida";
            }
        }

        private static void clasificarPc() {
            System.out.println("=== Clasificación de PCs según nivel de riesgo ===");
            for (Pc pc : pcs) {
                int v = contarVulnerabilidadesAbiertas(pc);
                String nivel = nivelRiesgo(v);
                String clase = claseIP(pc.getIp());

                System.out.println("----------------------------------------");
                System.out.println("PC " + pc.getId() + " | IP: " + pc.getIp() + " (" + clase + ") | SO: " + pc.getSo());
                System.out.println("Riesgo: " + nivel + "  (vulnerabilidades en puertos ABIERTOS: " + v + ")");
                System.out.println("Vulnerabilidades asociadas:");

                boolean alguna = false;
                for (Puerto p : pc.getPuertos()) {
                    if (!"Abierto".equalsIgnoreCase(p.getEstado())) continue;
                    if (p.getVulnerabilidades() == null || p.getVulnerabilidades().isEmpty()) continue;

                    alguna = true;
                    System.out.print("  - Puerto " + p.getNumero() + ": ");
                    for (int i = 0; i < p.getVulnerabilidades().size(); i++) {
                        System.out.print(p.getVulnerabilidades().get(i).getNombre());
                        if (i < p.getVulnerabilidades().size() - 1) System.out.print(", ");
                    }
                    System.out.println();
                }
                if (!alguna) System.out.println("  (sin vulnerabilidades en puertos abiertos)");
            }
        }

        //Elimina la PC con sus puertos
        private static void eliminarPc() {
            System.out.println("=== Eliminar PC ===");
            System.out.print("ID de la PC a eliminar: (PC00X) ");
            String id = s.nextLine();

            Pc pc = buscarPcPorId(id);
            if (pc == null) {
                System.out.println("No existe una PC con ese ID.");
                return;
            }
            
            for (int i = puertos.size() - 1; i >= 0; i--) {
                if (puertos.get(i).getId().equals(id)) {
                    puertos.remove(i);
                }
            }

            pcs.remove(pc);

            System.out.println("PC " + id + " eliminada con todos sus puertos asociados.");
        }

		private static Pc buscarPcPorId(String id) {
		    for (Pc pc : pcs) if (pc.getId().equals(id)) return pc;
		    return null;
		}
		private static void asociarVulnerabilidadesAPuerto(Puerto p) {
		    for (Vulnerabilidad v : vulnerabilidades) {
		        if (v.getPuerto().equals(p.getNumero())) { 
		            p.agregarVulnerabilidad(v);
		        }
		    }
		}

		private static void agregarPc() {
		    System.out.println("--------------------");
		    System.out.println("Nuevo PC");

		    System.out.print("Ingrese nuevo ID: ");
		    String id = s.nextLine();
		    if (id.isEmpty()) { System.out.println("ID vacío. Cancelado."); return; }
		    if (buscarPcPorId(id) != null) { System.out.println("Ese ID ya existe."); return; }

		    System.out.print("Ingrese nuevo IP: ");
		    String ip = s.nextLine();

		    System.out.print("Ingrese nuevo SO: ");
		    String so = s.nextLine();

		    Pc pc = new Pc(id, ip, so);

		    System.out.print("¿Cuántos puertos asociará? ");
		    int n = 0;
		    try { n = Integer.parseInt(s.nextLine().trim()); } catch (Exception e) { n = 0; }

		    for (int i = 1; i <= n; i++) {
		        System.out.println("Puerto #" + i);
		        System.out.print("  Número (ej. 22, 80, 443): ");
		        String numero = s.nextLine().trim();
		        System.out.print("  Estado (Abierto/Cerrado): ");
		        String estado = s.nextLine().trim();

		        Puerto p = new Puerto(id, numero, estado); //Pc nueva
		        pc.agregarPuerto(p);       // asocia a la PC
		        puertos.add(p);            // se agrega a la lista ya leida
		        asociarVulnerabilidadesAPuerto(p); // rellena vulnerabilidades según número
		    }

		    pcs.add(pc);
		    System.out.println("PC agregada correctamente.");
		}


		public static void menuUsuario() {
		    System.out.println("-----Menú Usuario-----");
		    System.out.println("Ingrese usuario: ");
		    String username = s.nextLine();
		    System.out.print("Ingrese contraseña: ");
		    String passIngresada = s.nextLine();

		    String hashIngresado = sha256Base64(passIngresada);
		    boolean autenticado = false;

		    for (Usuario u : usuarios) {
		        if (u.getUsername().equals(username)
		                && hashIngresado.equals(u.getContraseña())
		                && u.getRol().equalsIgnoreCase("USER")) {
		            autenticado = true;
		            break;
		        }
		    }

		    if (!autenticado) {
		        System.out.println("Credenciales inválidas o no es un usuario.");
		        return;
		    }

		    int opcion;
		    do {
		        System.out.println("----------------------------------------");
		        System.out.println("Menú Usuario");
		        System.out.println("1. Ver lista de PCs");
		        System.out.println("2. Escanear un PC (genera reporte)");
		        System.out.println("3. Ver total de puertos abiertos en toda la red");
		        System.out.println("4. Ordenar PCs según IP (Clase A/B/C)");
		        System.out.println("5. Salir");
		        System.out.print("Seleccione una opción: ");

		        while (!s.hasNextInt()) s.nextLine();
		        opcion = s.nextInt();
		        s.nextLine();

		        switch (opcion) {
		            case 1:
		                verListaPCsUsuario();
		                break;
		            case 2:
		                escanearPc(username);
		                break;
		            case 3:
		                verPuertosAbiertos();
		                break;
		            case 4:
		                ordenarPc();
		                break;
		            case 5:
		                System.out.println("Saliendo del menú usuario...");
		                System.out.println("");
		                break;
		            default:
		                System.out.println("Opción inválida. Intente de nuevo.");
		        }
		    } while (opcion != 5);
		}

		// === ORDENAR PCs POR CLASE DE IP (ESTILO SIMPLE) ===
		private static void ordenarPc() {
		    ArrayList<Pc> a = new ArrayList<>();
		    ArrayList<Pc> b = new ArrayList<>();
		    ArrayList<Pc> c = new ArrayList<>();
		    ArrayList<Pc> otras = new ArrayList<>();

		    // 1) separar por clase según primer octeto
		    for (Pc pc : pcs) {
		        int o1 = firstOctet(pc.getIp());
		        if (o1 >= 0 && o1 <= 127) {
		            a.add(pc);
		        } else if (o1 >= 128 && o1 <= 191) {
		            b.add(pc);
		        } else if (o1 >= 192 && o1 <= 223) {
		            c.add(pc);
		        } else {
		            otras.add(pc);
		        }
		    }

		    // 2) ordenar cada lista por IP con burbuja (simple, sin comparators)
		    burbujaPorIP(a);
		    burbujaPorIP(b);
		    burbujaPorIP(c);
		    burbujaPorIP(otras);

		    // 3) imprimir
		    System.out.println("=== PCs ordenadas por clase de IP (A/B/C) ===");
		    imprimirBucketSimple("Clase A", a);
		    imprimirBucketSimple("Clase B", b);
		    imprimirBucketSimple("Clase C", c);
		    if (!otras.isEmpty()) imprimirBucketSimple("Fuera de A/B/C", otras);
		}

		private static void imprimirBucketSimple(String titulo, ArrayList<Pc> lista) {
		    System.out.println("[" + titulo + "]");
		    if (lista.isEmpty()) {
		        System.out.println("  (sin PCs)");
		        return;
		    }
		    for (Pc pc : lista) {
		        System.out.println("  " + pc.getId() + "  " + pc.getIp() + "  |  " + pc.getSo());
		    }
		}

		private static void burbujaPorIP(ArrayList<Pc> lista) {
		    // ordena ascendente por IP (numérica)
		    for (int i = 0; i < lista.size() - 1; i++) {
		        for (int j = 0; j < lista.size() - 1 - i; j++) {
		            int ip1 = ipToInt(lista.get(j).getIp());
		            int ip2 = ipToInt(lista.get(j + 1).getIp());
		            if (ip1 > ip2) {
		                Pc tmp = lista.get(j);
		                lista.set(j, lista.get(j + 1));
		                lista.set(j + 1, tmp);
		            }
		        }
		    }
		}

		private static int firstOctet(String ip) {
		    try {
		        String[] p = ip.split("\\.");
		        return Integer.parseInt(p[0]);
		    } catch (Exception e) {
		        return -1; // inválida → otras
		    }
		}

		private static int ipToInt(String ip) {
		    try {
		        String[] a = ip.split("\\.");
		        int o1 = Integer.parseInt(a[0]);
		        int o2 = Integer.parseInt(a[1]);
		        int o3 = Integer.parseInt(a[2]);
		        int o4 = Integer.parseInt(a[3]);
		        // compone la IP como entero para comparar fácil
		        return (o1 << 24) | (o2 << 16) | (o3 << 8) | o4;
		    } catch (Exception e) {
		        return Integer.MAX_VALUE; // las inválidas quedan al final
		    }
		}



		private static void verPuertosAbiertos() {
		    System.out.println("=== Puertos ABIERTOs en toda la red ===");
		    int total = 0;
		    for (Pc pc : pcs) {
		        for (Puerto p : pc.getPuertos()) {
		            if (!"Abierto".equalsIgnoreCase(p.getEstado())) continue;
		            total++;
		            System.out.print("PC " + pc.getId() + " - Puerto " + p.getNumero());

		            if (p.getVulnerabilidades() != null && !p.getVulnerabilidades().isEmpty()) {
		                System.out.print("  [");
		                for (int i = 0; i < p.getVulnerabilidades().size(); i++) {
		                    System.out.print(p.getVulnerabilidades().get(i).getNombre());
		                    if (i < p.getVulnerabilidades().size() - 1) System.out.print(", ");
		                }
		                System.out.print("]");
		            }
		            System.out.println();
		        }
		    }
		    System.out.println("Total de puertos ABIERTOs en la red: " + total);
		}


		private static void escanearPc(String username) {
		    System.out.print("ID del PC a escanear: ");
		    String id = s.nextLine();
		    Pc pc = buscarPcPorId(id);
		    if (pc == null) {
		        System.out.println("No existe una PC con ese ID.");
		        return;
		    }

		    System.out.println("----------------------------------------");
		    System.out.println("Resultado del escaneo:");
		    System.out.println("PC " + pc.getId() + " | IP: " + pc.getIp() + " | SO: " + pc.getSo());
		    System.out.println("Puertos:");
		    for (Puerto p : pc.getPuertos()) {
		        System.out.print("  - " + p.getNumero() + " (" + p.getEstado() + ")");
		        if ("Abierto".equalsIgnoreCase(p.getEstado())
		                && p.getVulnerabilidades() != null
		                && !p.getVulnerabilidades().isEmpty()) {
		            System.out.print("  [");
		            for (int i = 0; i < p.getVulnerabilidades().size(); i++) {
		                System.out.print(p.getVulnerabilidades().get(i).getNombre());
		                if (i < p.getVulnerabilidades().size() - 1) System.out.print(", ");
		            }
		            System.out.print("]");
		        }
		        System.out.println();
		    }

		    // Nivel de riesgo
		    int v = contarVulnerabilidadesAbiertas(pc);
		    String nivel = nivelRiesgo(v);

		    // Fecha/hora
		    java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
		    String timestamp = ahora.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		    // Construir reporte en texto
		    StringBuilder rep = new StringBuilder();
		    rep.append("=== REPORTE DE ESCANEO ===\n");
		    rep.append("Fecha: ").append(timestamp).append("\n");
		    rep.append("Usuario: ").append(username).append("\n");
		    rep.append("PC: ").append(pc.getId()).append(" | IP: ").append(pc.getIp()).append(" | SO: ").append(pc.getSo()).append("\n");
		    rep.append("Nivel de riesgo: ").append(nivel).append(" (vulns en puertos abiertos: ").append(v).append(")\n");
		    rep.append("Puertos:\n");
		    for (Puerto p : pc.getPuertos()) {
		        rep.append("  - ").append(p.getNumero()).append(" (").append(p.getEstado()).append(")");
		        if ("Abierto".equalsIgnoreCase(p.getEstado())
		                && p.getVulnerabilidades() != null
		                && !p.getVulnerabilidades().isEmpty()) {
		            rep.append("  [");
		            for (int i = 0; i < p.getVulnerabilidades().size(); i++) {
		                rep.append(p.getVulnerabilidades().get(i).getNombre());
		                if (i < p.getVulnerabilidades().size() - 1) rep.append(", ");
		            }
		            rep.append("]");
		        }
		        rep.append("\n");
		    }
		    rep.append("\n");

		    // Guardar en reportes.txt (APPEND)
		    try {
		        java.nio.file.Files.writeString(
		            java.nio.file.Paths.get("reportes.txt"),
		            rep.toString(),
		            java.nio.charset.StandardCharsets.UTF_8,
		            java.nio.file.StandardOpenOption.CREATE,
		            java.nio.file.StandardOpenOption.APPEND
		        );
		        System.out.println("");
		        System.out.println("Reporte guardado en reportes.txt");
		    } catch (Exception e) {
		        System.out.println("No se pudo escribir el reporte: " + e.getMessage());
		    }
		}


		private static void verListaPCsUsuario() {
			for (Pc pc : pcs) {
				System.out.println("--------------");
				System.out.println(pc);
			}
		}

}
