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
}
