/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DiagramaDeClases.Cliente;
import DiagramaDeClases.Venta;
import DiagramaDeClases.Anuncio;
import DiagramaDeClases.Tasacion;
import DiagramaDeClases.TipoPropiedad;
import DiagramaDeClases.Ciudad;
import DiagramaDeClases.Edificio;
import DiagramaDeClases.Alquiler;
import java.util.ArrayList;
import java.util.List;
import DiagramaDeClases.PagoExpensa;
import DiagramaDeClases.Propiedad;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author matia
 */
public class PropiedadJpaController implements Serializable {

    public PropiedadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propiedad propiedad) {
        if (propiedad.getAlquiler() == null) {
            propiedad.setAlquiler(new ArrayList<Alquiler>());
        }
        if (propiedad.getPagoexpensa() == null) {
            propiedad.setPagoexpensa(new ArrayList<PagoExpensa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clientes = propiedad.getClientes();
            if (clientes != null) {
                clientes = em.getReference(clientes.getClass(), clientes.getCodigo());
                propiedad.setClientes(clientes);
            }
            Venta ventas = propiedad.getVentas();
            if (ventas != null) {
                ventas = em.getReference(ventas.getClass(), ventas.getCodigo());
                propiedad.setVentas(ventas);
            }
            Anuncio anuncios = propiedad.getAnuncios();
            if (anuncios != null) {
                anuncios = em.getReference(anuncios.getClass(), anuncios.getCodigo());
                propiedad.setAnuncios(anuncios);
            }
            Tasacion tasacion = propiedad.getTasacion();
            if (tasacion != null) {
                tasacion = em.getReference(tasacion.getClass(), tasacion.getCodigo());
                propiedad.setTasacion(tasacion);
            }
            TipoPropiedad tipropiedad = propiedad.getTipropiedad();
            if (tipropiedad != null) {
                tipropiedad = em.getReference(tipropiedad.getClass(), tipropiedad.getCodigo());
                propiedad.setTipropiedad(tipropiedad);
            }
            Ciudad ciudades = propiedad.getCiudades();
            if (ciudades != null) {
                ciudades = em.getReference(ciudades.getClass(), ciudades.getCodigo());
                propiedad.setCiudades(ciudades);
            }
            Edificio edificios = propiedad.getEdificios();
            if (edificios != null) {
                edificios = em.getReference(edificios.getClass(), edificios.getCodigo());
                propiedad.setEdificios(edificios);
            }
            List<Alquiler> attachedAlquiler = new ArrayList<Alquiler>();
            for (Alquiler alquilerAlquilerToAttach : propiedad.getAlquiler()) {
                alquilerAlquilerToAttach = em.getReference(alquilerAlquilerToAttach.getClass(), alquilerAlquilerToAttach.getCodigo());
                attachedAlquiler.add(alquilerAlquilerToAttach);
            }
            propiedad.setAlquiler(attachedAlquiler);
            List<PagoExpensa> attachedPagoexpensa = new ArrayList<PagoExpensa>();
            for (PagoExpensa pagoexpensaPagoExpensaToAttach : propiedad.getPagoexpensa()) {
                pagoexpensaPagoExpensaToAttach = em.getReference(pagoexpensaPagoExpensaToAttach.getClass(), pagoexpensaPagoExpensaToAttach.getCodigo());
                attachedPagoexpensa.add(pagoexpensaPagoExpensaToAttach);
            }
            propiedad.setPagoexpensa(attachedPagoexpensa);
            em.persist(propiedad);
            if (clientes != null) {
                clientes.getPropiedades().add(propiedad);
                clientes = em.merge(clientes);
            }
            if (ventas != null) {
                Propiedad oldPropiedadesOfVentas = ventas.getPropiedades();
                if (oldPropiedadesOfVentas != null) {
                    oldPropiedadesOfVentas.setVentas(null);
                    oldPropiedadesOfVentas = em.merge(oldPropiedadesOfVentas);
                }
                ventas.setPropiedades(propiedad);
                ventas = em.merge(ventas);
            }
            if (anuncios != null) {
                anuncios.getPropiedades().add(propiedad);
                anuncios = em.merge(anuncios);
            }
            if (tasacion != null) {
                Propiedad oldPropiedadesOfTasacion = tasacion.getPropiedades();
                if (oldPropiedadesOfTasacion != null) {
                    oldPropiedadesOfTasacion.setTasacion(null);
                    oldPropiedadesOfTasacion = em.merge(oldPropiedadesOfTasacion);
                }
                tasacion.setPropiedades(propiedad);
                tasacion = em.merge(tasacion);
            }
            if (tipropiedad != null) {
                tipropiedad.getPropiedades().add(propiedad);
                tipropiedad = em.merge(tipropiedad);
            }
            if (ciudades != null) {
                ciudades.getPropiedades().add(propiedad);
                ciudades = em.merge(ciudades);
            }
            if (edificios != null) {
                edificios.getPropiedades().add(propiedad);
                edificios = em.merge(edificios);
            }
            for (Alquiler alquilerAlquiler : propiedad.getAlquiler()) {
                Propiedad oldPropiedadOfAlquilerAlquiler = alquilerAlquiler.getPropiedad();
                alquilerAlquiler.setPropiedad(propiedad);
                alquilerAlquiler = em.merge(alquilerAlquiler);
                if (oldPropiedadOfAlquilerAlquiler != null) {
                    oldPropiedadOfAlquilerAlquiler.getAlquiler().remove(alquilerAlquiler);
                    oldPropiedadOfAlquilerAlquiler = em.merge(oldPropiedadOfAlquilerAlquiler);
                }
            }
            for (PagoExpensa pagoexpensaPagoExpensa : propiedad.getPagoexpensa()) {
                Propiedad oldPropiedadesOfPagoexpensaPagoExpensa = pagoexpensaPagoExpensa.getPropiedades();
                pagoexpensaPagoExpensa.setPropiedades(propiedad);
                pagoexpensaPagoExpensa = em.merge(pagoexpensaPagoExpensa);
                if (oldPropiedadesOfPagoexpensaPagoExpensa != null) {
                    oldPropiedadesOfPagoexpensaPagoExpensa.getPagoexpensa().remove(pagoexpensaPagoExpensa);
                    oldPropiedadesOfPagoexpensaPagoExpensa = em.merge(oldPropiedadesOfPagoexpensaPagoExpensa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propiedad propiedad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad persistentPropiedad = em.find(Propiedad.class, propiedad.getCodigo());
            Cliente clientesOld = persistentPropiedad.getClientes();
            Cliente clientesNew = propiedad.getClientes();
            Venta ventasOld = persistentPropiedad.getVentas();
            Venta ventasNew = propiedad.getVentas();
            Anuncio anunciosOld = persistentPropiedad.getAnuncios();
            Anuncio anunciosNew = propiedad.getAnuncios();
            Tasacion tasacionOld = persistentPropiedad.getTasacion();
            Tasacion tasacionNew = propiedad.getTasacion();
            TipoPropiedad tipropiedadOld = persistentPropiedad.getTipropiedad();
            TipoPropiedad tipropiedadNew = propiedad.getTipropiedad();
            Ciudad ciudadesOld = persistentPropiedad.getCiudades();
            Ciudad ciudadesNew = propiedad.getCiudades();
            Edificio edificiosOld = persistentPropiedad.getEdificios();
            Edificio edificiosNew = propiedad.getEdificios();
            List<Alquiler> alquilerOld = persistentPropiedad.getAlquiler();
            List<Alquiler> alquilerNew = propiedad.getAlquiler();
            List<PagoExpensa> pagoexpensaOld = persistentPropiedad.getPagoexpensa();
            List<PagoExpensa> pagoexpensaNew = propiedad.getPagoexpensa();
            if (clientesNew != null) {
                clientesNew = em.getReference(clientesNew.getClass(), clientesNew.getCodigo());
                propiedad.setClientes(clientesNew);
            }
            if (ventasNew != null) {
                ventasNew = em.getReference(ventasNew.getClass(), ventasNew.getCodigo());
                propiedad.setVentas(ventasNew);
            }
            if (anunciosNew != null) {
                anunciosNew = em.getReference(anunciosNew.getClass(), anunciosNew.getCodigo());
                propiedad.setAnuncios(anunciosNew);
            }
            if (tasacionNew != null) {
                tasacionNew = em.getReference(tasacionNew.getClass(), tasacionNew.getCodigo());
                propiedad.setTasacion(tasacionNew);
            }
            if (tipropiedadNew != null) {
                tipropiedadNew = em.getReference(tipropiedadNew.getClass(), tipropiedadNew.getCodigo());
                propiedad.setTipropiedad(tipropiedadNew);
            }
            if (ciudadesNew != null) {
                ciudadesNew = em.getReference(ciudadesNew.getClass(), ciudadesNew.getCodigo());
                propiedad.setCiudades(ciudadesNew);
            }
            if (edificiosNew != null) {
                edificiosNew = em.getReference(edificiosNew.getClass(), edificiosNew.getCodigo());
                propiedad.setEdificios(edificiosNew);
            }
            List<Alquiler> attachedAlquilerNew = new ArrayList<Alquiler>();
            for (Alquiler alquilerNewAlquilerToAttach : alquilerNew) {
                alquilerNewAlquilerToAttach = em.getReference(alquilerNewAlquilerToAttach.getClass(), alquilerNewAlquilerToAttach.getCodigo());
                attachedAlquilerNew.add(alquilerNewAlquilerToAttach);
            }
            alquilerNew = attachedAlquilerNew;
            propiedad.setAlquiler(alquilerNew);
            List<PagoExpensa> attachedPagoexpensaNew = new ArrayList<PagoExpensa>();
            for (PagoExpensa pagoexpensaNewPagoExpensaToAttach : pagoexpensaNew) {
                pagoexpensaNewPagoExpensaToAttach = em.getReference(pagoexpensaNewPagoExpensaToAttach.getClass(), pagoexpensaNewPagoExpensaToAttach.getCodigo());
                attachedPagoexpensaNew.add(pagoexpensaNewPagoExpensaToAttach);
            }
            pagoexpensaNew = attachedPagoexpensaNew;
            propiedad.setPagoexpensa(pagoexpensaNew);
            propiedad = em.merge(propiedad);
            if (clientesOld != null && !clientesOld.equals(clientesNew)) {
                clientesOld.getPropiedades().remove(propiedad);
                clientesOld = em.merge(clientesOld);
            }
            if (clientesNew != null && !clientesNew.equals(clientesOld)) {
                clientesNew.getPropiedades().add(propiedad);
                clientesNew = em.merge(clientesNew);
            }
            if (ventasOld != null && !ventasOld.equals(ventasNew)) {
                ventasOld.setPropiedades(null);
                ventasOld = em.merge(ventasOld);
            }
            if (ventasNew != null && !ventasNew.equals(ventasOld)) {
                Propiedad oldPropiedadesOfVentas = ventasNew.getPropiedades();
                if (oldPropiedadesOfVentas != null) {
                    oldPropiedadesOfVentas.setVentas(null);
                    oldPropiedadesOfVentas = em.merge(oldPropiedadesOfVentas);
                }
                ventasNew.setPropiedades(propiedad);
                ventasNew = em.merge(ventasNew);
            }
            if (anunciosOld != null && !anunciosOld.equals(anunciosNew)) {
                anunciosOld.getPropiedades().remove(propiedad);
                anunciosOld = em.merge(anunciosOld);
            }
            if (anunciosNew != null && !anunciosNew.equals(anunciosOld)) {
                anunciosNew.getPropiedades().add(propiedad);
                anunciosNew = em.merge(anunciosNew);
            }
            if (tasacionOld != null && !tasacionOld.equals(tasacionNew)) {
                tasacionOld.setPropiedades(null);
                tasacionOld = em.merge(tasacionOld);
            }
            if (tasacionNew != null && !tasacionNew.equals(tasacionOld)) {
                Propiedad oldPropiedadesOfTasacion = tasacionNew.getPropiedades();
                if (oldPropiedadesOfTasacion != null) {
                    oldPropiedadesOfTasacion.setTasacion(null);
                    oldPropiedadesOfTasacion = em.merge(oldPropiedadesOfTasacion);
                }
                tasacionNew.setPropiedades(propiedad);
                tasacionNew = em.merge(tasacionNew);
            }
            if (tipropiedadOld != null && !tipropiedadOld.equals(tipropiedadNew)) {
                tipropiedadOld.getPropiedades().remove(propiedad);
                tipropiedadOld = em.merge(tipropiedadOld);
            }
            if (tipropiedadNew != null && !tipropiedadNew.equals(tipropiedadOld)) {
                tipropiedadNew.getPropiedades().add(propiedad);
                tipropiedadNew = em.merge(tipropiedadNew);
            }
            if (ciudadesOld != null && !ciudadesOld.equals(ciudadesNew)) {
                ciudadesOld.getPropiedades().remove(propiedad);
                ciudadesOld = em.merge(ciudadesOld);
            }
            if (ciudadesNew != null && !ciudadesNew.equals(ciudadesOld)) {
                ciudadesNew.getPropiedades().add(propiedad);
                ciudadesNew = em.merge(ciudadesNew);
            }
            if (edificiosOld != null && !edificiosOld.equals(edificiosNew)) {
                edificiosOld.getPropiedades().remove(propiedad);
                edificiosOld = em.merge(edificiosOld);
            }
            if (edificiosNew != null && !edificiosNew.equals(edificiosOld)) {
                edificiosNew.getPropiedades().add(propiedad);
                edificiosNew = em.merge(edificiosNew);
            }
            for (Alquiler alquilerOldAlquiler : alquilerOld) {
                if (!alquilerNew.contains(alquilerOldAlquiler)) {
                    alquilerOldAlquiler.setPropiedad(null);
                    alquilerOldAlquiler = em.merge(alquilerOldAlquiler);
                }
            }
            for (Alquiler alquilerNewAlquiler : alquilerNew) {
                if (!alquilerOld.contains(alquilerNewAlquiler)) {
                    Propiedad oldPropiedadOfAlquilerNewAlquiler = alquilerNewAlquiler.getPropiedad();
                    alquilerNewAlquiler.setPropiedad(propiedad);
                    alquilerNewAlquiler = em.merge(alquilerNewAlquiler);
                    if (oldPropiedadOfAlquilerNewAlquiler != null && !oldPropiedadOfAlquilerNewAlquiler.equals(propiedad)) {
                        oldPropiedadOfAlquilerNewAlquiler.getAlquiler().remove(alquilerNewAlquiler);
                        oldPropiedadOfAlquilerNewAlquiler = em.merge(oldPropiedadOfAlquilerNewAlquiler);
                    }
                }
            }
            for (PagoExpensa pagoexpensaOldPagoExpensa : pagoexpensaOld) {
                if (!pagoexpensaNew.contains(pagoexpensaOldPagoExpensa)) {
                    pagoexpensaOldPagoExpensa.setPropiedades(null);
                    pagoexpensaOldPagoExpensa = em.merge(pagoexpensaOldPagoExpensa);
                }
            }
            for (PagoExpensa pagoexpensaNewPagoExpensa : pagoexpensaNew) {
                if (!pagoexpensaOld.contains(pagoexpensaNewPagoExpensa)) {
                    Propiedad oldPropiedadesOfPagoexpensaNewPagoExpensa = pagoexpensaNewPagoExpensa.getPropiedades();
                    pagoexpensaNewPagoExpensa.setPropiedades(propiedad);
                    pagoexpensaNewPagoExpensa = em.merge(pagoexpensaNewPagoExpensa);
                    if (oldPropiedadesOfPagoexpensaNewPagoExpensa != null && !oldPropiedadesOfPagoexpensaNewPagoExpensa.equals(propiedad)) {
                        oldPropiedadesOfPagoexpensaNewPagoExpensa.getPagoexpensa().remove(pagoexpensaNewPagoExpensa);
                        oldPropiedadesOfPagoexpensaNewPagoExpensa = em.merge(oldPropiedadesOfPagoexpensaNewPagoExpensa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = propiedad.getCodigo();
                if (findPropiedad(id) == null) {
                    throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad propiedad;
            try {
                propiedad = em.getReference(Propiedad.class, id);
                propiedad.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.", enfe);
            }
            Cliente clientes = propiedad.getClientes();
            if (clientes != null) {
                clientes.getPropiedades().remove(propiedad);
                clientes = em.merge(clientes);
            }
            Venta ventas = propiedad.getVentas();
            if (ventas != null) {
                ventas.setPropiedades(null);
                ventas = em.merge(ventas);
            }
            Anuncio anuncios = propiedad.getAnuncios();
            if (anuncios != null) {
                anuncios.getPropiedades().remove(propiedad);
                anuncios = em.merge(anuncios);
            }
            Tasacion tasacion = propiedad.getTasacion();
            if (tasacion != null) {
                tasacion.setPropiedades(null);
                tasacion = em.merge(tasacion);
            }
            TipoPropiedad tipropiedad = propiedad.getTipropiedad();
            if (tipropiedad != null) {
                tipropiedad.getPropiedades().remove(propiedad);
                tipropiedad = em.merge(tipropiedad);
            }
            Ciudad ciudades = propiedad.getCiudades();
            if (ciudades != null) {
                ciudades.getPropiedades().remove(propiedad);
                ciudades = em.merge(ciudades);
            }
            Edificio edificios = propiedad.getEdificios();
            if (edificios != null) {
                edificios.getPropiedades().remove(propiedad);
                edificios = em.merge(edificios);
            }
            List<Alquiler> alquiler = propiedad.getAlquiler();
            for (Alquiler alquilerAlquiler : alquiler) {
                alquilerAlquiler.setPropiedad(null);
                alquilerAlquiler = em.merge(alquilerAlquiler);
            }
            List<PagoExpensa> pagoexpensa = propiedad.getPagoexpensa();
            for (PagoExpensa pagoexpensaPagoExpensa : pagoexpensa) {
                pagoexpensaPagoExpensa.setPropiedades(null);
                pagoexpensaPagoExpensa = em.merge(pagoexpensaPagoExpensa);
            }
            em.remove(propiedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propiedad> findPropiedadEntities() {
        return findPropiedadEntities(true, -1, -1);
    }

    public List<Propiedad> findPropiedadEntities(int maxResults, int firstResult) {
        return findPropiedadEntities(false, maxResults, firstResult);
    }

    private List<Propiedad> findPropiedadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propiedad.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Propiedad findPropiedad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propiedad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropiedadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propiedad> rt = cq.from(Propiedad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
   public Propiedad buscarPorNombreUsuario(String nombre) throws Exception {
       Propiedad pro=null;
        
        String jpql="SELECT Object (u) FROM Propiedad u WHERE u.nombre = '"+ nombre+ "'";
        try {
            Query query = getEntityManager().createQuery(jpql);
            pro=(Propiedad) query.getSingleResult();
        } catch (Exception e) {
            return null;
                    
        }
        return pro;
    }
    
    
    public Propiedad buscarPorNombreEstado(String estado) throws Exception {
            Propiedad estados = null;

        String jpql = "SELECT Object (t) FROM TipoPropiedad t WHERE t.tipo = '" + estado + "'";
        try {
            Query query = getEntityManager().createQuery(jpql);
            estados = (Propiedad) query.getSingleResult();
        } catch (Exception e) {
            return null;

        }
        return estados;
    }
}
