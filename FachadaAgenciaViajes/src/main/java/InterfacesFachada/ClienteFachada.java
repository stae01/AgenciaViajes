/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Cliente;
import java.util.List;

/**
 *
 * @author user
 */
public interface ClienteFachada {
    void guardarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(Long id);
    Cliente consultarCliente(Long id);
    List<Cliente> consultarClientes();
    List<Cliente> consultarClientes(int maxResults, int firstResult);
    int contarClientes();
}
