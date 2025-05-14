/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import daos.exceptions.NonexistentEntityException;
import daos.exceptions.PersistenciaException;
import entidades.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author user
 */
public class ClienteDAO {

    private final IConexion conexion;

    public ClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public void guardarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = this.conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo agregar el cliente a la base de datos: " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void actualizaCliente(Cliente cliente) throws NonexistentEntityException {
        EntityManager em = this.conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Cliente clienteExistente = em.find(Cliente.class, cliente.getId());
            if (clienteExistente == null) {
                throw new NonexistentEntityException("El cliente con ID " + cliente.getId() + " no existe.");
            }

            em.merge(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new NonexistentEntityException("No se pudo actualizar el cliente: " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarCliente(Long idCliente) throws NonexistentEntityException {
        EntityManager em = this.conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new NonexistentEntityException("El cliente con ID " + idCliente + " no existe.");
            }

            em.remove(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new NonexistentEntityException("No se pudo eliminar el cliente: " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Cliente autenticar(String email, String password) {
        EntityManager em = this.conexion.crearConexion();
        Cliente cliente = null;
        try {
            cliente = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email AND c.password = :password", Cliente.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            cliente = null;
        } catch (Exception e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, "Error al autenticar cliente", e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        return cliente;
    }

    public List<Cliente> obtenerClientes() {
        return obtenerClientes(true, -1, -1);
    }

    public List<Cliente> obtenerClientes(int maxResults, int firstResult) {
        return obtenerClientes(false, maxResults, firstResult);
    }

    private List<Cliente> obtenerClientes(boolean all, int maxResults, int firstResult) {
        EntityManager em = this.conexion.crearConexion();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Cliente buscarPorEmail(String email) {
        EntityManager em = conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Cliente obtenerCliente(Long id) {
        EntityManager em = this.conexion.crearConexion();
        try {
            return em.find(Cliente.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public int obtieneTotalClientes() {
        EntityManager em = this.conexion.crearConexion();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
