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

    static {
        Cliente clienteAdmin = new Cliente("admin", "admin@admin.com", "admin", "12345679", true);
        clientes.add(clienteAdmin);
    }

    public static List<Cliente> obtener_lista() {
        return clientes;
    }

    public static void agregar_cliente(Cliente cliente) {
        clientes.add(cliente);
        imprimirClientes();
    }

    public static void imprimirClientes() {
        // Verificamos si la lista de clientes no está vacía
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Recorremos la lista de clientes e imprimimos cada uno
            for (Cliente cliente : clientes) {
                System.out.println(cliente); // Esto supone que la clase Cliente tiene un método toString() adecuado
            }
        }
    }

    /**
     * public static void eliminar_cliente(String telefono) { for (Cliente
     * cliente : clientes) { if (cliente.getTelefono() == telefono) {
     * clientes.remove(cliente); break; } } }
     */
    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static Cliente autenticar(String email, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        return null;
    }

    public static void eliminarCliente(String email) {
        clientes.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }

    public Cliente buscarCliente(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean editarCliente(String emailOriginal, String nuevoNombre, String nuevoEmail, String nuevaContrasena) {
        Cliente cliente = buscarCliente(emailOriginal);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setEmail(nuevoEmail);
            cliente.setPassword(nuevaContrasena);
            return true;
        }
        return false;
    }
}
