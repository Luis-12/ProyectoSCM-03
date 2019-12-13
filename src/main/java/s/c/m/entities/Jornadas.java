package s.c.m.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name= "jornadas")
public class Jornadas implements Serializable {

    @Id
    private String pk_idjornada;
    private String descripcion;

    public Jornadas() {
    }

    public String getPk_idjornada() {
        return pk_idjornada;
    }

    public void setPk_idjornada(String pk_idjornada) {
        this.pk_idjornada = pk_idjornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
