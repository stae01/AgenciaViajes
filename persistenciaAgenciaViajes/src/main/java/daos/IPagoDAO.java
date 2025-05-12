/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import daos.exceptions.PersistenciaException;
import entidades.Pago;

/**
 *
 * @author rramirez
 */
public interface IPagoDAO {

    public void crearPago(Pago pago) throws PersistenciaException;

    public Pago obtenerPagoPorId(Long idPago) throws PersistenciaException;

    public Pago obtenerPagoPorIdReserva(Long idReserva) throws PersistenciaException;

    public void eliminarPago(Long idPago) throws PersistenciaException;
    
}
