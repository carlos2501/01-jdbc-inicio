import entidades.Empleado;
import repositorios.ClienteRepo;
import repositorios.EmpleadoRepo;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public static void main(String[] args) throws SQLException {

    ClienteRepo repoCli = new ClienteRepo();
    EmpleadoRepo repoEmp = new EmpleadoRepo();

    System.out.println("-------------- Lista de clientes ------------");
    repoCli.listaDeClientes().forEach(System.out::println);
    System.out.println("-------------- Lista de empleados ------------");
    repoEmp.listaDeEmpleados().forEach(System.out::println);
    System.out.println("-------------- Lista de empleados x oficina ------------");
    Map<String, List<Empleado>> empleadosPorOficina = repoEmp.listaDeEmpleadosPorOficina();
    for (Map.Entry<String, List<Empleado>> entry : empleadosPorOficina.entrySet()) {
        System.out.println("Código de Oficina: " + entry.getKey());
        System.out.println("Empleados:");
        for (Empleado empleado : entry.getValue()) {
            System.out.println(empleado);
        }
        System.out.println("----------------------------");
    }
    System.out.println("\n-------------- CLIENTES CON DIRECCIÓN DE OFICINA ------------------");
    // Cabecera de la tabla
    System.out.printf("%-8s | %-25s | %-20s | %-25s | %-6s | %-15s%n",
            "Código", "Cliente", "Vendedor", "Calle", "CP", "Ciudad");
    System.out.println(String.join("", Collections.nCopies(105, "-")));
    // Impresión formateada

    repoCli.listaDeClientesConOficina().forEach(cofDTO ->
            System.out.printf("%-8d | %-25s | %-20s | %-25s | %-6s | %-15s%n",
                    cofDTO.codigo(),
                    limitarLongitud(cofDTO.nombre(), 25),
                    limitarLongitud(cofDTO.vendedor(), 20),
                    limitarLongitud(cofDTO.calle(), 25),
                    cofDTO.CP(),
                    cofDTO.ciudad()
            )
    );
}

// Método auxiliar para formato de consola
private static String limitarLongitud(String texto, int maxLength) {
    return texto.length() > maxLength ? texto.substring(0, maxLength - 3) + "..." : texto;
}
