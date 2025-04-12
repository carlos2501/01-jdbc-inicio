import util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public static void main(String[] args) {

    // Ahora utilizamos el singleton que hemos creado en ConexionBD.
    // Como vamos a ejecutar varias sentencias SQL, hay que incluirlos en el try con recursos
    try (Connection conex = ConexionBD.creaConexion();
         Statement stmt = conex.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
         Statement stmt2 = conex.createStatement();
         ResultSet rs2 = stmt2.executeQuery("SELECT * FROM empleado")
         ){

        System.out.println("--------------- Lista de clientes --------------------");
        while(rs.next()){
            System.out.println(rs.getInt("codigo_cliente") + " - " + rs.getString("nombre_cliente"));
        }
        System.out.println("--------------- Lista de empleados --------------------");
        while(rs2.next()){
            System.out.println(rs2.getInt("codigo_empleado") + " - " + rs2.getString("nombre"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
