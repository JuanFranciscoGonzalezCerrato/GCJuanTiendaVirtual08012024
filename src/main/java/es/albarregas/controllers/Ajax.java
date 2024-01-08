package es.albarregas.controllers;

import es.albarregas.beans.Carrito;
import es.albarregas.beans.Producto;
import es.albarregas.dao.Consulta;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "Ajax", urlPatterns = {"/Ajax"})
public class Ajax extends HttpServlet {

    JSONObject objeto = null;

    JSONArray arrayJSON = null;

    Consulta consul = new Consulta();

    /**
     * Este controlador es el encargado de el flujo de datos en relación de las
     * categorias y sus productos, el cual segun que accion le entre podrá
     * solicitar y devolver los productos de una categoría específica,
     * convirtiendo la información en formato JSON para responder a la solicitud
     * del cliente. O también puede añadir al carrito un producto en concreto y
     * aumenta el contador de los productos del carrito.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String url = "";

        Iterator i;

        switch (request.getParameter("accion")) {

            case "productos":
                arrayJSON = new JSONArray(consul.mostrarProdCat(request, request.getParameter("cat")));
                response.setContentType("application/json");
                response.getWriter().print(arrayJSON);

                break;

            case "addDelCarrito":

                HttpSession session = request.getSession();
                Carrito car = (Carrito) session.getAttribute("carrito");
                Producto p = consul.BuscarProdPK(request.getParameter("idProd"));
                objeto = new JSONObject();
                int cantTotal = 0;

                if (Boolean.parseBoolean(request.getParameter("ope"))) {
                    if (null == car) {
                        car = new Carrito();
                        car.getProductos().put(p, 1);
                    } else {
                        if (null == car.getProductos().get(p)) {
                            car.getProductos().put(p, 1);
                        } else {
                            int aux = 0;
                            aux = car.getProductos().get(p) + 1;
                            car.getProductos().put(p, aux);
                        }
                    }

                } else {
                    int aux = 0;
                    aux = car.getProductos().get(p) - 1;

                    if (aux == 0) {

                    } else {
                        car.getProductos().remove(p);
                    }

                }

                for (Producto prod : car.getProductos().keySet()) {

                    cantTotal = cantTotal + car.getProductos().get(prod);

                }

                session.setAttribute("carrito", car);
                objeto.put("cantTotal", cantTotal);

                response.setContentType("application/json");
                response.getWriter().print(objeto);

                break;

        }
    }

    /**
     *
     * Maneja el método HTTP <code>GET</code>.
     *
     * @param request solicitud servlet
     * @param response respuesta servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Ajax.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Maneja el método HTTP <code>POST</code>.
     *
     * @param request solicitud al servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Ajax.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una breve descripción del servlet.
     *
     * @return devuelve un String que contiene la descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
