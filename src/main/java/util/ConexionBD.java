package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL="jdbc:mysql://localhost:3306/jardineria";
    private static final String USUARIO= "root";
    private static final String CLAVE="carlospdl%";
    // esta variable contiene el objeto conexión -es el SINGLETON-
    private static Connection conex;

    // este método crea una instancia de una conexión a la BBDD
    public static Connection creaConexion() throws SQLException {
        if(conex == null){
            conex = DriverManager.getConnection(URL, USUARIO, CLAVE);
        }
        return conex;

    }
}
