/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsReservas;

import servletsClientes.*;
import entidades.Reserva;
import InterfacesFachada.ReservaFachada;
import entidades.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocioFachada.ReservaFachadaImpl;

/**
 *
 * @author pauli
 */
public class ConsultarReservasClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        ReservaFachada reservaFachada = new ReservaFachadaImpl();
        List<Reserva> reservas = reservaFachada.consultarReservasPorIdCliente(cliente.getId());

        request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("/views/modulo_cliente/verReservas.jsp").forward(request, response);
    }
}

