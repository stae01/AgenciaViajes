/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import daos.exceptions.PersistenciaException;
import entidades.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
public class ReservaDAO implements IReservaDAO {

    private final IConexion conexion;

    public ReservaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crearReserva(Reserva reserva) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo guardar la reserva: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public Reserva obtenerReservaPorId(Long idReserva) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            return em.find(Reserva.class, idReserva);
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener la reserva: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Reserva> obtenerReservasPorIdCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Reserva> cq = cb.createQuery(Reserva.class);
            Root<Reserva> root = cq.from(Reserva.class);
            cq.select(root).where(cb.equal(root.get("cliente").get("id"), idCliente));
            Query query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener reservas por cliente: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public void eliminarReserva(Long idReserva) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try
        {
            em.getTransaction().begin();
            Reserva reserva = em.find(Reserva.class, idReserva);
            if (reserva != null)
            {
                em.remove(reserva);
            }
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo eliminar la reserva: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

}
