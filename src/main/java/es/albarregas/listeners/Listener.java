package es.albarregas.Listeners;

import es.albarregas.controllers.FrontController;
import es.albarregas.connections.Conexion;
import es.albarregas.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class Listener implements ServletContextListener, ServletContextAttributeListener,
        HttpSessionListener, HttpSessionAttributeListener,
        HttpSessionActivationListener, HttpSessionBindingListener,
        ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Se ha invocado contextInitialized ya que se ha inicado la aplicación");
        ServletContext context = sce.getServletContext();

        try {
            Connection conexion = null;
            Statement sentencia = null;
            PreparedStatement preparada = null;

            conexion = Conexion.getConnection();
            sentencia = conexion.createStatement();

            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias;");

            List<Categoria> listaCate = new ArrayList<>();
            Categoria c = null;

            while (rs.next()) {
                c = new Categoria();
                c.setIdCategoria(rs.getInt("IdCategoria"));
                c.setNombre(rs.getString("Nombre"));
                c.setImagen(rs.getString("Imagen"));
                listaCate.add(c);
            }

            rs.close();

            context.setAttribute("listaCate", listaCate);

        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cuando se para la aplicación
        System.out.println("Se ha invocado contextDestroyed ya que se ha detenido la aplicación");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        // Cuando se añade un atributo de contexto
        System.out.println("Se ha invocado attributeAdded de ServletContextAttributeEvent. Se ha añadido el atributo " + event.getName());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        // Cuando se elimina un atributo del contexto
        System.out.println("Se ha invocado attributeRemoved de ServletContextAttributeEvent...");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
        // Cuando se modifica un atributo del contexto
        System.out.println("Se ha invocado attributeReplaced de ServletContextAttributeEvent...");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Cuando se crea una sesión
        System.out.println("Se ha invocado sessionCreated...");
        HttpSession session = se.getSession();
        System.out.println("A las " + getTime() + " se creo la sesion con \nID: "
                + session.getId() + "\nMaxInactiveInterval="
                + session.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Cuando se elimina una sesión
        System.out.println("Se ha invocado sessionDestroyed...");
        HttpSession session = se.getSession();
        System.out.println("A las " + getTime() + " se destruyo la sesion con \nID: " + session.getId());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // Cuando se añade un atributo de sesión
        System.out.println("Se ha invocado attributeAdded de HttpSessionBindingEvent...");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        // Cuando se elimina un atributo de sesión
        System.out.println("Se ha invocado attributeRemoved de HttpSessionBindingEvent...");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Se ha invocado attributeReplaced de HttpSessionBindingEvent...");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("Se ha invocado sessionWillPassivate...");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("Se ha invocado sessionDidActivate...");
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("Se ha invocado valueBound...");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("Se ha invocado valueUnbound...");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Se ha invocado requestDestroyed...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Se ha invocado requestInitialized...");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha invocado attributeAdded de ServletRequestAttributeEvent...");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha invocado attributeRemoved de ServletRequestAttributeEvent...");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha invocado attributeReplaced de ServletRequestAttributeEvent...");
    }

    private String getTime() {
        return new Date(System.currentTimeMillis()).toString();
    }
}
