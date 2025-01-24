package controladores;

import jakarta.persistence.*;
import java.util.*;
import modelos.*;

/**
 *
 * @author Sebastián Candelas Quero
 */
public class ctrlJpaCategoria {

    private EntityManagerFactory emf;
    
    //Constructor vacío
    public ctrlJpaCategoria() {
        
    }
    
    //Constructor con EMF
    public ctrlJpaCategoria(EntityManagerFactory emf) {
        this.emf = null;
        this.emf = emf;
    }
    
    //Metodo para crear el EM
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    
    //Metodo para crear una categoria
    public void crear(Categoria cat){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Libro> listLibros = new HashSet<Libro>();
            cat.setLibrosSet(listLibros);
            em.persist(cat);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para dar de baja una categoria
    public void baja(Categoria cat){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            cat = em.getReference(Categoria.class, cat.getIdCategoria());
            for(Libro l : cat.getLibrosSet()){
                l = em.getReference(Libro.class, l.getIdLibros());
                l.getCategoriasSet().remove(cat);
                l = em.merge(l);
            }
            em.remove(cat);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    //Metodo para obtener las categorías
    public List<Categoria> obtenerCategorias(){
        //Creamos el EM y una lista para guardar el resultado de la consulta
        EntityManager em = null;
        List<Categoria> listaCateg;
        try{
            //Inicializamos el em y generamos una query para la consulta
            em = getEntityManager();
            Query consulta = em.createNamedQuery("Categorias.findAll");
            //Guardamos los datos de la lista
            listaCateg = consulta.getResultList();
        }finally{
            em.close();
        }
        return listaCateg;
    }
    
    //Metodo para obtener una categoria dado si id
    public Categoria obtenerCategXId(Object id){
        EntityManager em = null;
        Categoria c = null;
        try{
            em = getEntityManager();
            c = em.find(Categoria.class, id);
            c.getLibrosSet().isEmpty();
        }finally{
            em.close();
        }
        return c;
    }
    
}
