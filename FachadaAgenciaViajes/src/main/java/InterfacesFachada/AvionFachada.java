/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Avion;
import java.util.List;

/**
 *
 * @author user
 */
public interface AvionFachada {
    void crearAvion(Avion avion);
    void eliminarAvion(Long idAvion);
    Avion obtenerAvionPorId(Long idAvion);
    List<Avion> buscarAviones(String modelo, Integer capacidad);
    public boolean guardarAvion(Avion avion);
    void actualizarAvion(Avion avion);
    int contarAviones();
    List<Avion> consultarAviones();
    List<Avion> consultarAviones(int maxResults, int firstResult);
}
