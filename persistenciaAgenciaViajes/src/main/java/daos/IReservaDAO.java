/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import daos.exceptions.PersistenciaException;
import entidades.Reserva;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IReservaDAO {

    public void crearReserva(Reserva reserva) throws PersistenciaException;

    public Reserva obtenerReservaPorId(Long idReserva) throws PersistenciaException;

    public List<Reserva> obtenerReservasPorIdCliente(Long idCliente) throws PersistenciaException;

    public void eliminarReserva(Long idReserva) throws PersistenciaException;

}
