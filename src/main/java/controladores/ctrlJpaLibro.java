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
        }finally{
            em.close();
        }
        return libro;
    }
    
    //Metodo para obtener la lista de libros con 'x' autor
    public List<Libro> obtenerLibrosAutor(String autor){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Libro> listaAutores;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Autores.findByCateg");
            consulta.setParameter("nomCategoria", autor);
            //Guardamos los datos de la lista
            listaAutores = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaAutores;
    }
}
