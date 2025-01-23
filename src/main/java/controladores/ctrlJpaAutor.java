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
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Autores.findByIdAutor");
            consulta.setParameter("idAutor", idAutor);
            //Guardamos los datos de la lista
            a = (Autor)consulta.getSingleResult();
            System.out.println(a.getLibrosSet().size());
            listaLibrosAutor = a.getLibrosSet();
        }finally{
            em.close();
        }
        return listaLibrosAutor;
    }
}
