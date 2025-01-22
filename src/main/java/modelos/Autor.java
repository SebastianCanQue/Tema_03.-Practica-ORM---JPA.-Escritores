package modelos;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Sebastián Candelas Quero
 */

@Entity
@Table(name="Autores")
@NamedQueries({
    @NamedQuery(name = "Autores.findAll", query = "SELECT a FROM Autores a"),
    @NamedQuery(name = "Autores.findByIdAutor", query = "SELECT a FROM Autores a WHERE a.idAutor = :idAutor"),
    @NamedQuery(name = "Autores.findByNomAutor", query = "SELECT a FROM Autores a WHERE a.nomAutor = :nomAutor")})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idAutor;
    @Basic(optional = false)
    private String nomAutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autor")
    private Set<Libro> librosSet;

    public Autor() {
    }

    public Autor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Autor(Integer idAutor, String nomAutor) {
        this.idAutor = idAutor;
        this.nomAutor = nomAutor;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
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
        hash += (idAutor != null ? idAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.idAutor == null && other.idAutor != null) || (this.idAutor != null && !this.idAutor.equals(other.idAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.karnedo.mavenproject1.Autores[ idAutor=" + idAutor + " ]";
    }
    
}