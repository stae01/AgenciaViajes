/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.VueloFachada;
import entidades.Avion;
import entidades.Vuelo;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.VueloFachadaImpl;

/**
 *
 * @author user
 */
@WebServlet("/agregarVuelo")
public class AgregarVuelosServlet extends HttpServlet {

    private final VueloFachada vueloFachada;

    public AgregarVuelosServlet() {
        this.vueloFachada = new VueloFachadaImpl();
    }

    /**
     * Maneja la solicitud HTTP <code>GET</code>, redirige al formulario de agregar vuelo.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/views/forms/agregarVueloForm.jsp");
    }

    /**
     * Maneja la solicitud HTTP <code>POST</code>, guarda un nuevo vuelo.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Recuperar los parámetros del formulario
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            String fechaSalidaStr = request.getParameter("fechaSalida");
            String fechaLlegadaStr = request.getParameter("fechaLlegada");
            Long idAvion = Long.parseLong(request.getParameter("idAvion"));

            // Convertir las fechas de String a Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date fechaSalida = sdf.parse(fechaSalidaStr);
            Date fechaLlegada = sdf.parse(fechaLlegadaStr);

            // Crear objeto Vuelo
            Vuelo vuelo = new Vuelo(origen, destino, fechaSalida, fechaLlegada, new Avion(), null);

            // Guardar el vuelo usando la fachada
            vueloFachada.crearVuelo(vuelo);

            // Redirigir al formulario con un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/views/forms/agregarVueloForm.jsp?exito=true");
        } catch (Exception e) {
            // En caso de error, redirigir al formulario con un mensaje de error
            response.sendRedirect(request.getContextPath() + "/views/forms/agregarVueloForm.jsp?error=true");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para agregar nuevos vuelos";
    }
}