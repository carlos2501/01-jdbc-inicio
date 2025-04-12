import java.sql.*;

public static void main(String[] args) {
    // Variables para definir la conexión a la BBDD
    String url = "jdbc:mysql://localhost:3306/jardineria";
    String usu = "root";
    String clave = "carlospdl%";

    // Utilizamos try... con recursos para asegurar el cierre de la conexión
    try (Connection conex = DriverManager.getConnection(url, usu, clave);
         Statement stmt = conex.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")){

        // Si existen datos...
        while (rs.next()) {
            System.out.println(rs.getInt("codigo_cliente") + " - " + rs.getString("nombre_cliente"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
