package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {
    @Id
    private String pk_coddepartamento;
    private String nombre;
    private  String descripcion;
    //@OneToOne
    //@JoinColumn(name = "fk_idadmin",nullable = false)
    //private Administrador administrador;
    private String fk_idadmin;
    private String estado;
    @OneToMany(mappedBy = "colaborador")
    private Set<Colaborador> colaboradores;

    public String getPk_coddepartamento() {
        return pk_coddepartamento;
    }

    public void setPk_coddepartamento(String pk_coddepartamento) {
        this.pk_coddepartamento = pk_coddepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFk_idadmin() {
        return fk_idadmin;
    }

    public void setFk_idadmin(String fk_idadmin) {
        this.fk_idadmin = fk_idadmin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Departamento() {
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "pk_coddepartamento='" + pk_coddepartamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
