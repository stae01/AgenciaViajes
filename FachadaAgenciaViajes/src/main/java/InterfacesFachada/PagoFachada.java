/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Pago;

/**
 *
 * @author user
 */
public interface PagoFachada {
    void crearPago(Pago pago);
    Pago obtenerPagoPorId(Long idPago);
    Pago obtenerPagoPorIdReserva(Long idReserva);
    void eliminarPago(Long idPago);
}
