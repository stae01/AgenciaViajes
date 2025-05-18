/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.VueloFachada;
import conexion.Conexion;
import conexion.IConexion;
import daos.VueloDAO;
import daos.exceptions.PersistenciaException;
import entidades.Vuelo;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class VueloFachadaImpl implements VueloFachada {

    private final IConexion conexion;
    private final VueloDAO vueloDAO;

    public VueloFachadaImpl() {
        this.conexion = new Conexion();
        this.vueloDAO = new VueloDAO(conexion);
    }

    @Override
    public void crearVuelo(Vuelo vuelo) {
        try {
            vueloDAO.crearVuelo(vuelo);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al crear vuelo", e);
        }
    }

    @Override
    public Vuelo obtenerVueloPorId(Long idVuelo) {
        try {
            return vueloDAO.obtenerVueloPorId(idVuelo);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener vuelo por ID", e);
            return null;
        }
    }

    @Override
    public List<Vuelo> obtenerVuelosPorOrigenDestino(String origen, String destino) {
        try {
            return vueloDAO.obtenerVuelosPorOrigenDestino(origen, destino);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener vuelos por origen y destino", e);
            return null;
        }
    }

    @Override
    public List<Vuelo> obtenerVuelosPorFecha(Date fecha) {
        try {
            return vueloDAO.obtenerVuelosPorFecha(fecha);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener vuelos por fecha", e);
            return null;
        }
    }

    @Override
    public List<Vuelo> obtenerTodosLosVuelos() {
        return this.vueloDAO.obtenerVuelos();

    }

    @Override
    public int contarVuelos() {
        return this.vueloDAO.obtieneTotalVuelos();
    }

    @Override
    public List<Vuelo> consultarVuelos(int maxResults, int firstResult) {
        return this.vueloDAO.obtenerVuelos(maxResults, firstResult);
    }

    @Override
    public void eliminarVuelo(Long idVuelo) {
        try {
            vueloDAO.eliminarVuelo(idVuelo);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al eliminar vuelo", e);
        }
    }
}
