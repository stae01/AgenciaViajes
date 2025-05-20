/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.AvionFachada;
import InterfacesFachada.VueloFachada;
import entidades.Avion;
import entidades.Vuelo;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.AvionFachadaImpl;
import negocioFachada.VueloFachadaImpl;

/**
 *
 * @author user
 */
@WebServlet("/agregarVuelo")
public class AgregarVuelosServlet extends HttpServlet {

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

        AvionFachada avionFachada = new AvionFachadaImpl();
        List<Avion> avionesDisponibles = avionFachada.consultarAviones();

        boolean hayDisponibles = false;
        for (Avion avion : avionesDisponibles) {
            if (!avion.isBusy()) {
                hayDisponibles = true;
                break;
            }
        }

        if (!hayDisponibles) {
            // No hay aviones disponibles, redirigir con mensaje
            response.sendRedirect(request.getContextPath() + "/views/modulo admin/agregarAvionesForm.jsp?noDisponibles=true");
            return;
        }

        // Si hay aviones disponibles, continuar con la carga del formulario
        request.setAttribute("aviones", avionesDisponibles);
        request.getRequestDispatcher("/views/modulo admin/agregarVueloForm.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
            AvionFachada avionFachada = new AvionFachadaImpl();
            Avion avion = avionFachada.obtenerAvionPorId(idAvion);

            avion.setIsBusy(true);
            avionFachada.actualizarAvion(avion);

            avion = avionFachada.obtenerAvionPorId(idAvion);

            // Convertir las fechas de String a Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date fechaSalida = sdf.parse(fechaSalidaStr);
            Date fechaLlegada = sdf.parse(fechaLlegadaStr);

            // Crear objeto Vuelo
            Vuelo vuelo = new Vuelo(origen, destino, fechaSalida, fechaLlegada, avion, null);

            // Guardar el vuelo usando la fachada
            VueloFachada vueloFachada = new VueloFachadaImpl();
            vueloFachada.crearVuelo(vuelo);

            // Redirigir al formulario con un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/views/modulo_admin/agregarVueloForm.jsp?exito=true");
        } catch (Exception e) {
            // En caso de error, redirigir al formulario con un mensaje de error
            response.sendRedirect(request.getContextPath() + "/views/modulo_admin/agregarVueloForm.jsp?error=true");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para agregar nuevos vuelos";
    }
}
