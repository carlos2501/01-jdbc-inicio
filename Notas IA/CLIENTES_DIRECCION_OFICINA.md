## Consulta:
Dada esta clase

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
    
        public List<Cliente> listaDeClientesConOficina() throws SQLException {
        }
    }
genera el código del método listaDeClientesConOficina para visualizar en la consola los datos de la consulta.

## Resultado Resumido:
(No incluye código innecesario para el ejemplo; ya sea porque se genera automáticamente, porque ya existe, o porque 
no afecta al ejemplo y disminuye la legibilidad del mismo)

    public List<Cliente> listaDeClientesConOficina() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(CLIENTES_DIRECCION_OFICINA)) {
    
            // Cabecera de la tabla
            System.out.println("\n=== CLIENTES CON DIRECCIÓN DE OFICINA ===");
            System.out.printf("%-8s | %-25s | %-20s | %-25s | %-6s | %-15s%n",
                "Código", "Cliente", "Vendedor", "Calle", "CP", "Ciudad");
            System.out.println(String.join("", Collections.nCopies(105, "-")));
    
            while (rs.next()) {
                // Obtención de valores
                int codigo = rs.getInt("codigo_cliente");
                String cliente = rs.getString("nombre_cliente");
                String vendedor = rs.getString("Vendedor");
                String calle = rs.getString("Calle");
                String cp = rs.getString("CP");
                String ciudad = rs.getString("ciudad");
    
                // Impresión formateada
                System.out.printf("%-8d | %-25s | %-20s | %-25s | %-6s | %-15s%n",
                    codigo, limitarLongitud(cliente, 25), limitarLongitud(vendedor, 20),
                    limitarLongitud(calle, 25), cp, ciudad);
                
                // Opcional: Si quisieras cargar datos básicos en objetos Cliente
                Cliente c = new Cliente();
                c.setCodigoCliente(codigo);
                c.setNombreCliente(cliente);
                clientes.add(c);
                }
        }
        return clientes;
    }

    // Método auxiliar para formato de consola
    private String limitarLongitud(String texto, int maxLength) {
    return texto.length() > maxLength ? texto.substring(0, maxLength - 3) + "..." : texto;
    }

## Acciones realizadas con la respuesta:
- Se ha desechado la parte de cargar datos básicos del cliente
- Se mantiene la impresión en el repositorio ya que no se pueden asignar los resultados a ninguna clase por tener datos
de varias tablas.
