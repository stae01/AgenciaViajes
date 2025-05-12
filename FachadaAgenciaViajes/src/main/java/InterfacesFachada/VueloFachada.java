/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Vuelo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface VueloFachada {
    void crearVuelo(Vuelo vuelo);
    Vuelo obtenerVueloPorId(Long idVuelo);
    List<Vuelo> obtenerVuelosPorOrigenDestino(String origen, String destino);
    List<Vuelo> obtenerVuelosPorFecha(Date fecha);
    List<Vuelo> obtenerTodosLosVuelos();
    void eliminarVuelo(Long idVuelo);
}
