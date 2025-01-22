package modelos;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Sebastián Candelas Quero
 */

@Entity
@Table(name="Categorias")
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByIdCategoria", query = "SELECT c FROM Categorias c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categorias.findByNomCategoria", query = "SELECT c FROM Categorias c WHERE c.nomCategoria = :nomCategoria")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idCategoria;
    @Basic(optional = false)
    private String nomCategoria;
    @ManyToMany(mappedBy = "categoriasSet")
    private Set<Libro> librosSet;

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(Integer idCategoria, String nomCategoria) {
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public Set<Libro> getLibrosSet() {
        return librosSet;
    }

    public void setLibrosSet(Set<Libro> librosSet) {
        this.librosSet = librosSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.karnedo.mavenproject1.Categorias[ idCategoria=" + idCategoria + " ]";
    }
    
}
