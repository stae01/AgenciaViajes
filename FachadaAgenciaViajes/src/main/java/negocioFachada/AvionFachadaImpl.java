/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.AvionFachada;
import conexion.Conexion;
import conexion.IConexion;
import daos.AvionDAO;
import daos.exceptions.PersistenciaException;
import entidades.Avion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class AvionFachadaImpl implements AvionFachada {

    private final IConexion conexion;
    private final AvionDAO avionDAO;

    public AvionFachadaImpl() {
        this.conexion = new Conexion();
        this.avionDAO = new AvionDAO(conexion);
    }

    @Override
    public void crearAvion(Avion avion) {
        try {
            avionDAO.crearAvion(avion);
        } catch (PersistenciaException e) {
            Logger.getLogger(AvionFachadaImpl.class.getName()).log(Level.SEVERE, "Error al crear avión", e);
        }
    }

    @Override
    public Avion obtenerAvionPorId(Long idAvion) {
        try {
            return avionDAO.obtenerAvionPorId(idAvion);
        } catch (PersistenciaException e) {
            Logger.getLogger(AvionFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener avión por ID", e);
            return null;
        }
    }

    @Override
    public List<Avion> obtenerTodosLosAviones() {
        try {
            return avionDAO.obtenerTodosLosAviones();
        } catch (PersistenciaException e) {
            Logger.getLogger(AvionFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener todos los aviones", e);
            return null;
        }
    }

    @Override
    public void eliminarAvion(Long idAvion) {
        try {
            avionDAO.eliminarAvion(idAvion);
        } catch (PersistenciaException e) {
            Logger.getLogger(AvionFachadaImpl.class.getName()).log(Level.SEVERE, "Error al eliminar avión", e);
        }
    }
}
