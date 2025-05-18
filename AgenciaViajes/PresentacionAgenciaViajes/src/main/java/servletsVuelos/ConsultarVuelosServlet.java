/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.VueloFachada;
import entidades.Vuelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.VueloFachadaImpl;

/**
 *
 * @author Chris
 */
public class ConsultarVuelosServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VueloFachada vueloFachada = new VueloFachadaImpl();

            // Parámetros de paginación
            int pageSize = 10;
            int paginaActual = 1;

            // Verifica si se envió el número de página
            String paginaParam = request.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e) {
                    paginaActual = 1;
                }
            }

            // Obtener total de productos y calcular total de páginas
            int totalVuelos = vueloFachada.contarVuelos();

// Evitar que totalPaginas sea 0
            int totalPaginas = (int) Math.ceil((double) totalVuelos / pageSize);
            if (totalPaginas < 1) {
                totalPaginas = 1;
            }

// Validar que la página esté dentro del rango
            if (paginaActual < 1) {
                paginaActual = 1;
            }
            if (paginaActual > totalPaginas) {
                paginaActual = totalPaginas;
            }

// Obtener vuelos solo si hay alguno
            int primerResultado = (paginaActual - 1) * pageSize;
            List<Vuelo> vuelos = totalVuelos > 0
                    ? vueloFachada.consultarVuelos(pageSize, primerResultado)
                    : new ArrayList<>();

// Enviar los datos a la vista
            request.setAttribute("vuelos", vuelos);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo admin/consultaVuelos.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/views/modulo admin/consultaVuelos.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
