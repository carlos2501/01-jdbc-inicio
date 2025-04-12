import java.sql.*;

public static void main(String[] args) {
    // Variables para definir la conexión a la BBDD
    String url = "jdbc:mysql://localhost:3306/jardineria";
    String usu = "root";
    String clave = "carlospdl%";

    // Bloque de conexión y procesamiento de la respuesta.
    // Recordar que la respuesta es SIEMPRE un conjunto de registros (aunque sea un conjunto vacío -null-)
    try {
        Connection conex = DriverManager.getConnection(url, usu, clave);
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");

        // Si existen datos...
        while (rs.next()) {
            System.out.println(rs.getInt("codigo_cliente") + " - " + rs.getString("nombre_cliente"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
