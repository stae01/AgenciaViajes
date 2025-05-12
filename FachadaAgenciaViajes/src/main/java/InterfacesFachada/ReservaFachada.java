/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Reserva;
import java.util.List;

/**
 *
 * @author user
 */
public interface ReservaFachada {
    Reserva consultarReservaPorId(Long idReserva);
    List<Reserva> consultarReservasPorIdCliente(Long idCliente);
    void eliminarReserva(Long idReserva);
    void registrarReserva(Reserva reserva);
    }
