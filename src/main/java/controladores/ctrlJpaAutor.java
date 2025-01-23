package controladores;

import jakarta.persistence.*;
import java.util.*;
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
    public void crear(Autor autor){
        if(autor.getLibrosSet() == null){
            autor.setLibrosSet(new HashSet<Libro>());
        }
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para dar de baja un autor
    public void baja(Object idAutor){
        Autor autor;
        EntityManager em = null;
        try{
            em = getEntityManager();
            autor = em.getReference(Autor.class, idAutor);
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        }finally{
            em.close();
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
}
