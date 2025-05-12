/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.PagoFachada;
import conexion.IConexion;
import daos.PagoDAO;
import daos.exceptions.PersistenciaException;
import entidades.Pago;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PagoFachadaImpl implements PagoFachada {

    private final PagoDAO pagoDAO;

    public PagoFachadaImpl(IConexion conexion) {
        this.pagoDAO = new PagoDAO(conexion);
    }

    @Override
    public void crearPago(Pago pago) {
        try {
            pagoDAO.crearPago(pago);
        } catch (PersistenciaException e) {
            Logger.getLogger(PagoFachadaImpl.class.getName()).log(Level.SEVERE, "Error al crear el pago", e);
        }
    }

    @Override
    public Pago obtenerPagoPorId(Long idPago) {
        try {
            return pagoDAO.obtenerPagoPorId(idPago);
        } catch (PersistenciaException e) {
            Logger.getLogger(PagoFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener el pago por ID", e);
            return null; 
        }
    }

    @Override
    public Pago obtenerPagoPorIdReserva(Long idReserva) {
        try {
            return pagoDAO.obtenerPagoPorIdReserva(idReserva);
        } catch (PersistenciaException e) {
            Logger.getLogger(PagoFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener el pago por ID de reserva", e);
            return null; 
        }
    }

    @Override
    public void eliminarPago(Long idPago) {
        try {
            pagoDAO.eliminarPago(idPago);
        } catch (PersistenciaException e) {
            Logger.getLogger(PagoFachadaImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el pago", e);
        }
    }
}