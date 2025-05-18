/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import daos.exceptions.PersistenciaException;
import entidades.Vuelo;
import java.util.Date;
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
public class VueloDAO implements IVueloDAO {

    private final IConexion conexion;

    public VueloDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crearVuelo(Vuelo vuelo) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(vuelo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el vuelo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Vuelo obtenerVueloPorId(Long idVuelo) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            return em.find(Vuelo.class, idVuelo);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vuelo> obtenerVuelosPorOrigenDestino(String origen, String destino) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Vuelo> cq = cb.createQuery(Vuelo.class);
            Root<Vuelo> vueloRoot = cq.from(Vuelo.class);
            cq.select(vueloRoot)
                    .where(cb.equal(vueloRoot.get("origen"), origen),
                            cb.equal(vueloRoot.get("destino"), destino));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener vuelos por origen y destino: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vuelo> obtenerVuelosPorFecha(Date fecha) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Vuelo> cq = cb.createQuery(Vuelo.class);
            Root<Vuelo> vueloRoot = cq.from(Vuelo.class);
            cq.select(vueloRoot)
                    .where(cb.equal(vueloRoot.get("fechaSalida"), fecha));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener vuelos por fecha: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vuelo> obtenerVuelos() {
        return obtenerVuelos(true, -1, -1);
    }

    @Override
    public List<Vuelo> obtenerVuelos(int maxResults, int firstResult) {
        return obtenerVuelos(false, maxResults, firstResult);
    }

    @Override
    public List<Vuelo> obtenerVuelos(boolean all, int maxResults, int firstResult) {
        EntityManager em = this.conexion.crearConexion();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vuelo.class));
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

    @Override
    public int obtieneTotalVuelos() {
        EntityManager em = this.conexion.crearConexion();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vuelo> rt = cq.from(Vuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarVuelo(Long idVuelo) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Vuelo vuelo = em.find(Vuelo.class, idVuelo);
            if (vuelo != null) {
                em.remove(vuelo);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el vuelo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
