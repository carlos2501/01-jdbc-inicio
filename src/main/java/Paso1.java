import java.sql.*;

public static void main(String[] args) {
    // Variables para definir la conexión a la BBDD
    String url = "jdbc:mysql://localhost:3306/jardineria";
    String usu = "root";
    String clave = "carlospdl%";

    // Es necesario inicializar las variables que se van a utilizar en el bloque try-catch
    Connection conex = null;
    Statement stmt = null;
    ResultSet rs = null;

    // Bloque de conexión y procesamiento de la respuesta.
    // Recordar que la respuesta es SIEMPRE un conjunto de registros (aunque sea un conjunto vacío -null-)
    try {
        conex = DriverManager.getConnection(url, usu, clave);
        stmt = conex.createStatement();
        rs = stmt.executeQuery("SELECT * FROM cliente");

        // Si existen datos...
        while (rs.next()) {
            System.out.println(rs.getInt("codigo_cliente") + " - " + rs.getString("nombre_cliente"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        /** Al utilizar el finally, nos aaseguramos de que SIEMPRE se cierra la coneción. Sin embargo, como puede
         *  fallar la comunicación es necesario usar el bloque try-catch
         */
        try {
            rs.close();
            stmt.close();
            conex.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
