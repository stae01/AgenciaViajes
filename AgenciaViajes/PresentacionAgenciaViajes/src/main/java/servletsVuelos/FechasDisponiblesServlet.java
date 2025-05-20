/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import InterfacesFachada.VueloFachada;
import negocioFachada.VueloFachadaImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
/**
 *
 * @author pauli
 */
public class FechasDisponiblesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (origen == null || origen.isEmpty() || destino == null || destino.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Parámetros 'origen' y 'destino' son obligatorios.\"}");
            return;
        }

        try {
            VueloFachada vueloFachada = new VueloFachadaImpl();
            List<String> fechasDisponibles = vueloFachada.obtenerFechasDisponibles(origen, destino);

            Gson gson = new Gson();
            String json = gson.toJson(fechasDisponibles);

            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.print(json);
                out.flush();
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al obtener fechas disponibles.\"}");
        }
    }
}
