/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.VueloFachada;
import entidades.Vuelo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import negocioFachada.VueloFachadaImpl;

/**
 *
 * @author user
 */
@WebServlet("/BuscarVuelosServlet")

public class BuscarVuelosServlet extends HttpServlet {

    private final VueloFachada vueloFachada;

    public BuscarVuelosServlet() {
        this.vueloFachada = new VueloFachadaImpl();
    }

    /**
     * Maneja la solicitud HTTP <code>GET</code>, realiza la búsqueda de vuelos.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        String fechaStr = request.getParameter("fecha");
        Date fecha = null;

        try {
            if (fechaStr != null && !fechaStr.isBlank()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            }

            List<Vuelo> vuelos = vueloFachada.obtenerVuelosPorOrigenDestino(origen, destino);

            if (fecha != null) {
                LocalDate fechaBusqueda = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                vuelos.removeIf(vuelo -> !compareDates(vuelo.getFechaSalida(), fechaBusqueda));
            }

            request.setAttribute("vuelos", vuelos);

            // Redirigir a la página de resultados
            request.getRequestDispatcher("/views/vuelo/resultadosVuelos.jsp").forward(request, response);

           
        } catch (Exception e) {
            // En caso de error, redirigir al formulario de búsqueda con un mensaje de error
            request.setAttribute("error", "No se pudieron obtener los vuelos.");
            request.getRequestDispatcher("/views/forms/buscarVuelosForm.jsp").forward(request, response);
        }
    }

    /**
     * Método para comparar las fechas solo por día, mes y año, sin considerar la hora.
     */
    private boolean compareDates(Date fechaVuelo, LocalDate fechaBusqueda) {
        LocalDate vueloLocalDate = fechaVuelo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return vueloLocalDate.equals(fechaBusqueda);
    }
}