package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexiónBD {
	public static Connection getConexión() {
        Connection cnx= null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver correcto");
            cnx= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/El_BodegónBD","root","mysql"

                    );
            System.out.println("Conexión correcta");

        } catch (Exception e) {
            System.out.println("Error: "+e); //si sale error java.langclassnot foun es porque no hay mysqldriver jdbc, tiene que salir driver correcto

        }
        return cnx;
    }
	public static void main(String[] args) {
	   getConexión();

	}

}
