/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsAviones;

import InterfacesFachada.AvionFachada;
import entidades.Avion;
import jakarta.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import negocioFachada.AvionFachadaImpl;

/**
 *
 * @author pauli
 */
@WebServlet("/BuscarAvionesServlet")
public class BuscarAvionesServlet extends HttpServlet {

    private final AvionFachada avionFachada = new AvionFachadaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, javax.servlet.ServletException {

        String modelo = request.getParameter("modelo");
        String capacidadStr = request.getParameter("capacidad");
        Integer capacidad = null;

        try {
            if (capacidadStr != null && !capacidadStr.isBlank()) {
                capacidad = Integer.parseInt(capacidadStr);
            }

            List<Avion> aviones = avionFachada.buscarAviones(modelo, capacidad);

            request.setAttribute("aviones", aviones);
            // Redirigiendo a un JSP donde mostrar los resultados
            request.getRequestDispatcher("/views/avion/resultadosAviones.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "No se pudieron obtener los aviones.");
            request.getRequestDispatcher("/views/forms/buscarAvionesForm.jsp").forward(request, response);
        }
    }
}
