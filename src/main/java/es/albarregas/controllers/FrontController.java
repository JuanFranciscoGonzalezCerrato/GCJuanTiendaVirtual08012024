package es.albarregas.controllers;

import es.albarregas.connections.Conexion;
import es.albarregas.dao.Consulta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Maneja el método HTTP <code>GET</code>.
     *
     * @param request solicitud al servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

/**
 * Maneja el método HTTP <code>POST</code>. 
 * Este método recibe y procesa las solicitudes POST del cliente, 
 * determina la acción a realizar según los parámetros recibidos y redirige la solicitud a la página correspondiente
 * en función de las acciones realizadas por el usuario en el formulario web.
 *
 * @param request solicitud al servlet
 * @param response respuesta del servlet
 * @throws ServletException si ocurre un error específico del servlet
 * @throws IOException si ocurre un error de entrada/salida
 */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String url = "";
            Connection conexion = null;
            conexion = Conexion.getConnection();

            String boton = request.getParameter("boton");
            String botonVolver = request.getParameter("volver");
            String botonVolverIndex = request.getParameter("volverIndex");
            Consulta consul = new Consulta();
            if (botonVolver != null && botonVolver.equals("true")) {
                url = "/index.jsp";
            }
            if (botonVolver != null && botonVolver.equals("true")) {
                url = "JSP/jsp/MostrarProdAnonimo.jsp";
            }
            if (consul.compUsu(request)) {

                url = "JSP/jsp/MostrarProd.jsp";

            }
            if (boton != null) {
                switch (boton) {
                    case "Registrate":

                        url = "JSP/USUARIO/registro.jsp";
                        break;

                }
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Retorna una breve descripción del servlet.
     *
     * @return devuelve una cadena que contiene la descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
