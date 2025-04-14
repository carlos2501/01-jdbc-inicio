package repositorios;

import DTOs.ClienteOficinaDTO;
import entidades.Cliente;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo {

    private static final String CLIENTES_DIRECCION_OFICINA = """
        SELECT cl.codigo_cliente, cl.nombre_cliente, CONCAT(e.nombre, ' ', e.apellido1) AS Vendedor, o.linea_direccion1 AS Calle, o.codigo_postal AS CP, o.ciudad
        FROM cliente cl
            JOIN jardineria.empleado e ON cl.rep_ventas = e.codigo_empleado
            JOIN jardineria.oficina o ON e.codigo_oficina = o.codigo_oficina
        """;

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.creaConexion();
    }

    public List<Cliente> listaDeClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        // Una buena práctica es sacar la query com constante externa al método
        String query = "SELECT * FROM cliente";

        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFax(rs.getString("fax"));
                cliente.setLineaDireccion1(rs.getString("linea_direccion1"));
                cliente.setLineaDireccion2(rs.getString("linea_direccion2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));

                // Manejo de valores que pueden ser nulos
                int repVentas = rs.getInt("rep_ventas");
                if (rs.wasNull()) {
                    cliente.setRepVentas(null);
                } else {
                    cliente.setRepVentas(repVentas);
                }

                float limiteCredito = rs.getFloat("limite_credito");
                if (rs.wasNull()) {
                    cliente.setLimiteCredito(null);
                } else {
                    cliente.setLimiteCredito(limiteCredito);
                }

                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public List<ClienteOficinaDTO> listaDeClientesConOficina() throws SQLException {
        List<ClienteOficinaDTO> clientesConOficina = new ArrayList<>();
        
        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(CLIENTES_DIRECCION_OFICINA)) {

            while (rs.next()) {
                // Obtención de valores
                ClienteOficinaDTO cofDTO = new ClienteOficinaDTO(
                        rs.getInt("codigo_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("Vendedor"),
                        rs.getString("Calle"),
                        rs.getString("CP"),
                        rs.getString("ciudad")
                );
                clientesConOficina.add(cofDTO);
            }
        }
        return clientesConOficina;
    }
}

