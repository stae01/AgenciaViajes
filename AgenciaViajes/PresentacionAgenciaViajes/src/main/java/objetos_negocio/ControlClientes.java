/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos_negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ControlClientes {
    
        private static List<Cliente> clientes = new ArrayList<>();
        
        public static List<Cliente> obtener_lista(){
        return clientes;
    }
    
    
    public static void agregar_cliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    public static void eliminar_cliente(String telefono){
        for (Cliente cliente : clientes) {
            if(cliente.getTelefono() == telefono){
                clientes.remove(cliente);
                break;
            }
        }
    }
    
    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    
}
