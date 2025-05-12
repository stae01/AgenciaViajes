/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.ReservaFachada;
import conexion.Conexion;
import conexion.IConexion;
import daos.ReservaDAO;
import daos.exceptions.PersistenciaException;
import entidades.Reserva;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ReservaFachadaImpl implements ReservaFachada{

    private final IConexion conexion;
    private final ReservaDAO reservaDAO;

    public ReservaFachadaImpl() {
        this.conexion = new Conexion();
        this.reservaDAO = new ReservaDAO(this.conexion);
    }

    @Override
    public void registrarReserva(Reserva reserva) {
        try {
            reservaDAO.crearReserva(reserva);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ReservaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Reserva consultarReservaPorId(Long idReserva) {
        try {
            return reservaDAO.obtenerReservaPorId(idReserva);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ReservaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Reserva> consultarReservasPorIdCliente(Long idCliente) {
        try {
            return reservaDAO.obtenerReservasPorIdCliente(idCliente);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ReservaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void eliminarReserva(Long idReserva) {
        try {
            reservaDAO.eliminarReserva(idReserva);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ReservaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
