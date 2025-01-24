package controladores;

import jakarta.persistence.*;
import java.util.*;
import modelos.*;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class ctrlJpaLibro {
    
    private EntityManagerFactory emf;
    
    //Constructor vacío
    public ctrlJpaLibro() {
        
    }
    
    //Constructor con EMF
    public ctrlJpaLibro(EntityManagerFactory emf) {
        this.emf = null;
        this.emf = emf;
    }
    
    //Metodo para crear el EM
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    
    //Metodo para crear un Libro
    public void crear(Libro libro){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Categoria> categAniadir = new HashSet<Categoria>();
            Autor autor = em.getReference(Autor.class, libro.getAutor().getIdAutor());
            for(Categoria c : libro.getCategoriasSet()){
                c = (Categoria)em.getReference(Categoria.class, c.getIdCategoria());
                categAniadir.add(c);
            }
            libro.setCategoriasSet(categAniadir);
            libro.setAutor(autor);
            em.persist(libro);
            autor.getLibrosSet().add(libro);
            autor = (Autor) em.merge(autor);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para dar de baja un libro
    public void baja(Libro libro){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            libro = em.getReference(Libro.class, libro.getIdLibros());
            libro.getAutor().getLibrosSet().remove(libro);
            em.remove(libro);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para obtener las categoríoas de un libro
    public Set<Categoria> obtenerCategoriasLibro (Object nom){
        EntityManager em = null;
        Set<Categoria> listaCategoriasLibros;
        Libro l = new Libro();
        try{
            //Inicializamos el em y buscamos el autor
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Libros.findByTitulo");
            consulta.setParameter("titulo", nom);
            l = (Libro)consulta.getSingleResult();
            //Guardamos los datos de la lista
            listaCategoriasLibros = l.getCategoriasSet();
            listaCategoriasLibros.isEmpty();
        }finally{
            em.close();
        }
        return listaCategoriasLibros;
    }
    
    //Metodo para obtener los libros
    public List<Libro> obtenerAllLibros(){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Libro> listaLibros;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Libros.findAll");
            //Guardamos los datos de la lista
            listaLibros = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaLibros;
    } 
    
    //Metodo para obtener un libro dado su id
    public Libro obtenerLibroXId(Object id){
        //Creamos el EM y una onjeto para guardar el resultado de la consulta
        EntityManager em = null;
        Libro libro = null;
        try{
            //Inicializamos el em 
            em = getEntityManager();
            //Guardamos los datos en el objeto
            libro = em.find(Libro.class, id);
            libro.getCategoriasSet().isEmpty();
        }finally{
            em.close();
        }
        return libro;
    }
    
    //Metodo para obtener la lista de libros con 'x' autor
    public List<Libro> obtenerLibrosAutor(String autor){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Libro> listaLibros;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Libros.findByAutor");
            consulta.setParameter("nomAutor", autor);
            //Guardamos los datos de la lista
            listaLibros = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaLibros;
    }
}
