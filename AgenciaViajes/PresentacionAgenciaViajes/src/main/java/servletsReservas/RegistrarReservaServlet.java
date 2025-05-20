/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsReservas;

import InterfacesFachada.ClienteFachada;
import InterfacesFachada.ReservaFachada;
import entidades.Cliente;
import entidades.Reserva;
import entidades.Vuelo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.ClienteFachadaImpl;
import InterfacesFachada.ReservaFachada;
import InterfacesFachada.VueloFachada;
import javax.servlet.RequestDispatcher;

import negocioFachada.ReservaFachadaImpl;
import negocioFachada.VueloFachadaImpl;

/**
 *
 * @author pauli
 */
public class RegistrarReservaServlet extends HttpServlet {

    private final ReservaFachada reservaFachada = new ReservaFachadaImpl();
    private final ClienteFachada clienteFachada = new ClienteFachadaImpl();
    private final VueloFachada vueloFachada = new VueloFachadaImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener parámetros del formulario
            Long idCliente = Long.parseLong(request.getParameter("idCliente"));
            Long idVuelo = Long.parseLong(request.getParameter("idVuelo"));
            int cantidadPasajeros = Integer.parseInt(request.getParameter("cantidadPasajeros"));

            // Obtener Cliente y Vuelo desde la base de datos
            Cliente cliente = clienteFachada.consultarCliente(idCliente);
            Vuelo vuelo = vueloFachada.obtenerVueloPorId(idVuelo);

            if (cliente == null || vuelo == null) {
                request.setAttribute("error", "No se encontró el cliente o el vuelo.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo_cliente/consultaVuelosCliente.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Crear reserva
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setVuelo(vuelo);
            reserva.setCantidadPasajeros(cantidadPasajeros);
            reserva.setPago(null); // Si tienes lógica de pago, agréguela después

            // Registrar reserva
            reservaFachada.registrarReserva(reserva);

            // Enviar confirmación
            request.setAttribute("mensaje", "Reserva registrada exitosamente.");
            request.setAttribute("reserva", reserva);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo_cliente/confirmacionReserva.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar la reserva: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo_cliente/consultaVuelosCliente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para registrar reservas de vuelos por parte de un cliente.";
    }
}
