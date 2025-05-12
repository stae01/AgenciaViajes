/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import daos.exceptions.PersistenciaException;
import entidades.Pago;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
public class PagoDAO implements IPagoDAO {

    private final IConexion conexion;

    public PagoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crearPago(Pago pago) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            em.getTransaction().begin();
            em.persist(pago);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar el pago: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public Pago obtenerPagoPorId(Long idPago) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            return em.find(Pago.class, idPago);
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener el pago: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public Pago obtenerPagoPorIdReserva(Long idReserva) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Pago> cq = cb.createQuery(Pago.class);
            Root<Pago> root = cq.from(Pago.class);
            cq.select(root).where(cb.equal(root.get("reserva").get("id"), idReserva));
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener el pago por reserva: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public void eliminarPago(Long idPago) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            em.getTransaction().begin();
            Pago pago = em.find(Pago.class, idPago);
            if (pago != null)
            {
                em.remove(pago);
            }
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el pago: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }
}
