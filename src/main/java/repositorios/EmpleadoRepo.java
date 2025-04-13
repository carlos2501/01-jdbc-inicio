package repositorios;

import entidades.Empleado;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The EmpleadoRepo class represents a repository for managing Employee data.
 * It provides methods to interact with the database and retrieve employee information.
 */
public class EmpleadoRepo {

    private static final String SELECT_EMPLEADOS = "SELECT * FROM empleado";
    private static final String SELECT_EMPLEADOSxOFICINA = "SELECT * FROM empleado ORDER BY codigo_oficina";

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.creaConexion();
    }

    private Statement crearSentencia() throws SQLException {
        return obtenerConexion().createStatement();
    }

    /**
     * Método para mapear el resultado de la consulta SELECT * FROM empleado a una lista de objetos Empleado.
     */
    public List<Empleado> listaDeEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try (Statement stmt = crearSentencia();
             ResultSet rs = stmt.executeQuery(SELECT_EMPLEADOS)) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido1(rs.getString("apellido1"));
                empleado.setApellido2(rs.getString("apellido2")); // Puede ser NULL
                empleado.setExtension(rs.getString("extension"));
                empleado.setEmail(rs.getString("email"));
                empleado.setCodigoOficina(rs.getString("codigo_oficina"));
                empleado.setCodigoJefe(rs.getObject("codigo_jefe") != null ? rs.getInt("codigo_jefe") : null); // Manejo de NULL
                empleado.setPuesto(rs.getString("puesto")); // Puede ser NULL
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes mejorarlo con logs
        }
        return empleados;
    }

    /**
     * Método para obtener los empleados de cada oficina
     */
    public Map<String, List<Empleado>> listaDeEmpleadosPorOficina() {
        // Para tener los empleados de cada oficina, utilizamos un Map en el que la clase es el código de la oficina,
        // y el valor va a ser la lista de empleados de esa oficina
        Map<String, List<Empleado>> empleadosPorOficina = new HashMap<>();

        try (Statement stmt = crearSentencia();
             ResultSet rs = stmt.executeQuery(SELECT_EMPLEADOSxOFICINA)) {
            // Recorremos los registros devueltos por la consulta
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido1(rs.getString("apellido1"));
                empleado.setApellido2(rs.getString("apellido2"));
                empleado.setExtension(rs.getString("extension"));
                empleado.setEmail(rs.getString("email"));
                empleado.setCodigoOficina(rs.getString("codigo_oficina"));
                empleado.setCodigoJefe(rs.getObject("codigo_jefe") != null ? rs.getInt("codigo_jefe") : null);
                empleado.setPuesto(rs.getString("puesto"));

                // Agrupar empleados por oficina.
                empleadosPorOficina
                        // Si la oficina no existe como clave en el Map, creamos un nuevo elemento
                        .computeIfAbsent(empleado.getCodigoOficina(), k -> new ArrayList<>())
                        // añadimos el empleado
                        .add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empleadosPorOficina;
    }
}
