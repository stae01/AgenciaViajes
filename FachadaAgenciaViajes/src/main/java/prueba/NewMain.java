/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import InterfacesFachada.ClienteFachada;
import entidades.Cliente;
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
        
        ClienteFachada clientedao = new ClienteFachadaImpl();
        
        
        Cliente cliente = new Cliente();
        
        clientedao.guardarCliente(cliente);
    }
    
}
