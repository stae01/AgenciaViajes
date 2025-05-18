/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import daos.exceptions.PersistenciaException;
import entidades.Avion;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IAvionDAO {

    public void crearAvion(Avion avion) throws PersistenciaException;
    
    void actualizaAvion(Avion avion) throws NonexistentEntityException;

    public Avion obtenerAvionPorId(Long idAvion) throws PersistenciaException;

    List<Avion> obtenerAviones();
    
    List<Avion> obtenerAviones(int maxResults, int firstResult);
    
    List<Avion> obtenerAviones(boolean all, int maxResults, int firstResult);

    public void eliminarAvion(Long idAvion) throws PersistenciaException;
    
    public List<Avion> buscarAviones(String modelo, Integer capacidad) throws PersistenciaException;
    
    public void guardarAvion(Avion avion) throws PersistenciaException;
    
    int obtieneTotalAviones();
}
