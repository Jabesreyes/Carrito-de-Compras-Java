/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import bean.CarritoBeanLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CarritoServlet", urlPatterns = {"/Carrito", "/"})
public class CarritoServlet extends HttpServlet {

    CarritoBeanLocal carritoEJB = lookupCarritoBeanLocal();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("op")) {
            case "pc":
                addCarrito(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        index(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void addCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idSesion = request.getSession().getId();
            String producto = request.getParameter("nombre");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            float precio = Float.parseFloat(request.getParameter("precio"));
            carritoEJB.agregarItem(idSesion, producto, cantidad, precio);
            request.setAttribute("carrito", carritoEJB.obtenerCarrito(idSesion));
            request.setAttribute("totalProductos", carritoEJB.totalProductos(idSesion));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idSesion = request.getSession().getId();
        request.setAttribute("carrito", carritoEJB.obtenerCarrito(idSesion));
        request.setAttribute("totalProductos", carritoEJB.totalProductos(idSesion));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    //llamada al carrito EJB manual
    private CarritoBeanLocal lookupCarritoBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CarritoBeanLocal) c.lookup("java:global/CarritoWeb/CarritoWeb-ejb/CarritoBean!bean.CarritoBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
