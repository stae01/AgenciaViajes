/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.AvionFachada;
import InterfacesFachada.VueloFachada;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entidades.Vuelo;
import entidades.Avion;
import java.io.IOException;
import negocioFachada.AvionFachadaImpl;
import negocioFachada.VueloFachadaImpl;

public class EliminarVuelosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long idVuelo = Long.parseLong(request.getParameter("idVuelo"));

            VueloFachada vueloFachada = new VueloFachadaImpl();
            Vuelo vuelo = vueloFachada.obtenerVueloPorId(idVuelo); // Para liberar el avión después

            if (vuelo != null) {
                // Eliminar vuelo
                vueloFachada.eliminarVuelo(idVuelo);

                // Liberar avión si estaba asignado
                Avion avion = vuelo.getAvion();
                if (avion != null) {
                    AvionFachada avionFachada = new AvionFachadaImpl();
                    avion.setIsBusy(false);
                    avionFachada.actualizarAvion(avion);
                }

                // Redirigir con mensaje de éxito
                response.sendRedirect(request.getContextPath() + "/listarVuelos?eliminado=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/listarVuelos?error=vueloNoEncontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarVuelos?error=exception");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para eliminar vuelos";
    }
}

