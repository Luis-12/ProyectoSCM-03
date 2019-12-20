package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name="tipodedescansos")

public class Tipodedescansos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int pk_idDescanso;
    private Time duracion;
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "fk_idColaborador",nullable = false)
    private Colaborador colaborador;

    public int getPk_idDescanso() {
        return pk_idDescanso;
    }

    public void setPk_idDescanso(int pk_idDescanso) {
        this.pk_idDescanso = pk_idDescanso;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
