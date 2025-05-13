/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.ClienteFachada;
import conexion.Conexion;
import conexion.IConexion;
import daos.ClienteDAO;
import daos.exceptions.NonexistentEntityException;
import daos.exceptions.PersistenciaException;
import entidades.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClienteFachadaImpl implements ClienteFachada {

    private final IConexion conexion;
    private final ClienteDAO clienteDAO;

    public ClienteFachadaImpl() {
        this.conexion = new Conexion();
        this.clienteDAO = new ClienteDAO(conexion);
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        try {
            this.clienteDAO.guardarCliente(cliente);
        } catch (PersistenciaException e) {
            Logger.getLogger(ClienteFachadaImpl.class.getName()).log(Level.SEVERE, "Error al guardar cliente", e);
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        try {
            this.clienteDAO.actualizaCliente(cliente);
        } catch (NonexistentEntityException e) {
            Logger.getLogger(ClienteFachadaImpl.class.getName()).log(Level.SEVERE, "Error al actualizar cliente", e);
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        try {
            this.clienteDAO.eliminarCliente(id);
        } catch (NonexistentEntityException e) {
            Logger.getLogger(ClienteFachadaImpl.class.getName()).log(Level.SEVERE, "Error al eliminar cliente", e);
        }
    }

    @Override
    public Cliente consultarCliente(Long id) {
        return this.clienteDAO.obtenerCliente(id);
    }

    @Override
    public List<Cliente> consultarClientes() {
        return this.clienteDAO.obtenerClientes();
    }

    @Override
    public List<Cliente> consultarClientes(int maxResults, int firstResult) {
        return this.clienteDAO.obtenerClientes(maxResults, firstResult);
    }

    @Override
    public int contarClientes() {
        return this.clienteDAO.obtieneTotalClientes();
    }
}
