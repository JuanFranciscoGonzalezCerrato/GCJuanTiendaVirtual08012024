package es.albarregas.controllers;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.Consulta;
import es.albarregas.models.MD5;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "InsertarUsuario", urlPatterns = {"/InsertarUsuario"})
public class InsertarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

/**
 * Maneja el método HTTP <code>GET</code>. 
 * Si por algún motivo se entra por este método se le redigirá al index.jsp.
 * @param request solicitud al servlet
 * @param response respuesta del servlet
 * @throws ServletException si ocurre un error específico del servlet
 * @throws IOException si ocurre un error de entrada/salida
 */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }

/**
 * Maneja el método HTTP <code>POST</code>. 
 * Este método recibe datos del cliente a través de una solicitud POST,
 * verifica si el usuario ya existe en la base de datos y realiza la inserción de un nuevo usuario si no existe, 
 * redirigiendo al usuario a la página de inicio una vez completada la operación.
 *
 * @param request solicitud al servlet con los datos del usuario a registrar
 * @param response respuesta del servlet al cliente después de la inserción del usuario
 * @throws ServletException si ocurre un error específico del servlet
 * @throws IOException si ocurre un error de entrada/salida
 */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String nif = request.getParameter("nif");
        String direccion = request.getParameter("direccion");
        String codigoPostal = request.getParameter("cp");
        String localidad = request.getParameter("localidad");
        String provincia = request.getParameter("provincia");

        
        Usuario usu = new Usuario();
        usu.setNombre(nombre);
        usu.setApellidos(apellidos);
        usu.setTelefono(telefono);
        try {
            usu.setPassword(MD5.Codificar(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InsertarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        usu.setEmail(email);
        usu.setNIF(nif);
        usu.setDireccion(direccion);
        usu.setCodigoPostal(codigoPostal);
        usu.setLocalidad(localidad);
        usu.setProvincia(provincia);
        
        Consulta consulta = new Consulta();
        boolean usuarioExiste = true;
        try {
            usuarioExiste = consulta.usuarioExiste(email);
            if (!usuarioExiste) {
                boolean insercionExitosa = false;
                try {
                    insercionExitosa = consulta.insertarUsuario(usu);
                } catch (SQLException ex) {
                    Logger.getLogger(InsertarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (insercionExitosa) {
                    System.out.println("Usuario registrado correctamente");
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                } else {
                    System.out.println("No se pudo registrar al usuario");
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
            } else {
                System.out.println("Usuario ya existe");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
