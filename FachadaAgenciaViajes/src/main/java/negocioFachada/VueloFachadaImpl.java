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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PixabayUtil;

/**
 *
 * @author user
 */
public class VueloFachadaImpl implements VueloFachada {

    private final IConexion conexion;
    private final VueloDAO vueloDAO;
    private PixabayUtil pixabayUtil;

    public VueloFachadaImpl() {
        this.conexion = new Conexion();
        this.vueloDAO = new VueloDAO(conexion);
        this.pixabayUtil = new PixabayUtil();
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

    @Override
    public int contarVuelosConFiltros(String origen, String destino, String fechaSalida) {
        try {
            return vueloDAO.contarVuelosConFiltros(origen, destino, fechaSalida);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al contar vuelos con filtros", e);
            return 0;
        }
    }

    @Override
    public List<Vuelo> consultarVuelosConFiltros(String origen, String destino, String fechaSalida, int maxResults, int firstResult) {
        try {
            return vueloDAO.obtenerVuelosConFiltros(origen, destino, fechaSalida, maxResults, firstResult);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al consultar vuelos con filtros", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> obtenerOrígenesDisponibles() {
        try {
            return vueloDAO.obtenerOrígenesDisponibles();
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener orígenes", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> obtenerDestinosDisponibles() {
        try {
            return vueloDAO.obtenerDestinosDisponibles();
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener destinos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> obtenerFechasDisponibles(String origen, String destino) {
        try {
            return vueloDAO.obtenerFechasDisponibles(origen, destino);
        } catch (PersistenciaException e) {
            Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener fechas disponibles", e);
            return Collections.emptyList();
        }
    }
    public String obtenerImagenPorDestino(String destino) {
    try {
        return pixabayUtil.buscarImagen(destino);
    } catch (Exception e) {
        Logger.getLogger(VueloFachadaImpl.class.getName()).log(Level.SEVERE, "Error al obtener imagen", e);
        return null;
    }
}

}
