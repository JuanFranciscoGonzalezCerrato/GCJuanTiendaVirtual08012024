package es.albarregas.connections;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {

    static DataSource dataSource = null;
    static Connection conexion = null;

    /**
     * Método estático que se encarga de obtener la conexion con la base de
     * datos
     *
     * @return
     */
    public static Connection getConnection() {

        try {

            Context contextoInicial = new InitialContext();
            dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/APool");
            conexion = dataSource.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return conexion;
    }

    /**
     *Método encargado de cerrar la conexion con la base de datos.
     */
    public static void closeConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
