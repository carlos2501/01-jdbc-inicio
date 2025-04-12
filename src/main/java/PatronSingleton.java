import util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public static void main(String[] args) {
    // Ahora utilizamos el singleton que hemos creado en ConexionBD
    try (Connection conex = ConexionBD.creaConexion();
         Statement stmt = conex.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")){

        while(rs.next()){
            System.out.println(rs.getInt("codigo_cliente") + " - " + rs.getString("nombre_cliente"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
