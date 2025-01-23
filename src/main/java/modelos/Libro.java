package modelos;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Sebastián Candelas Quero
 */

@Entity
@Table(name="Libros")
@NamedQueries({
    @NamedQuery(name = "Libros.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libros.findByIdLibros", query = "SELECT l FROM Libro l WHERE l.idLibros = :idLibros"),
    @NamedQuery(name = "Libros.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Libros.findByFechaPublicacion", query = "SELECT l FROM Libro l WHERE l.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Libros.findByPrecio", query = "SELECT l FROM Libro l WHERE l.precio = :precio")})
public class Libro implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idLibros;
    private String titulo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    private BigDecimal precio;
    @JoinTable(name = "Libros_Categorias", joinColumns = {
        @JoinColumn(name = "libro", referencedColumnName = "idLibros")}, inverseJoinColumns = {
        @JoinColumn(name = "categoria", referencedColumnName = "idCategoria")})
    @ManyToMany
    private Set<Categoria> categoriasSet;
    @JoinColumn(name = "autor", referencedColumnName = "idAutor")
    @ManyToOne(optional = false)
    private Autor autor;

    public Libro() {
    }

    public Libro(Integer idLibros) {
        this.idLibros = idLibros;
    }

    public Libro(Integer idLibros, BigDecimal precio) {
        this.idLibros = idLibros;
        this.precio = precio;
    }

    public Integer getIdLibros() {
        return idLibros;
    }

    public void setIdLibros(Integer idLibros) {
        this.idLibros = idLibros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Set<Categoria> getCategoriasSet() {
        return categoriasSet;
    }

    public void setCategoriasSet(Set<Categoria> categoriasSet) {
        this.categoriasSet = categoriasSet;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibros != null ? idLibros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibros == null && other.idLibros != null) || (this.idLibros != null && !this.idLibros.equals(other.idLibros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Libros[ idLibros=" + idLibros + " ]";
    }
}
