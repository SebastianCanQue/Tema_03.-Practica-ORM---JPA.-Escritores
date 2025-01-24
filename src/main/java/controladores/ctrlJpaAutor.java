package controladores;

import controladores.exceptions.*;
import jakarta.persistence.*;
import java.util.*;
import javax.swing.JOptionPane;
import modelos.*;

/**
 *
 * @author Sebastián Candelas Quero
 */

public class ctrlJpaAutor {
    
    private EntityManagerFactory emf;

    //Constructor vacío
    public ctrlJpaAutor() {
        
    }
    
    //Constructor con EMF
    public ctrlJpaAutor(EntityManagerFactory emf) {
        this.emf = null;
        this.emf = emf;
    }
    
    //Metodo para crear el EM
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    
    //Metodo para crear un autor
    public void crear(String nombre, Set<Libro>conjuntoLibros){
        Autor autor = new Autor(nombre, conjuntoLibros);
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Libro> librosAniadir = new HashSet<Libro>();
            for(Libro l : autor.getLibrosSet()){
                l = (Libro)em.getReference(Libro.class, l.getIdLibros());
                librosAniadir.add(l);
            }
            autor.setLibrosSet(librosAniadir);
            em.persist(autor);
            for(Libro l : autor.getLibrosSet()){
                Autor antiguoAutor = l.getAutor();
                l.setAutor(autor);
                l = (Libro)em.merge(l);
                if(antiguoAutor != null){
                    antiguoAutor.getLibrosSet().remove(l);
                    antiguoAutor = (Autor) em.merge(antiguoAutor);
                }
            }
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para dar de baja un autor
    public void baja(Autor autor){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            autor = em.getReference(Autor.class, autor.getIdAutor());
            em.remove(autor);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para editar un autor
    public void editar(Autor autor) throws IllegalOrphanException, NonexistentEntityException, Exception{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor antiguoModAutor = em.find(Autor.class, autor.getIdAutor());
            Set<Libro> librosSetAntiguo = antiguoModAutor.getLibrosSet();
            Set<Libro> librosSetNuevo = autor.getLibrosSet();
//            List<String> illegalOrphanMessages = null;
//            for (Libro libroAntiguo : librosSetAntiguo) {
//                if (!librosSetNuevo.contains(libroAntiguo)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("El libro " + libroAntiguo 
//                            + " debe permanecer en la lista del autor ya que no se puede quedar sin autor");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            Set<Libro> NuevoLibrosSet = new HashSet<Libro>();
            for (Libro libroNuevo : librosSetNuevo) {
                libroNuevo = em.getReference(libroNuevo.getClass(), libroNuevo.getIdLibros());
                NuevoLibrosSet.add(libroNuevo);
            }
            librosSetNuevo = NuevoLibrosSet;
            autor.setLibrosSet(librosSetNuevo);
            for (Libro libroNuevo : autor.getLibrosSet()) {
                System.out.println(libroNuevo);
            }
            autor = (Autor)em.merge(autor);
            for (Libro librosNuevo : librosSetNuevo) {
                if (!librosSetAntiguo.contains(librosNuevo)) {
                    Autor antiguoAutor = librosNuevo.getAutor();
                    librosNuevo.setAutor(autor);
                    librosNuevo = (Libro) em.merge(librosNuevo);
                    if (antiguoAutor != null && !antiguoAutor.equals(autor)) {
                        antiguoAutor.getLibrosSet().remove(librosNuevo);
                        antiguoAutor = (Autor)em.merge(antiguoAutor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autor.getIdAutor();
                if (obtenerAutorXId(id) == null) {
                    throw new NonexistentEntityException("El autor " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    //Metodo para obtener los autores, recive un String con el nombre de una namedQuery
    public List<Autor> obtenerAllAutores(){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Autor> listaAutores;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Autores.findAll");
            //Guardamos los datos de la lista
            listaAutores = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaAutores;
    }
    
    //Metodo para obtener la lista de libros de un Autor
    public Set<Libro> obtenerLibrosAutor(Object idAutor){
        EntityManager em = null;
        Set<Libro> listaLibrosAutor;
        Autor a = new Autor();
        try{
            //Inicializamos el em y buscamos el autor
            em = getEntityManager();
            a = em.find(Autor.class, idAutor);
            //Guardamos los datos de la lista
            listaLibrosAutor = a.getLibrosSet();
            listaLibrosAutor.isEmpty();
        }finally{
            em.close();
        }
        return listaLibrosAutor;
    }
    
    //Metodo para obtener la lista de autoress con libros de 'x' categoría
    public List<Autor> obtenerAutoresCateg(String cat){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Autor> listaAutores;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Autores.findByCateg");
            consulta.setParameter("nomCategoria", cat);
            //Guardamos los datos de la lista
            listaAutores = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaAutores;
    }
    
    //Metodo para obtener un autor dado su id
    public Autor obtenerAutorXId(Object id){
        //Creamos el EM 
        EntityManager em = null;
        Autor autor = null;
        try{
            em = getEntityManager();
            autor = em.find(Autor.class, id);
            autor.getLibrosSet().isEmpty();
        }finally{
            em.close();
        }
        return autor;
    }
    
    //Metodo para obtener autor por su nombre
    public Autor obtenerAutorXNombre(Object nombre){
        //Creamos el EM 
        EntityManager em = null;
        Autor autor = null;
        try{
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Autores.findByNomAutor");
            consulta.setParameter("nomAutor", nombre);
            autor = (Autor)consulta.getSingleResult();
            autor.getLibrosSet().isEmpty();
        }finally{
            em.close();
        }
        return autor;
    }
}
