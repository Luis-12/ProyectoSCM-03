package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "descansos")
public class Descansos implements Serializable {
    @Id
    private int pk_iddescanso;
    @OneToOne
    @JoinColumn(name = "fk_idhorario",nullable = false)
    private Horarios horario;
    private Time horainicio;
    private Time horafinalizacion;
    private String descripcion;
    @OneToMany(mappedBy = "descanso")
    private Set<AsignacionDescansos> asignacionDescansos;

    public Descansos() {
    }

    public Set<AsignacionDescansos> getAsignacionDescansos() {
        return asignacionDescansos;
    }

    public void setAsignacionDescansos(Set<AsignacionDescansos> asignacionDescansos) {
        this.asignacionDescansos = asignacionDescansos;
    }

    public int getPk_iddescanso() {
        return pk_iddescanso;
    }

    public void setPk_iddescanso(int pk_iddescanso) {
        this.pk_iddescanso = pk_iddescanso;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public Time getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Time getHorafinalizacion() {
        return horafinalizacion;
    }

    public void setHorafinalizacion(Time horafinalizacion) {
        this.horafinalizacion = horafinalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
