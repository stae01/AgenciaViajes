/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsAviones;

import InterfacesFachada.AvionFachada;
import entidades.Avion;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import negocioFachada.AvionFachadaImpl;

/**
 *
 * @author pauli
 */
public class AgregarAvionesServlet extends HttpServlet {
    
    private final AvionFachada avionFachada = new AvionFachadaImpl();
    
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
          RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo admin/agregarAvionesForm.jsp");
          dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelo = request.getParameter("modelo");
        String matricula = request.getParameter("matricula");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));

        // Creación de un nuevo objeto avión
        Avion avion = new Avion(modelo, matricula, capacidad, false, new ArrayList<>());

        boolean exito = false;
        try {
            // Llamar a la fachada para guardar el avión
            exito = avionFachada.guardarAvion(avion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirigir según el resultado
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/views/modulo admin/agregarAvionesForm.jsp?exito=true");
        } else {
            response.sendRedirect("/views/modulo admin/agregarAvionesForm.jsp?error=true");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para agregar nuevos aviones";
    }
}