package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "colaboradores")
public class Colaborador implements Serializable {

    @Id
    private String pk_idColaborador;
    @OneToOne
    @JoinColumn(name = "fk_idDepartamento",nullable = false)
    private Departamento departamento;
    @OneToOne
    @JoinColumn(name = "fk_idPuesto",nullable = false)
    private Puesto puesto;
    private String nombre;
    private String clave;
    private java.lang.Integer telefono;
    private String correo;
    private Date fechaInicioLaboral;
    private String estado;
    private String justificacion;

    public String getPk_idColaborador()
    {
        return pk_idColaborador;
    }

    public void setPk_idColaborador(String pk_idColaborador)
    {
        this.pk_idColaborador = pk_idColaborador;
    }

    public Departamento getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(Puesto puesto)
    {
        this.puesto = puesto;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

    public Integer getTelefono()
    {
        return telefono;
    }

    public void setTelefono(Integer telefono)
    {
        this.telefono = telefono;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public Date getFechaInicioLaboral()
    {
        return fechaInicioLaboral;
    }

    public void setFechaInicioLaboral(Date fechaInicioLaboral)
    {
        this.fechaInicioLaboral = fechaInicioLaboral;
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

    public Colaborador()
    {
    }

    @Override
    public String toString()
    {
        return "Colaborador{" +
                "pk_idColaborador='" + pk_idColaborador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                ", telefono=" + telefono +
                ", correo='" + correo + '\'' +
                ", fechaInicioLaboral=" + fechaInicioLaboral +
                ", estado='" + estado + '\'' +
                '}';
    }
}
