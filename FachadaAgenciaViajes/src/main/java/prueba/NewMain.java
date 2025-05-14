/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import InterfacesFachada.ClienteFachada;
import entidades.Cliente;
import java.util.List;
import negocioFachada.ClienteFachadaImpl;

/**
 *
 * @author Chris
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClienteFachada fachada = new ClienteFachadaImpl();
        System.out.println("Total clientes: " + fachada.contarClientes());
        List<Cliente> clientes = fachada.consultarClientes(10, 0);
        System.out.println("Clientes obtenidos: " + clientes.size());
        clientes.forEach(c -> System.out.println(c.getNombres()));
    
}

}