/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import daos.exceptions.PersistenciaException;
import entidades.Avion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
public class AvionDAO implements IAvionDAO {

    private final IConexion conexion;

    public AvionDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crearAvion(Avion avion) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(avion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el avión: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Avion obtenerAvionPorId(Long idAvion) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            return em.find(Avion.class, idAvion);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el avión por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avion> obtenerTodosLosAviones() throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Avion> cq = cb.createQuery(Avion.class);
            Root<Avion> root = cq.from(Avion.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los aviones: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarAvion(Long idAvion) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Avion avion = em.find(Avion.class, idAvion);
            if (avion != null) {
                em.remove(avion);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el avión: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Avion> buscarAviones(String modelo, Integer capacidad) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Avion> cq = cb.createQuery(Avion.class);
            Root<Avion> root = cq.from(Avion.class);

            List<Predicate> filtros = new ArrayList<>();

            if (modelo != null && !modelo.isBlank()) {
                filtros.add(cb.like(cb.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%"));
            }

            if (capacidad != null) {
                filtros.add(cb.equal(root.get("capacidad"), capacidad));
            }

            cq.select(root).where(cb.and(filtros.toArray(new Predicate[0])));
            return em.createQuery(cq).getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar aviones: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void guardarAvion(Avion avion) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();

            // Persistir el nuevo avión
            em.persist(avion);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar el avión: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
