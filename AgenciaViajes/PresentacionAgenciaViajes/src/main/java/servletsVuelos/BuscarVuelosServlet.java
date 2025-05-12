/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsVuelos;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author user
 */
@WebServlet("/BuscarVuelosServlet")
public class BuscarVuelosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros de la búsqueda (origen, destino, fechas, etc.)
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        String fechaSalida = request.getParameter("fechaSalida");
        String fechaRegreso = request.getParameter("fechaRegreso");
        int numPasajeros = Integer.parseInt(request.getParameter("numPasajeros"));
        
        // Lógica para buscar vuelos (esto es solo un ejemplo, podrías hacer una consulta a la base de datos)
        //List<Vuelo> vuelosDisponibles = buscarVuelos(origen, destino, fechaSalida, fechaRegreso, numPasajeros);
        
        // Enviar la lista de vuelos a la página JSP
        //request.setAttribute("vuelosDisponibles", vuelosDisponibles);
        
        // Redirigir a la página de resultados de la búsqueda
        RequestDispatcher dispatcher = request.getRequestDispatcher("resultadosBusqueda.jsp");
        dispatcher.forward(request, response);
    }
    
//    private List<Vuelo> buscarVuelos(String origen, String destino, String fechaSalida, String fechaRegreso, int numPasajeros) {
//        // Lógica de búsqueda, normalmente consultando la base de datos
//        return VueloDAO.buscarVuelos(origen, destino, fechaSalida, fechaRegreso, numPasajeros);
//    }
}
