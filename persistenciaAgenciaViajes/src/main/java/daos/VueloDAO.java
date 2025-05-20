/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.google.protobuf.TextFormat.ParseException;
import conexion.IConexion;
import daos.exceptions.PersistenciaException;
import entidades.Vuelo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
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

    public int contarVuelosConFiltros(String origen, String destino, String fechaSalida) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Vuelo> vueloRoot = cq.from(Vuelo.class);

            List<Predicate> predicates = new ArrayList<>();

            if (origen != null && !origen.isEmpty()) {
                predicates.add(cb.equal(vueloRoot.get("origen"), origen));
            }
            if (destino != null && !destino.isEmpty()) {
                predicates.add(cb.equal(vueloRoot.get("destino"), destino));
            }
           if (fechaSalida != null && !fechaSalida.isEmpty()) {
                Date fecha = parseFecha(fechaSalida); // fecha: 2025-05-20 00:00:00
                if (fecha != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fecha);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    Date inicioDia = cal.getTime();

                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MILLISECOND, 999);
                    Date finDia = cal.getTime();

                    predicates.add(cb.between(vueloRoot.get("fechaSalida"), inicioDia, finDia));
                }
            }

            cq.select(cb.count(vueloRoot));
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            return em.createQuery(cq).getSingleResult().intValue();
        } catch (Exception e) {
            throw new PersistenciaException("Error al contar vuelos con filtros: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vuelo> obtenerVuelosConFiltros(String origen, String destino, String fechaSalida, int maxResults, int firstResult) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {

            System.out.println("obtenerVuelosConFiltros - Parámetros recibidos:");
            System.out.println("Origen: " + origen);
            System.out.println("Destino: " + destino);
            System.out.println("FechaSalida: " + fechaSalida);
            System.out.println("maxResults: " + maxResults + ", firstResult: " + firstResult);

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Vuelo> cq = cb.createQuery(Vuelo.class);
            Root<Vuelo> vueloRoot = cq.from(Vuelo.class);

            List<Predicate> predicates = new ArrayList<>();

            if (origen != null && !origen.isEmpty()) {
                predicates.add(cb.equal(vueloRoot.get("origen"), origen));
            }
            if (destino != null && !destino.isEmpty()) {
                predicates.add(cb.equal(vueloRoot.get("destino"), destino));
            }
            if (fechaSalida != null && !fechaSalida.isEmpty()) {
                Date fecha = parseFecha(fechaSalida);
                if (fecha != null) {
                    predicates.add(cb.between(
                            vueloRoot.get("fechaSalida"),
                            getStartOfDay(fecha),
                            getEndOfDay(fecha)
                    ));
                }
            }

            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            cq.select(vueloRoot);
            Query query = em.createQuery(cq);
            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener vuelos con filtros: " + e.getMessage());
        } finally {
            em.close();
        }
    }

// Método auxiliar para parsear la fecha
    private Date parseFecha(String fechaStr) throws PersistenciaException {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(VueloDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al parsear la fecha: " + fechaStr, ex);
        }
    }

    @Override
    public List<String> obtenerOrígenesDisponibles() throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            String jpql = "SELECT DISTINCT v.origen FROM Vuelo v ORDER BY v.origen";
            return em.createQuery(jpql, String.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener orígenes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerDestinosDisponibles() throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            String jpql = "SELECT DISTINCT v.destino FROM Vuelo v ORDER BY v.destino";
            return em.createQuery(jpql, String.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener destinos: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerFechasDisponibles(String origen, String destino) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Date> cq = cb.createQuery(Date.class);
            Root<Vuelo> vueloRoot = cq.from(Vuelo.class);

            // Selecciona las fechas con hora (sin usar función DATE)
            cq.select(vueloRoot.get("fechaSalida")).distinct(true)
                    .where(cb.equal(vueloRoot.get("origen"), origen),
                            cb.equal(vueloRoot.get("destino"), destino))
                    .orderBy(cb.asc(vueloRoot.get("fechaSalida")));

            List<Date> fechasConHora = em.createQuery(cq).getResultList();

            // Usamos un LinkedHashSet para eliminar duplicados y mantener orden
            Set<String> fechasSinHora = new LinkedHashSet<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Date fecha : fechasConHora) {
                fechasSinHora.add(sdf.format(fecha));  // Convierte a formato yyyy-MM-dd
            }

            return new ArrayList<>(fechasSinHora);

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener fechas disponibles: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    private Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
}
