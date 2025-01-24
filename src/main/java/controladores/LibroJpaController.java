/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelos.Autor;
import modelos.Categoria;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Libro;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        if (libro.getCategoriasSet() == null) {
            libro.setCategoriasSet(new HashSet<Categoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor autor = libro.getAutor();
            if (autor != null) {
                autor = em.getReference(autor.getClass(), autor.getIdAutor());
                libro.setAutor(autor);
            }
            Set<Categoria> attachedCategoriasSet = new HashSet<Categoria>();
            for (Categoria categoriasSetCategoriaToAttach : libro.getCategoriasSet()) {
                categoriasSetCategoriaToAttach = em.getReference(categoriasSetCategoriaToAttach.getClass(), categoriasSetCategoriaToAttach.getIdCategoria());
                attachedCategoriasSet.add(categoriasSetCategoriaToAttach);
            }
            libro.setCategoriasSet(attachedCategoriasSet);
            em.persist(libro);
            if (autor != null) {
                autor.getLibrosSet().add(libro);
                autor = em.merge(autor);
            }
            for (Categoria categoriasSetCategoria : libro.getCategoriasSet()) {
                categoriasSetCategoria.getLibrosSet().add(libro);
                categoriasSetCategoria = em.merge(categoriasSetCategoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro persistentLibro = em.find(Libro.class, libro.getIdLibros());
            Autor autorOld = persistentLibro.getAutor();
            Autor autorNew = libro.getAutor();
            Set<Categoria> categoriasSetOld = persistentLibro.getCategoriasSet();
            Set<Categoria> categoriasSetNew = libro.getCategoriasSet();
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getIdAutor());
                libro.setAutor(autorNew);
            }
            Set<Categoria> attachedCategoriasSetNew = new HashSet<Categoria>();
            for (Categoria categoriasSetNewCategoriaToAttach : categoriasSetNew) {
                categoriasSetNewCategoriaToAttach = em.getReference(categoriasSetNewCategoriaToAttach.getClass(), categoriasSetNewCategoriaToAttach.getIdCategoria());
                attachedCategoriasSetNew.add(categoriasSetNewCategoriaToAttach);
            }
            categoriasSetNew = attachedCategoriasSetNew;
            libro.setCategoriasSet(categoriasSetNew);
            libro = em.merge(libro);
            if (autorOld != null && !autorOld.equals(autorNew)) {
                autorOld.getLibrosSet().remove(libro);
                autorOld = em.merge(autorOld);
            }
            if (autorNew != null && !autorNew.equals(autorOld)) {
                autorNew.getLibrosSet().add(libro);
                autorNew = em.merge(autorNew);
            }
            for (Categoria categoriasSetOldCategoria : categoriasSetOld) {
                if (!categoriasSetNew.contains(categoriasSetOldCategoria)) {
                    categoriasSetOldCategoria.getLibrosSet().remove(libro);
                    categoriasSetOldCategoria = em.merge(categoriasSetOldCategoria);
                }
            }
            for (Categoria categoriasSetNewCategoria : categoriasSetNew) {
                if (!categoriasSetOld.contains(categoriasSetNewCategoria)) {
                    categoriasSetNewCategoria.getLibrosSet().add(libro);
                    categoriasSetNewCategoria = em.merge(categoriasSetNewCategoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libro.getIdLibros();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getIdLibros();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            Autor autor = libro.getAutor();
            if (autor != null) {
                autor.getLibrosSet().remove(libro);
                autor = em.merge(autor);
            }
            Set<Categoria> categoriasSet = libro.getCategoriasSet();
            for (Categoria categoriasSetCategoria : categoriasSet) {
                categoriasSetCategoria.getLibrosSet().remove(libro);
                categoriasSetCategoria = em.merge(categoriasSetCategoria);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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

    public Libro findLibro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
