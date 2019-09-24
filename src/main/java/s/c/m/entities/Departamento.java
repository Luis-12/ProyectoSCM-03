package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

    @Id
    private String pk_codDepartamento;
    private String nombre;
    private String estado;
    @OneToMany(mappedBy = "departamento")
    private Set<Colaborador> colaboradores;

    public String getPk_codDepartamento()
    {
        return pk_codDepartamento;
    }

    public void setPk_codDepartamento(String pk_codDepartamento)
    {
        this.pk_codDepartamento = pk_codDepartamento;
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

    public Departamento()
    {
    }

    @Override
    public String toString()
    {
        return "Departamento{" +
                "pk_coddepartamento='" + pk_codDepartamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
