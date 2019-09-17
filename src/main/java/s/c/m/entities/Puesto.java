package s.c.m.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PUESTOS")
public class Puesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_idPuesto;
    private String descripcion;
    @OneToMany(mappedBy = "puesto")
    private Set<Colaborador> colaboradores;

    public int getPk_idPuesto() {
        return pk_idPuesto;
    }

    public void setPk_idPuesto(int pk_idPuesto) {
        this.pk_idPuesto = pk_idPuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(Set<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Puesto() {
    }
}
