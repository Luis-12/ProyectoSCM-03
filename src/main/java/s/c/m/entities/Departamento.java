package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

    @Id
    private String pk_idDepartamento;
    private String nombre;
    private String estado;
    @OneToMany(mappedBy = "departamento")
    private Set<Colaborador> colaboradores;
    private String justificacion;

    public String getPk_idDepartamento() {
        return pk_idDepartamento;
    }

    public void setPk_idDepartamento(String pk_idDepartamento) {
        this.pk_idDepartamento = pk_idDepartamento;
    }

    public Set<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(Set<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getJustificacion() { return justificacion; }

    public void setJustificacion(String justificacion) { this.justificacion = justificacion; }

    public Departamento()
    {
    }

    @Override
    public String toString()
    {
        return "Departamento{" +
                "pk_coddepartamento='" + pk_idDepartamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
