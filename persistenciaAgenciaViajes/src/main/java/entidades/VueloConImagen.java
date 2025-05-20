/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author pauli
 */
public class VueloConImagen {

    private Vuelo vuelo;
    private String urlImagen;

    public VueloConImagen(Vuelo vuelo, String urlImagen) {
        this.vuelo = vuelo;
        this.urlImagen = urlImagen;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }
    
    
}
