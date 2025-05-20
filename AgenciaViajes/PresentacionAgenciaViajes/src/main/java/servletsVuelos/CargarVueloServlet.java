/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import entidades.Vuelo;
import entidades.Cliente;
import InterfacesFachada.VueloFachada;
import negocioFachada.VueloFachadaImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author pauli
 */
public class CargarVueloServlet extends HttpServlet {

    private final VueloFachada vueloFachada = new VueloFachadaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramIdVuelo = request.getParameter("idVuelo");
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (paramIdVuelo != null && cliente != null) {
            try {
                Long idVuelo = Long.parseLong(paramIdVuelo);
                Vuelo vuelo = vueloFachada.obtenerVueloPorId(idVuelo);
                if (vuelo != null) {
                    request.setAttribute("vuelo", vuelo);
                    request.getRequestDispatcher("/views/modulo_cliente/reservaVuelo.jsp")
                            .forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // idVuelo no válido
            }
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vuelo no encontrado o sesión inválida.");
    }
}