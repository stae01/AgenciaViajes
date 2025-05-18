/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import daos.exceptions.PersistenciaException;
import entidades.Vuelo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IVueloDAO {

    public void crearVuelo(Vuelo vuelo) throws PersistenciaException;

    public Vuelo obtenerVueloPorId(Long idVuelo) throws PersistenciaException;

    public List<Vuelo> obtenerVuelosPorOrigenDestino(String origen, String destino) throws PersistenciaException;

    public List<Vuelo> obtenerVuelosPorFecha(Date fecha) throws PersistenciaException;

    public void eliminarVuelo(Long idVuelo) throws PersistenciaException;
    
    List<Vuelo> obtenerVuelos();
    
    List<Vuelo> obtenerVuelos(int maxResults, int firstResult);
    
    List<Vuelo> obtenerVuelos(boolean all, int maxResults, int firstResult);
    
    int obtieneTotalVuelos();

}
