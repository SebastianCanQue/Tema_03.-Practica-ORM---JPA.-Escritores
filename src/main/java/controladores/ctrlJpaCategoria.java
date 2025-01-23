package controladores;

import jakarta.persistence.*;
import java.util.List;
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
    
}
