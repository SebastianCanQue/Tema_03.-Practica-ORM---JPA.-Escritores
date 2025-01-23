/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelos.Libro;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import modelos.Autor;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class AutorJpaController implements Serializable {

    public AutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autor autor) {
        if (autor.getLibrosSet() == null) {
            autor.setLibrosSet(new HashSet<Libro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Libro> attachedLibrosSet = new HashSet<Libro>();
            for (Libro librosSetLibroToAttach : autor.getLibrosSet()) {
                librosSetLibroToAttach = em.getReference(librosSetLibroToAttach.getClass(), librosSetLibroToAttach.getIdLibros());
                attachedLibrosSet.add(librosSetLibroToAttach);
            }
            autor.setLibrosSet(attachedLibrosSet);
            em.persist(autor);
            for (Libro librosSetLibro : autor.getLibrosSet()) {
                Autor oldAutorOfLibrosSetLibro = librosSetLibro.getAutor();
                librosSetLibro.setAutor(autor);
                librosSetLibro = em.merge(librosSetLibro);
                if (oldAutorOfLibrosSetLibro != null) {
                    oldAutorOfLibrosSetLibro.getLibrosSet().remove(librosSetLibro);
                    oldAutorOfLibrosSetLibro = em.merge(oldAutorOfLibrosSetLibro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autor autor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor persistentAutor = em.find(Autor.class, autor.getIdAutor());
            Set<Libro> librosSetOld = persistentAutor.getLibrosSet();
            Set<Libro> librosSetNew = autor.getLibrosSet();
            List<String> illegalOrphanMessages = null;
            for (Libro librosSetOldLibro : librosSetOld) {
                if (!librosSetNew.contains(librosSetOldLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Libro " + librosSetOldLibro + " since its autor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Libro> attachedLibrosSetNew = new HashSet<Libro>();
            for (Libro librosSetNewLibroToAttach : librosSetNew) {
                librosSetNewLibroToAttach = em.getReference(librosSetNewLibroToAttach.getClass(), librosSetNewLibroToAttach.getIdLibros());
                attachedLibrosSetNew.add(librosSetNewLibroToAttach);
            }
            librosSetNew = attachedLibrosSetNew;
            autor.setLibrosSet(librosSetNew);
            autor = em.merge(autor);
            for (Libro librosSetNewLibro : librosSetNew) {
                if (!librosSetOld.contains(librosSetNewLibro)) {
                    Autor oldAutorOfLibrosSetNewLibro = librosSetNewLibro.getAutor();
                    librosSetNewLibro.setAutor(autor);
                    librosSetNewLibro = em.merge(librosSetNewLibro);
                    if (oldAutorOfLibrosSetNewLibro != null && !oldAutorOfLibrosSetNewLibro.equals(autor)) {
                        oldAutorOfLibrosSetNewLibro.getLibrosSet().remove(librosSetNewLibro);
                        oldAutorOfLibrosSetNewLibro = em.merge(oldAutorOfLibrosSetNewLibro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autor.getIdAutor();
                if (findAutor(id) == null) {
                    throw new NonexistentEntityException("The autor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor autor;
            try {
                autor = em.getReference(Autor.class, id);
                autor.getIdAutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Libro> librosSetOrphanCheck = autor.getLibrosSet();
            for (Libro librosSetOrphanCheckLibro : librosSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Autor (" + autor + ") cannot be destroyed since the Libro " + librosSetOrphanCheckLibro + " in its librosSet field has a non-nullable autor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autor> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Autor> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Autor> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autor.class));
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

    public Autor findAutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autor> rt = cq.from(Autor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
