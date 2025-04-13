import entidades.Empleado;
import repositorios.ClienteRepo;
import repositorios.EmpleadoRepo;

import java.sql.SQLException;
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
    repoCli.listaDeClientesConOficina();
}
