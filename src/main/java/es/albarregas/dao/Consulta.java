package es.albarregas.dao;

import es.albarregas.beans.Producto;
import es.albarregas.beans.Usuario;
import es.albarregas.connections.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Consulta {

    Connection conexion = null;
    Statement sentencia = null;
    PreparedStatement preparada = null;

    /**
     * Comprueba si un usuario está registrado en la base de datos.
     *
     * @param request solicitud al servlet
     * @return true si el usuario está registrado, false si no lo está
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean compUsu(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Conexion.closeConexion();
        conexion = Conexion.getConnection();
        sentencia = conexion.createStatement();
        int cont = 0;
        boolean retorno = false;
        ResultSet rs = sentencia.executeQuery("SELECT * FROM usuarios where Email='" + request.getParameter("email") + "' AND Password ='" + request.getParameter("password") + "';");
        Usuario u = null;
        while (rs.next()) {
            u = new Usuario();
            u.setEmail(rs.getString("Email"));
            u.setPassword(rs.getString("Password"));
            u.setNombre(rs.getString("Nombre"));
            u.setApellidos(rs.getString("Apellidos"));
            u.setNIF(rs.getString("NIF"));
            u.setTelefono(rs.getString("Telefono"));
            u.setDireccion(rs.getString("Direccion"));
            u.setCodigoPostal(rs.getString("CodigoPostal"));
            u.setLocalidad(rs.getString("Localidad"));
            u.setUltimoAcceso(rs.getDate("UltimoAcceso"));
            u.setProvincia(rs.getString("Provincia"));
            //       u.setAvatarCodificado(rs.getString("AvatarCodificado"));
            //      u.setImagenAvatar(rs.getBytes("ImagenAvatar"));
            cont++;
        }
        if (cont >= 1) {
            retorno = true;
        } else {
            retorno = false;
        }
        session.setAttribute("usuarioLogueado", u);
        if (rs != null) {
            rs.close();
        }
        if (sentencia != null) {
            sentencia.close();
        }
        if (conexion != null) {
            conexion.close();
        }
        return retorno;
    }

    /**
     * Comprueba si un usuario ya existe en la base de datos.
     *
     * @param email email del usuario a verificar
     * @return true si el usuario ya existe, false si no existe
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean usuarioExiste(String email) throws SQLException {
        boolean existe = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Conexion.closeConexion();
            conexion = Conexion.getConnection();
            String query = "SELECT COUNT(*) AS count FROM usuarios WHERE Email = ?";
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                existe = count > 0;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
        return existe;
    }

    /**
     * Obtiene una lista de productos pertenecientes a una categoría específica.
     *
     * @param request solicitud al servlet
     * @param cat identificador de la categoría de los productos
     * @return una lista de productos de la categoría especificada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Producto> mostrarProdCat(HttpServletRequest request, String cat) throws SQLException {
        Conexion.closeConexion();
        conexion = Conexion.getConnection();
        sentencia = conexion.createStatement();
        List retorno = new ArrayList<Producto>();
        HttpSession session = request.getSession();
        Producto p = null;
        ResultSet rs = sentencia.executeQuery("SELECT * FROM productos where IdCategoria='" + cat + "';");

        while (rs.next()) {
            p = new Producto();
            p.setIdProducto(rs.getInt("IdProducto"));
            p.setIdCategoria(rs.getInt("IdCategoria"));
            p.setNombre(rs.getString("Nombre"));
            p.setDescripcion(rs.getString("Descripcion"));
            p.setPrecio(rs.getDouble("Precio"));
            p.setMarca(rs.getString("Marca"));
            p.setImagen(rs.getString("Imagen"));
            retorno.add(p);
        }
        if (rs != null) {
            rs.close();
        }
        if (sentencia != null) {
            sentencia.close();
        }
        if (conexion != null) {
            conexion.close();
        }
        return retorno;
    }

    /**
     * Busca un producto por su clave primaria (ID).
     *
     * @param id identificador del producto
     * @return el producto correspondiente al ID especificado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Producto BuscarProdPK(String id) throws SQLException {
        Conexion.closeConexion();
        conexion = Conexion.getConnection();
        sentencia = conexion.createStatement();
        List retorno = new ArrayList<Producto>();
        Producto p = null;
        ResultSet rs = sentencia.executeQuery("SELECT * FROM productos where IdProducto='" + id + "';");

        while (rs.next()) {
            p = new Producto();
            p.setIdProducto(rs.getInt("IdProducto"));
            p.setIdProducto(rs.getInt("IdCategoria"));
            p.setNombre(rs.getString("Nombre"));
            p.setDescripcion(rs.getString("Descripcion"));
            p.setPrecio(rs.getDouble("Precio"));
            p.setMarca(rs.getString("Marca"));
            p.setImagen(rs.getString("Imagen"));
            retorno.add(p);
        }
        if (rs != null) {
            rs.close();
        }
        if (sentencia != null) {
            sentencia.close();
        }
        if (conexion != null) {
            conexion.close();
        }
        return (Producto) retorno.get(0);
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario usuario a ser insertado en la base de datos
     * @return true si la inserción fue exitosa, false si falló
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean insertarUsuario(Usuario usuario) throws SQLException {
        Conexion.closeConexion();
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        boolean insercionExitosa = false;

        try {
            conexion = Conexion.getConnection();
            String query = "INSERT INTO usuarios (Email, Password, Nombre, Apellidos, NIF, Telefono, Direccion, CodigoPostal, Localidad, Provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conexion.prepareStatement(query);

            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getNombre());
            preparedStatement.setString(4, usuario.getApellidos());
            preparedStatement.setString(5, usuario.getNIF());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getDireccion());
            preparedStatement.setString(8, usuario.getCodigoPostal());
            preparedStatement.setString(9, usuario.getLocalidad());
            preparedStatement.setString(10, usuario.getProvincia());

            int filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas > 0) {
                insercionExitosa = true;
            }
        } finally {
            // Cerramos recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

        return insercionExitosa;
    }

}
